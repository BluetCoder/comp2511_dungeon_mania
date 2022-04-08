package dungeonmania;

import dungeonmania.Goals.*;
import dungeonmania.Goals.ExitGoal;
import dungeonmania.Goals.GoalComposite;
import dungeonmania.dungeon.Dungeon;

import dungeonmania.entity.*;
import dungeonmania.entity.Spider.Spider;

import dungeonmania.exceptions.InvalidActionException;
import dungeonmania.inventory.*;
import dungeonmania.response.models.DungeonResponse;

import dungeonmania.util.Battle;
import dungeonmania.util.Direction;
import dungeonmania.util.FileLoader;
import dungeonmania.util.Position;

import java.io.IOException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Random;

import java.util.HashMap;
import java.util.LinkedHashMap;

import org.json.JSONArray;
import org.json.JSONObject;

public class DungeonManiaController {
    Map<Integer, DungeonResponse> dungeonStates = new LinkedHashMap<>();
    private Map<String, Dungeon> dungeons = new HashMap<>();
    private Dungeon playingDungeon;

    public DungeonManiaController() {
    }

    public String getSkin() {
        return "default";
    }

    public String getLocalisation() {
        return "en_US";
    }

    public List<String> getGameModes() {
        return Arrays.asList("Standard", "Peaceful", "Hard");
    }

