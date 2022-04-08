package dungeonmania.entity;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import dungeonmania.dungeon.Dungeon;
import dungeonmania.inventory.*;
import dungeonmania.util.Battle;
import dungeonmania.util.Direction;
import dungeonmania.util.Position;

public class Player extends Entity implements Subject, BattleInterface {
    private ArrayList<EnemyObserver> enemies = new ArrayList<EnemyObserver>();
    private String gameMode;
    private double health;
    private double attack = 1;
    private boolean inBattle;
    private Inventory inventory;
    private PlayerState playerState;
    private Position initialPosition;
    private List<Entity> allies = new ArrayList<>();
    private Dungeon playingDungeon;

    /**
     * Player class that inherits Entity super class
     * 
     * @param id
     * @param id
     * @param type
     * @param position
     * @param isInteractable
     * @param isCollidable
     * @param inBattle
     * @param inventory
     * @param playerState
     */
    public Player(int id, String type, Position position, boolean isInteractable, boolean isCollidable) {
        super(id, type, position, isInteractable, isCollidable);
        this.inBattle = false;
        this.inventory = new Inventory();
        this.playerState = new NormalState();
    }

    /**
     * Returns the dungeon the player is playing in
     * 
     * @return Dungeon
     */
    public Dungeon getPlayingDungeon() {
        return playingDungeon;
    }

    /**
     * Sets the dungeon the player is playing in
     * 
     * @param playingDungeon
     */
    public void setPlayingDungeon(Dungeon playingDungeon) {
        this.playingDungeon = playingDungeon;
    }

    /**
     * Set the player's health
     * 
     * @param health
     */
    public void setHealth(int health) {
        this.health = health;
    }

    /**
     * Get the game mode the player is playing in
     * 
     * @return String
     */
    public String getGameMode() {
        return gameMode;
    }

    /**
     * Sets the game mode the player is playing in
     * 
     * @param gameMode
     */
    public void setGameMode(String gameMode) {
        this.gameMode = gameMode;
        if (gameMode.equals("Hard")) {
            setHealth(2500);
        } else {
            setHealth(4000);
        }
    }

    /**
     * Set the amount of player attack damage
     * 
     * @param attack
     */
    public void setAttack(int attack) {
        this.attack = attack;
    }

    /**
     * @param playerState
     */
    public void setPlayerState(PlayerState playerState) {
        this.playerState = playerState;
    }

    /**
     * @return PlayerState
     */
    public PlayerState getPlayerState() {
        return playerState;
    }

    /**
     * Sets the player's initial position
     * 
     * @param position
     */
    public void setInitialPosition(Position position) {
        this.initialPosition = position;
    }

    /**
     * Get the player's initial position
     * 
     * @return Position
     */
    public Position getInitialPosition() {
        return initialPosition;
    }

    @Override
    public void attach(EnemyObserver observer) {
        if (!enemies.contains(observer)) {
            enemies.add(observer);
        }
    }

    @Override
    public void detach(EnemyObserver observer) {
        enemies.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (EnemyObserver enemy : enemies) {
            if (this.getPlayerState().getState() == "invincible") {
                enemy.updateEnemyMovementInvincible(this);
            } else {
                enemy.updateEnemyMovement();
            }
        }
    }

    /**
     * Get the player's inventory
     * 
     * @return Inventory
     */
    public Inventory getInventory() {
        return this.inventory;
    }

