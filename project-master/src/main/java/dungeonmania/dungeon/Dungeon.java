package dungeonmania.dungeon;

import java.util.ArrayList;
import java.util.List;

import dungeonmania.Goals.GoalComposite;
import dungeonmania.entity.Bomb;
import dungeonmania.entity.Boulder;
import dungeonmania.entity.Entity;
import dungeonmania.entity.FloorSwitch;
import dungeonmania.entity.Player;
import dungeonmania.entity.Portal;
import dungeonmania.entity.SwampTile;
import dungeonmania.entity.ZombieToastSpawner;
import dungeonmania.entity.Spider.Spider;
import dungeonmania.inventory.Item;
import dungeonmania.response.models.ItemResponse;
import dungeonmania.util.Position;
import dungeonmania.response.models.AnimationQueue;
import dungeonmania.response.models.DungeonResponse;
import dungeonmania.response.models.EntityResponse;

public class Dungeon {
    private String dungeonId;
    private String dungeonName;
    private List<Entity> entities;
    private GoalComposite goals;
    private List<AnimationQueue> animations;
    private int width;
    private int height;
    private Player player;
    private int tickCount;
    private String gameMode;
    private int entityCount;

    /**
     * Dungeon class
     * 
     * @param dungeonId
     * @param dungeonName
     * @param entities
     * @param goals
     * @param goalLogic
     * @param width
     * @param height
     * @param player
     * @param gameMode
     */
    public Dungeon(String dungeonId, String dungeonName, List<Entity> entities, GoalComposite goals, int width,
            int height, Player player, String gameMode) {
        this.dungeonId = dungeonId;
        this.dungeonName = dungeonName;
        this.entities = entities;
        this.goals = goals;
        this.width = width;
        this.height = height;
        this.player = player;
        this.gameMode = gameMode;
        this.tickCount = 1;
        this.entityCount = entities.size();
    }

    /**
     * Returns the entity count for ID purposes
     * 
     * @return entityCount
     */
    public int getEntityCount() {
        return entityCount;
    }

    /**
     * 
     * @param entityCount
     */
    public void setEntityCount(int entityCount) {
        this.entityCount = entityCount;
    }

    /**
     * Returns the current tick count
     * 
     * @return int
     */
    public int getTickCount() {
        return tickCount;
    }

    /**
     * Sets the tick count
     * 
     * @param tickCount
     */
    public void setTickCount(int tickCount) {
        this.tickCount = tickCount;
    }

    /**
     * Method we call to remove an entity from our list based on death or pick up
     * 
     * @param e
     */
    public void removeEntityFromDungeon(Entity e) {
        entities.remove(e);
    }

    /**
     * Method we call to add entities to our entity list and increase entity count
     * for ID
     * 
     * @param e
     */
    public void addEntityToDungeon(Entity e) {
        entities.add(e);
        setEntityCount(entityCount += 1);
    }

    /**
     * 
     * @return int for what ticks zombies can spawn
     */
    public int getZombieSpawnTick() {
        if (gameMode.equals("Hard")) {
            return 15;
        }
        return 20;
    }

    /**
     * 
     * @return int for what ticks hydra can spawn
     */
    public int getHydraSpawnTick() {
        return 50;
    }

    /**
     * 
     * @return int for what ticks spider can spawn
     */
    public int getSpiderSpawnTick() {
        return 25;
    }

    /**
     * 
     * @return int for what ticks mercenary can spawn
     */
    public int getMercenarySpawnTick() {
        return 30;
    }

    /**
     * Returns the player for the dungeon
     * 
     * @return Player
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * Returns the width of the dungeon
     * 
     * @return int
     */
    public int getWidth() {
        return width;
    }

    /**
     * Returns the height of the dungeon
     * 
     * @return int
     */
    public int getHeight() {
        return height;
    }

    /**
     * Returns the dungeon id
     * 
     * @return String
     */
    public String getDungeonId() {
        return dungeonId;
    }

    /**
     * Returns the game difficulty
     * 
     * @return String
     */
    public String getGameMode() {
        return gameMode;
    }

    /**
     * Returns the dungeon name
     * 
     * @return String
     */
    public String getDungeonName() {
        return dungeonName;
    }

    /**
     * Returns all the entities stored in the dungeon
     * 
     * @return List<Entity>
     */
    public List<Entity> getEntities() {
        return entities;
    }