    /**
     * /dungeons
     * 
     * Done for you.
     */
    public static List<String> dungeons() {
        try {
            return FileLoader.listFileNamesInResourceDirectory("/dungeons");
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }

    /**
     * Creates a new game, where dungeonName is the name of the dungeon map
     * (corresponding to a JSON file stored in the model) and gameMode is one of
     * "standard", "peaceful" or "hard"
     * 
     * @param dungeonName
     * @param gameMode
     * @return a new game
     * @throws IllegalArgumentException
     * @throws IOException
     */
    public DungeonResponse newGame(String dungeonName, String gameMode) throws IllegalArgumentException {
        List<String> allDungeons = dungeons();

        if (allDungeons.contains(dungeonName)) {
            String jsonString;
            try {
                jsonString = FileLoader.loadResourceFile("/dungeons/" + dungeonName + ".json");
            } catch (IOException e) {
                e.printStackTrace();
                throw new IllegalArgumentException("Bad filename");
            }

            JSONObject dungeonJson = new JSONObject(jsonString);

            JSONArray dungeonEntities = dungeonJson.getJSONArray("entities");
            List<Entity> entitiesList = createEntityList(dungeonEntities);
            JSONObject goalConditions = dungeonJson.getJSONObject("goal-condition");

            GoalComposite goals = recursiveNode(goalConditions, entitiesList);

            int width = dungeonJson.getInt("width");
            int height = dungeonJson.getInt("height");
            String id = Integer.toString(dungeons.size());

            Player player = getDungeonPlayer(entitiesList);
            player.setInitialPosition(player.getPosition());
            player.setGameMode(gameMode);

            // need to account for inventory and buildables
            Dungeon newDungeon = new Dungeon(id, dungeonName, entitiesList, goals, width, height, player, gameMode);

            playingDungeon = newDungeon;
            // Random random = new Random();
            // int randomX = random.nextInt(playingDungeon.getWidth() - 2) + 1;
            // int randomY = random.nextInt(playingDungeon.getHeight() - 2) + 1;
            // Position position = new Position(randomX, randomY);
            // int spiderId = playingDungeon.getEntityCount();

            // Spider spider = new Spider(spiderId, "spider", position, true, false, playingDungeon.getSwampTiles(), playingDungeon.getBoulders());
            // // playingDungeon.setEntityCount(id);
            // playingDungeon.addEntityToDungeon(spider);
            // player.attach(spider); 
            // // playingDungeon.setEnt
            // playingDungeon.setEntityCount(entitiesList.size());

            // temporary for mercenary
            for (Entity entity : entitiesList) {
                if (entity instanceof Mercenary) {
                    Mercenary mercenary = (Mercenary) entity;
                    mercenary.setAllEntities(entitiesList);
                    mercenary.setAllSwampTiles(playingDungeon.getSwampTiles());
                    mercenary.setHeight(height);
                    mercenary.setWidth(width);
                    mercenary.setPlayer(player);
                    player.attach(mercenary);
                }
            }

            // delete this later on

            player.setPlayingDungeon(playingDungeon);
            // playingDungeon.setEntityCount(entitiesList.size());
            DungeonResponse dungeonResponse = newDungeon.getDungeonResponse();

            return dungeonResponse;

        }

        return null;
    }

    /**
     * Saves the current game state with the given name so that if the application
     * is terminated, the current game state can be reloaded and the player can
     * continue from where it left off
     * 
     * @param name
     * @return the game save state
     * @throws IllegalArgumentException
     */
    public DungeonResponse saveGame(String name) throws IllegalArgumentException {
        // Saving dungeon and adding to hashMap
        dungeons.put(name, playingDungeon);
        return playingDungeon.getDungeonResponse();
    }

    /**
     * Loads the game with the given id from the existing games saved
     * 
     * @param name
     * @return the saved dungeon game
     * @throws IllegalArgumentException
     */
    public DungeonResponse loadGame(String name) throws IllegalArgumentException {
        Dungeon loadDungeon = dungeons.get(name);
        if (loadDungeon != null) {
            playingDungeon = loadDungeon;
            return loadDungeon.getDungeonResponse();
        }
        return null;
    }

    /**
     * @return a list containing all the saved games that are currently stored
     */
    public List<String> allGames() {
        List<String> allGamesList = new ArrayList<>();
        for (String dungeonName : dungeons.keySet()) {
            allGamesList.add(dungeonName);
        }
        return allGamesList;
    }

    /**
     * Ticks the game state. When a tick occurs: The player moves in the specified
     * direction one square All enemies move respectively Any items which are used
     * are 'actioned' and interact with the relevant entity
     * 
     * @param itemUsed
     * @param movementDirection
     * @return the game state tick
     * @throws IllegalArgumentException
     * @throws InvalidActionException
     */
    public DungeonResponse tick(String itemUsed, Direction movementDirection)
            throws IllegalArgumentException, InvalidActionException {
        // List<Entity> allEntities = playingDungeon.getEntities();
        List<Portal> allPortals = playingDungeon.getPortals();
        List<SwampTile> allSwampTiles = playingDungeon.getSwampTiles();
        List<ZombieToastSpawner> allSpawners = playingDungeon.getSpawners();
        List<Boulder> allBoulders = playingDungeon.getBoulders();

        Player player = playingDungeon.getPlayer();
        DungeonResponse tick = null;

        if (itemUsed != null) {
            Item item = player.getInventory().getInventory().get(Integer.valueOf(itemUsed));
            switch(item.getType()) {
                case "bomb":
                    if (player.getInventory().itemExists("bomb")) {
                        BombInv bomb = (BombInv)item;
                        bomb.place(playingDungeon);
                        break;
                    }
                case "health_potion":
                    if (player.getInventory().itemExists("health_potion")) {
                        HealthPotionInv potion = (HealthPotionInv)item;
                        potion.drink(player);
                        break;
                    }
                case "invisibility_potion":
                    if (player.getInventory().itemExists("invisibility_potion")) {
                        InvisibilityPotionInv potion = (InvisibilityPotionInv)item;
                        potion.drink(player);
                        break;
                    }
                case "invincibility_potion":
                    if (player.getInventory().itemExists("invincibility_potion")) {
                        InvincibilityPotionInv potion = (InvincibilityPotionInv)item;
                        potion.drink(player);
                        break;
                    }
                case "sceptre":
                    if (player.getInventory().itemExists("sceptre")) {
                        Sceptre sceptre = (Sceptre)item;
                        sceptre.mindControl(player, playingDungeon);
                        break;
                    }
                default: {
                    break;
                }
            }
        }
        player.checkAllies();

        if (player.getPlayerState().getRemainingTime() > 0) {
            player.getPlayerState().reduceRemainingTime();
        } else {
            player.getPlayerState().toNormal(player);
        }
        if (movementDirection != null) {
            player.setPlayerMovement(movementDirection, playingDungeon.getEntities());
        } else {
            player.notifyObservers();
        }
        playingDungeon.setFloorSwitchToggle();
        playingDungeon.checkForBombExplosions();

        for (Portal portal : allPortals) {
            if (portal.checkPortalPosition(player) == true) {
                String portalColour = portal.getColour();
                int portalId = portal.getId();

                for (Portal portal2 : allPortals) {
                    if (portal2.getColour().equals(portalColour) && portal2.getId() != portalId) {
                        Position newPlayerPostion = portal2.teleportPlayer(movementDirection);
                        if (portal2.checkBlockingEntities(newPlayerPostion, playingDungeon)) {
                            player.setPosition(newPlayerPostion);
                        }
                    }
                }
            }
        }

        int count = playingDungeon.getTickCount();

        if (count % playingDungeon.getZombieSpawnTick() == 0) {
            for (ZombieToastSpawner spawner : allSpawners) {
                int id = playingDungeon.getEntityCount();
                spawner.canSpawnZombie(playingDungeon.getEntities(), player, id, allSwampTiles, playingDungeon);
            }
        }

        if (count % playingDungeon.getSpiderSpawnTick() == 0 && playingDungeon.getSpiders().size() < 5) {
            Random random = new Random();
            int randomX = random.nextInt(playingDungeon.getWidth() - 2) + 1;
            int randomY = random.nextInt(playingDungeon.getHeight() - 2) + 1;
            Position position = new Position(randomX, randomY);
            int id = playingDungeon.getEntityCount();

            Spider spider = new Spider(id, "spider", position, true, false, allSwampTiles, allBoulders);
            playingDungeon.addEntityToDungeon(spider);
            player.attach(spider);

        }

        if (count % playingDungeon.getHydraSpawnTick() == 0 && playingDungeon.getGameMode().equals("Hard")) {
            Position spawnPoint = player.getInitialPosition();
            int id = playingDungeon.getEntityCount();
            Hydra hydra = new Hydra(id, "hydra", spawnPoint, true, false, playingDungeon.getEntities(), allSwampTiles);
            playingDungeon.addEntityToDungeon(hydra);
            player.attach(hydra);
        }
        if (count % playingDungeon.getMercenarySpawnTick() == 0) {
            Position spawnPoint = player.getInitialPosition();
            int id = playingDungeon.getEntityCount();
            Mercenary mercenary = null;
            Random random = new Random();
            if (random.nextInt(1000) < 299) {
                mercenary = new Assassin(id, "assassin", spawnPoint, true, false);
            } else {
                mercenary = new Mercenary(id, "mercenary", spawnPoint, true, false);
            }
            int width = playingDungeon.getWidth();
            int height = playingDungeon.getHeight();
            mercenary.setAllEntities(playingDungeon.getEntities());
            mercenary.setAllSwampTiles(playingDungeon.getSwampTiles());
            mercenary.setHeight(height);
            mercenary.setWidth(width);
            mercenary.setPlayer(player);
            player.attach(mercenary);
            playingDungeon.addEntityToDungeon(mercenary);
        }
        dungeonStates.put(count, playingDungeon.getDungeonResponse());

        playingDungeon.setTickCount(count += 1);

        String gameMode = playingDungeon.getGameMode();
        Battle.doBattle(player, playingDungeon.getEntities(), gameMode, playingDungeon);

        tick = playingDungeon.checkGoalsCompleted();
        return tick;
    }

    public DungeonResponse rewind(int ticks) throws IllegalArgumentException {
        if (ticks <= 0) {
            throw new IllegalArgumentException();
        }

        int lastKey = dungeonStates.keySet().size();

        if (ticks == 1) {
            dungeonStates.remove(lastKey);
        } else if (ticks == 5) {
            dungeonStates.keySet()
                    .removeAll(Arrays.asList(dungeonStates.keySet().toArray()).subList(lastKey - 5, lastKey));
        }

        int newlastKey = dungeonStates.keySet().size();
        DungeonResponse newDungeonState = dungeonStates.get(newlastKey);

        return newDungeonState;
    }

    /**
     * Interacts with a mercenary (where the character bribes the mercenary) or a
     * zombie spawner, where the character destroys the spawner
     * 
     * @param entityId
     * @return
     * @throws IllegalArgumentException
     * @throws InvalidActionException
     */
    public DungeonResponse interact(String entityId) throws IllegalArgumentException, InvalidActionException {

        Player player = playingDungeon.getPlayer();
        Entity entity = getEntityById(Integer.parseInt(entityId));

        if (entity instanceof InteractableInterface) {
            ((InteractableInterface) entity).interactAction(player, playingDungeon);
        }

        DungeonResponse interact = playingDungeon.getDungeonResponse();

        return interact;
    }

    public Entity getEntityById(int id) {
        Entity entity = null;
        for (Entity e : playingDungeon.getEntities()) {
            if (e.getId() == id) {
                entity = e;
            }
        }
        return entity;
    }

    /**
     * Builds the given entity, where buildable is one of bow and shield
     * 
     * @param buildable
     * @return
     * @throws IllegalArgumentException
     * @throws InvalidActionException
     */
    public DungeonResponse build(String buildable) throws IllegalArgumentException, InvalidActionException {
        Player player = playingDungeon.getPlayer();

        switch (buildable) {
        case "bow":
            if (player.getInventory().getBowBuild()) {
                Bow bow = new Bow("bow");
                bow.build(player);

            }
            break;
        case "shield":
            if (player.getInventory().getShieldBuild()) {
                Shield shield = new Shield("shield");
                shield.build(player);
            }
            break;
        case "sceptre":
            if (player.getInventory().getSceptreBuild()) {
                Sceptre sceptre = new Sceptre("sceptre");
                sceptre.build(player);
            }
            break;
        case "midnight_armour":
            if (player.getInventory().getMidnightArmourBuild()) {
                MidnightArmour midnightArmour = new MidnightArmour("midnight_armour");
                midnightArmour.build(player);
            }
            break;

        }
        player.getInventory().canBuild();
        DungeonResponse build = playingDungeon.getDungeonResponse();

        return build;
    }

    /**
     * Gets the character for the dungeon
     * 
     * @param entities
     * @return the player
     */
    public Player getDungeonPlayer(List<Entity> entities) {
        Player player = null;
        for (Entity entity : entities) {
            if (entity.getType().equals("player")) {
                player = ((Player) entity);
            }
        }
        return player;
    }

    /**
     * Creates a list to store entities
     * 
     * @param dungeonEntities
     * @return the entity list
     */
    private List<Entity> createEntityList(JSONArray dungeonEntities) {
        List<Entity> entitiesList = new ArrayList<Entity>();

        for (int i = 0; i < dungeonEntities.length(); i++) {
            JSONObject entityObject = dungeonEntities.getJSONObject(i);
            Entity entity = createEntity(entityObject, i);

            if (entity != null) {
                entitiesList.add(entity);
            }

        }

        return entitiesList;
    }

    /**
     * Creates any entity that is needed
     * 
     * @param id
     * @param entityObject
     * @return an entity of a particular type
     */
    private Entity createEntity(JSONObject entityObject, int id) {
        String type = entityObject.getString("type");
        int x = entityObject.getInt("x");
        int y = entityObject.getInt("y");
        Position position = new Position(x, y);

        Entity entity = null;
        switch (type) {
        case "time_turner":
            entity = new TimeTurner(id, type, position, false, false);
            break;
        case "player":
            entity = new Player(id, type, position, false, false);
            break;
        case "wall":
            entity = new Wall(id, type, position, false, true);
            break;
        case "exit":
            entity = new Exit(id, type, position, false, false);
            break;
        case "door":
            int doorKeyNum = entityObject.getInt("key");
            entity = new Door(id, type, position, false, true, doorKeyNum);
            break;
        case "key":
            int keyNum = entityObject.getInt("key");
            entity = new Key(id, type, position, false, false, keyNum);
            break;
        case "boulder":
            entity = new Boulder(id, type, position, false, true);
            break;
        case "switch":
            entity = new FloorSwitch(id, type, position, false, false);
            break;
        case "bomb":
            entity = new Bomb(id, type, position, false, false);
            break;
        case "sword":
            entity = new Sword(id, type, position, false, false);
            break;
        case "invincibility_potion":
            entity = new InvincibilityPotion(id, type, position, false, false);
            break;
        case "invisibility_potion":
            entity = new InvisibilityPotion(id, type, position, false, false);
            break;
        case "armour":
            entity = new Armour(id, type, position, false, false);
            break;
        case "arrow":
            entity = new Arrow(id, type, position, false, false);
            break;
        case "health_potion":
            entity = new HealthPotion(id, type, position, false, false);
            break;
        case "mercenary":
            entity = new Mercenary(id, type, position, true, false);
            break;
        case "portal":
            String portalColour = entityObject.getString("colour");
            entity = new Portal(id, type, position, false, false, portalColour);
            break;
        case "treasure":
            entity = new Treasure(id, type, position, false, false);
            break;
        case "wood":
            entity = new Wood(id, type, position, false, false);
            break;
        case "zombie_toast_spawner":
            entity = new ZombieToastSpawner(id, type, position, true, true);
            break;
        case "sun_stone":
            entity = new SunStone(id, type, position, false, false);
            break;
        case "swamp_tile":
            int movementFactor = entityObject.getInt("movement_factor");
            entity = new SwampTile(id, type, position, false, false, movementFactor);
            break;
        }

        return entity;
    }

    public GoalComposite recursiveNode(JSONObject goalConditions, List<Entity> allEntities) {
        String goal = goalConditions.getString("goal");
        if (goal.equals("AND")) {
            JSONArray subGoals = goalConditions.getJSONArray("subgoals");
            JSONObject goal1 = subGoals.getJSONObject(0);
            JSONObject goal2 = subGoals.getJSONObject(1);
            return new AndGoal(recursiveNode(goal1, allEntities), recursiveNode(goal2, allEntities));
        } else if (goal.equals("OR")) {
            JSONArray subGoals = goalConditions.getJSONArray("subgoals");
            JSONObject goal1 = subGoals.getJSONObject(0);
            JSONObject goal2 = subGoals.getJSONObject(1);
            return new OrGoal(recursiveNode(goal1, allEntities), recursiveNode(goal2, allEntities));
        } else if (goal.equals("exit")) {
            return new ExitGoal(allEntities);
        } else if (goal.equals("boulders")) {
            return new BoulderGoal(allEntities);
        } else if (goal.equals("treasure")) {
            return new TreasureGoal(allEntities);
        } else {
            return new EnemiesGoal(allEntities);
        }
        // the else statement kinda sucks.
    }
}