    /**
     * Sets the player movement from each tick based on the specified direction, and
     * checks to see whether the move is possible
     * 
     * @param movementDirection
     * @param allEntities
     */
    public void setPlayerMovement(Direction movementDirection, List<Entity> allEntities) {
        Position changeInPosition = movementDirection.getOffset();
        Position newPlayerPosition = super.getPosition().translateBy(changeInPosition);
        inBattle = false;
        for (Entity entity : allEntities) {
            String entityType = entity.getType();
            Position entityPosition = entity.getPosition();

            /**
             * Check to see if player is about to move into door and we check to see if it
             * has the require key to open the door, otherwise it won't move through it,
             * will be blocked just like a wall
             */
            if (entityType.equals("door")) {
                Door door = (Door) entity;
                int doorKeyNum = door.getKeyNum();
                Position doorPosition = door.getPosition();

                if (newPlayerPosition.equals(doorPosition) && inventory.itemExists("key")) {
                    KeyInv key = (KeyInv) inventory.itemGetter("key");
                    int keyNum = key.getKeyNum();

                    if (keyNum == doorKeyNum) {
                        super.moveTo(movementDirection);
                        inventory.removeInventory(key);
                        door.setCollidable(false);
                        break;
                    }
                }
                /**
                 * If the player moves into a boulder, we check to see if the boulder can also
                 * subsequently move in the same direction the player was defined to move, if it
                 * can then we move both the player and boulder, if it cannot then both the
                 * player and boulder remain in their original positions
                 */
            } else if (entityType.equals("boulder")) {
                Boulder boulder = (Boulder) entity;
                Position boulderPosition = entityPosition;
                if (newPlayerPosition.equals(boulderPosition) && boulder.canMove(movementDirection, allEntities)) {
                    boulder.moveTo(movementDirection);
                    super.moveTo(movementDirection);
                    /**
                     * With every possible player movement, their opportunity to collect and build
                     * is also considered
                     */
                    this.canCollect(super.getPosition());
                    this.getInventory().canBuild();
                    break;
                }
            } else {
                if (super.canMove(movementDirection, allEntities)) {
                    super.moveTo(movementDirection);
                    this.canCollect(super.getPosition());
                    this.getInventory().canBuild();
                    break;
                }
            }
        }

        Battle.doBattle(this, allEntities, gameMode, playingDungeon);
        this.notifyObservers();
    }

    /**
     * This function checks if the Player's current position overlaps with an Entity
     * that can be collected Then makes an Item class of that Entity, adds it to
     * Player Inventory and removing it from the Entity list
     * 
     * @param position
     * @param allEntities
     */
    public void canCollect(Position position) {
        for (Entity entity : playingDungeon.getEntities()) {
            if (entity.getPosition().equals(position)) {
                Item item = null;
                switch (entity.getType()) {
                case "treasure":
                    item = new TreasureInv(entity.getType());
                    break;
                case "key":
                    Key key = (Key) entity;
                    if (!this.getInventory().itemExists("key")) {
                        item = new KeyInv(key.getType(), key.getKeyNum());
                    }
                    break;
                case "health_potion":
                    item = new HealthPotionInv(entity.getType());
                    break;
                case "invincibility_potion":
                    item = new InvincibilityPotionInv(entity.getType());
                    break;
                case "invisibility_potion":
                    item = new InvisibilityPotionInv(entity.getType());
                    break;
                case "wood":
                    item = new WoodInv(entity.getType());
                    break;
                case "arrow":
                    item = new ArrowInv(entity.getType());
                    break;
                case "bomb":
                    item = new BombInv(entity.getType());
                    break;
                case "sword":
                    item = new SwordInv(entity.getType());
                    break;
                case "armour":
                    item = new ArmourInv(entity.getType());
                    break;
                case "sun_stone":
                    item = new SunStoneInv(entity.getType());
                    break;
                case "time_turner":
                    item = new TimeTurnerInv(entity.getType());
                    break;
                default:
                    break;
                }
                if (item != null) {
                    this.getInventory().addInventory(item);
                    playingDungeon.removeEntityFromDungeon(entity);
                    return;
                }
            }

        }
    }

    /**
     * @return double
     */
    @Override
    public double getHealth() {
        return health;
    }

    /**
     * @return double
     */
    @Override
    public double getAttack() {
        return attack;
    }

    /**
     * @return OffensiveWeapon
     */
    @Override
    public OffensiveWeapon getOffensiveWeapon() {
        List<Item> potentialWeapons = inventory.getInventory();
        List<Item> itemsToRemove = new ArrayList<>();
        OffensiveWeapon targetWeapon = null;
        for (Item weapon : potentialWeapons) {
            if (weapon instanceof OffensiveWeapon) {
                targetWeapon = (OffensiveWeapon) weapon;
                targetWeapon.reduceDurability();
                if (targetWeapon.getDurability() == 0) {
                    itemsToRemove.add(weapon);
                }
                break;
            }
        }
        removeDestroyedItems(itemsToRemove);
        return targetWeapon;
    }