    /**
     * Returns all the builded items
     * 
     * @return List<String>
     */
    public List<String> getBuildables() {
        List<String> buildable = new ArrayList<String>();
        if (this.getPlayer().getInventory().getBowBuild()) {
            buildable.add("bow");
        }
        if (this.getPlayer().getInventory().getShieldBuild()) {
            buildable.add("shield");
        }
        if (this.getPlayer().getInventory().getSceptreBuild()) {
            buildable.add("sceptre");
        }
        if (this.getPlayer().getInventory().getMidnightArmourBuild() && !entitiesExist("zombie_toast")) {
            buildable.add("midnight_armour");
        }
        return buildable;
    }

    /**
     * Checks during each tick whether if a floorswitch has been toggled by a
     * boulder, and sets it to be true if toggled, false if a boulder is not
     * pressing on the switch
     */
    public void setFloorSwitchToggle() {
        List<Position> allBoulderPositions = new ArrayList<Position>();
        for (Entity entity : entities) {
            if (entity.getType().equals("boulder")) {
                Boulder boulder = (Boulder) entity;
                allBoulderPositions.add(boulder.getPosition());
            }
        }

        for (Entity entity : entities) {
            if (entity.getType().equals("switch")) {
                FloorSwitch floorSwitch = (FloorSwitch) entity;
                if (allBoulderPositions.contains(floorSwitch.getPosition())) {
                    floorSwitch.setToggledByBoulder(true);
                } else {
                    floorSwitch.setToggledByBoulder(false);
                }
            }
        }
    }

    /**
     * Method that checks if switch next to boulder is toggled, initiating the
     * explosion
     */
    public void checkForBombExplosions() {
        List<FloorSwitch> allSwitches = new ArrayList<>();
        for (Entity entity : entities) {
            if (entity.getType().equals("switch")) {
                FloorSwitch floorSwitch = (FloorSwitch) entity;
                allSwitches.add(floorSwitch);
            }
        }

        for (FloorSwitch fs : allSwitches) {
            boolean isToggled = fs.isToggledByBoulder();
            Position fsPosition = fs.getPosition();

            List<Bomb> allBombs = getBombs();
            for (Bomb bomb : allBombs) {
                boolean isAdjacent = Position.isAdjacent(fsPosition, bomb.getPosition());
                if (isAdjacent && isToggled) {
                    List<Position> positionsRemovedByBomb = bomb.getPosition().getAdjacentPositions();
                    bomb.explode(positionsRemovedByBomb, entities, this);
                }
            }
        }
    }

    /**
     * Returns the dungeon response instance of the current dungeon
     * 
     * @return DungeonResponse
     */
    public DungeonResponse getDungeonResponse() {
        List<EntityResponse> entityResponses = convertToEntityResponse();
        List<ItemResponse> itemResponses = convertToItemResponse();
        String stringGoals = goals.getGoalName();
        List<String> buildable = getBuildables();

        DungeonResponse newDungeonResponse = new DungeonResponse(this.dungeonId, this.dungeonName, entityResponses,
                itemResponses, buildable, stringGoals, this.animations);

        return newDungeonResponse;
    }

    /**
     * Checks after each tick on whether a goal or goals has been completed, and
     * finishes the game based on the set game logic of the dungeon.
     * 
     * If the gameLogic = OR, then only 1 goal needs to be acheived to finish the
     * game If the gameLogic = AND, then all the goals need to be achieved to finish
     * If there is no OR/AND then there is only 1 goal that needs to be achieved to
     * finish
     * 
     * Returns the dungeon response instance with the updated goal logic for each
     * tick
     * 
     * @return DungeonResponse
     */
    public DungeonResponse checkGoalsCompleted() {
        if (goals.checkGoalCompleted()) {
            String goalsFinished = "";
            List<EntityResponse> entityResponses = convertToEntityResponse();
            List<ItemResponse> itemResponses = convertToItemResponse();
            List<String> buildable = getBuildables();

            return new DungeonResponse(this.dungeonId, this.dungeonName, entityResponses, itemResponses, buildable,
                    goalsFinished, this.animations);
        }

        return getDungeonResponse();
    }

    /**
     * Uses all the entities in the dungeon and converts each Entity instance into
     * its corresponding EntityResponse instance to form the EntityResponse list
     * 
     * @return List<EntityResponse>
     */
    private List<EntityResponse> convertToEntityResponse() {
        List<EntityResponse> entityResponses = new ArrayList<EntityResponse>();

        for (int i = 0; i < entities.size(); i++) {
            Entity entity = entities.get(i);
            EntityResponse newEntityResponse = null;

            /**
             * If entity type is unlit_bomb, cast back to type bomb in order to pass bomb
             * object
             */
            if (entity.getType().equals("unlit_bomb")) {
                newEntityResponse = new EntityResponse(entity.getId(), "bomb", entity.getPosition(),
                        entity.isInteractable());
            } else {
                newEntityResponse = new EntityResponse(entity.getId(), entity.getType(), entity.getPosition(),
                        entity.isInteractable());
            }

            entityResponses.add(newEntityResponse);
        }
        return entityResponses;
    }

    /**
     * Returns entities in player inventory in dungeon and converts inventory
     * instance into its corressponding ItemResponse
     * 
     * @return List<ItemResponse>
     */
    private List<ItemResponse> convertToItemResponse() {
        List<ItemResponse> itemResponses = new ArrayList<ItemResponse>();
        List<Item> inventory = this.player.getInventory().getInventory();

        for (int i = 0; i < inventory.size(); i++) {
            Item item = inventory.get(i);
            String itemId = Integer.toString(i);

            ItemResponse newItemResponse = new ItemResponse(itemId, item.getType());
            itemResponses.add(newItemResponse);
        }
        return itemResponses;
    }

    /**
     * Method that returns a list of portal entities
     * 
     * @return List<Portal>
     */
    public List<Portal> getPortals() {

        List<Portal> allPortals = new ArrayList<>();
        for (Entity entity : entities) {
            if (entity.getType().equals("portal")) {
                allPortals.add((Portal) entity);
            }
        }

        return allPortals;
    }

    /**
     * Method used to check for moving entities currently in dungeon
     * 
     * @param type
     * @return
     */
    public boolean entitiesExist(String type) {
        for (Entity entity : this.getEntities()) {
            if (entity.getType().equals(type)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Method that returns all swamp tile entities
     * 
     * @return
     */
    public List<SwampTile> getSwampTiles() {

        List<SwampTile> allSwampTiles = new ArrayList<>();
        for (Entity entity : entities) {
            if (entity.getType().equals("swamp_tile")) {
                allSwampTiles.add((SwampTile) entity);
            }
        }

        return allSwampTiles;
    }

    /**
     * Method that returns a list of all current spawners in dungeon
     * 
     * @return
     */
    public List<ZombieToastSpawner> getSpawners() {

        List<ZombieToastSpawner> allSpawners = new ArrayList<>();
        for (Entity entity : entities) {
            if (entity.getType().equals("zombie_toast_spawner")) {
                allSpawners.add((ZombieToastSpawner) entity);
            }
        }

        return allSpawners;
    }

    /**
     * Method that returns a list of all current spawners in dungeon
     * 
     * @return
     */
    public List<Boulder> getBoulders() {

        List<Boulder> allBoulder = new ArrayList<>();
        for (Entity entity : entities) {
            if (entity.getType().equals("boulder")) {
                allBoulder.add((Boulder) entity);
            }
        }

        return allBoulder;
    }

    /**
     * Method that returns a list of all current Spider entities in dungeon
     * 
     * @return
     */
    public List<Spider> getSpiders() {
        List<Spider> allSpiders = new ArrayList<>();
        for (Entity e : entities) {
            if (e.getType().equals("spider")) {
                allSpiders.add((Spider) e);
            }
        }

        return allSpiders;
    }

    /**
     * Method that returns a list of all current bombs in dungeon, both lit and
     * unlit bombs
     * 
     * @return
     */
    public List<Bomb> getBombs() {

        List<Bomb> allBombs = new ArrayList<Bomb>();
        for (Entity entity : entities) {
            if (entity.getType().equals("bomb") || entity.getType().equals("unlit_bomb")) {
                allBombs.add((Bomb) entity);
            }
        }

        return allBombs;
    }

    /**
     * Returns a list of entities by position to check for entities adjacent to bomb
     * prior ro detonating
     * 
     * @param position
     * @return
     */
    public List<Entity> getEntitiesByPosition(Position position) {
        List<Entity> entitiesByPosition = new ArrayList<>();
        for (Entity entity : entities) {
            if (entity.getPosition().equals(position)) {
                entitiesByPosition.add(entity);
            }
        }
        return entitiesByPosition;

    }

}