    /**
     * @param newHealth
     * @param weakness
     */
    @Override
    public void setHealthAfterDamage(double newHealth, Boolean weakness) {

        health = newHealth;

    }

    /**
     * @param weakness
     * @return OffensiveWeapon
     */
    @Override
    public OffensiveWeapon getOffensiveWeapon(String weakness) {
        List<Item> potentialWeapons = inventory.getInventory();
        List<Item> itemsToRemove = new ArrayList<>();
        OffensiveWeapon targetWeapon = null;
        for (Item weapon : potentialWeapons) {
            if (weapon instanceof OffensiveWeapon) {
                if (weapon.getType().equals(weakness)) {
                    targetWeapon = (OffensiveWeapon) weapon;
                    targetWeapon.reduceDurability();
                    if (targetWeapon.getDurability() == 0) {
                        itemsToRemove.add(weapon);
                    }
                    break;
                }
            }
        }
        removeDestroyedItems(itemsToRemove);
        return targetWeapon;
    }

    /**
     * @return double
     */
    @Override
    public double getDefenceReduction() {
        List<Item> potentialArmour = inventory.getInventory();
        List<Item> itemsToRemove = new ArrayList<>();
        double damageDeduction = 0;
        for (Item item : potentialArmour) {
            if (item instanceof DefensiveWeapon) {
                DefensiveWeapon armour = (DefensiveWeapon) item;
                damageDeduction += armour.getDamageReduction();
                armour.reduceDurability();
                if (armour.getDurability() == 0) {
                    itemsToRemove.add(item);
                }
            }
        }
        removeDestroyedItems(itemsToRemove);
        return damageDeduction;
    }

    /**
     * @return String
     */
    @Override
    public String getWeaponWeakness() {
        return null;
    }

    /**
     * Remove items after durability is finished
     * 
     * @param itemsToRemove
     */
    private void removeDestroyedItems(List<Item> itemsToRemove) {
        List<Item> playerInventory = inventory.getInventory();
        for (Item item : itemsToRemove) {
            playerInventory.remove(item);
        }
    }

    /**
     * @return double
     */
    @Override
    public double getArmourAttack() {
        List<Item> potentialArmour = inventory.getInventory();
        double attackIncrease = 0;
        for (Item item : potentialArmour) {
            if (item instanceof DefensiveWeapon) {
                DefensiveWeapon armour = (DefensiveWeapon) item;
                attackIncrease += armour.getArmourAttackIncrease();
            }
        }
        return attackIncrease;
    }

    /**
     * Add an ally to the player
     * 
     * @param ally
     */
    public void addAlly(Entity ally) {
        if (ally instanceof BattleInterface) {
            allies.add(ally);
        }
    }

    /**
     * Check if the entity is an ally of player
     * 
     * @param entity
     * @return boolean
     */
    public boolean isAnAlly(Entity entity) {
        if (allies.contains(entity)) {
            return true;
        }
        return false;
    }

    /**
     * Get all the player's allies
     * 
     * @return List<Entity>
     */
    public List<Entity> getAllies() {
        return allies;
    }

    /**
     * Check to see if player is in battle or not
     * 
     * @return Boolean
     */
    public Boolean getInBattle() {
        return inBattle;
    }

    /**
     * Sets whether the player is in battle or not
     * 
     * @param inBattle
     */
    public void setInBattle(Boolean inBattle) {
        this.inBattle = inBattle;
    }

    /**
     * Reduce the allyTick for all mind controlled mercenaries
     * Remove from allies list if tick is 0
     */
    public void checkAllies() {
        for (Entity entity : this.getAllies()) {
            Mercenary mercenary = (Mercenary)entity;
            if (mercenary.getAllyTick() > 0) {
                mercenary.setAllyTick(mercenary.getAllyTick() - 1);
            } else if (mercenary.getAllyTick() == 0) {
                mercenary.setAlly(false);
            }
        }
        Iterator<Entity> allies = this.getAllies().iterator();
        while (allies.hasNext()) {
            Entity entity = allies.next();
            if (!(((Mercenary)entity).getAlly())) {
                allies.remove();
                break;
            }
        }

    }
}
