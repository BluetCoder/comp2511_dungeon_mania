package dungeonmania.entity;

import java.util.List;
import java.util.ArrayList;
import java.util.Random;

import dungeonmania.inventory.*;
import dungeonmania.util.Position;
import java.util.Map;
import java.util.Queue;

import java.util.HashMap;
import java.util.LinkedList;

import dungeonmania.dungeon.Dungeon;
import dungeonmania.inventory.OffensiveWeapon;
import dungeonmania.util.Direction;

public class Mercenary extends Entity implements EnemyObserver, BattleInterface, InteractableInterface{
    private List<Entity> allEntities;
    private List<SwampTile> allSwampTiles;
    private double enemyHealth;
    private double enemyAttack = 1.5;
    private int movementCount;
    private int width;
    private int height;
    private Player player;
    private boolean ally = false;
    
    // The tick that the Mercenary will remain your ally. -1 if bribed for indenfinite ally
    private int allyTick = 0;
    private ArmourInv hasArmour;

    /**
     * Mercenary class class that inherits Entity super class
     * 
     * @param type
     * @param position
     * @param isInteractable
     * @param isCollidable
     * @param allSwampTiles
     */
    public Mercenary(int id, String type, Position position, boolean isInteractable, boolean isCollidable) {
        super(id, type, position, isInteractable, isCollidable);
        this.hasArmour = this.setHasArmour();
        this.enemyHealth = 1500;
        this.movementCount = 1;
    }

    /**
     * Setter for has armour
     * 
     * @return ArmourInv
     */
    public ArmourInv setHasArmour() {
        if (this.dropRateArmour() && super.getType().equals("mercenary")) {
            ArmourInv armour = new ArmourInv("armour");
            return armour;
        }
        return null;
    }

    /**
     * Sets list of swamp tile
     * 
     * @param allSwampTiles
     */
    public void setAllSwampTiles(List<SwampTile> allSwampTiles) {
        this.allSwampTiles = allSwampTiles;
    }

    /**
     * Sets width of map
     * 
     * @param width
     */
    public void setWidth(int width) {
        this.width = width;
    }

    /**
     * Sets height of map
     * 
     * @param width
     */
    public void setHeight(int height) {
        this.height = height;
    }

    /**
     * Sets all entities list
     * 
     * @param allEntities
     */
    public void setAllEntities(List<Entity> allEntities) {
        this.allEntities = allEntities;
    }

    /**
     * Sets Player
     * 
     * @param player
     */
    public void setPlayer(Player player) {
        this.player = player;
    }

    /**
     * Gets Movement Count
     * 
     * @return int
     */
    public int getMovementCount() {
        return movementCount;
    }

    /**
     * Getter for allyTick
     * @return
     */
    public int getAllyTick() {
        return allyTick;
    }

    /**
     * Setter for allyTick
     * @param allyTick
     */
    public void setAllyTick(int allyTick) {
        this.allyTick = allyTick;
    }

    /** Sets Movement Count
     * 
     * @param ticks
     */
    public void setMovementCount(int ticks) {
        this.movementCount = ticks;
    }

    @Override
    public void updateEnemyMovement() {
        Map<Position, Position> prev = Dijkstras();
        List<Position> battleRadius = super.getPosition().getAdjacentPositions();
        Position destination = player.getPosition();
        Boolean playerInBattle = player.getInBattle();
        Position nextPosition = interpretDijkstras(destination, prev);
        Boolean noMovement = swampTileNoMovement();

        if (noMovement == false) {
            if (nextPosition != null) {
                if (nextPosition.equals(player.getPosition()) && ally == true) {
                } else {
                    super.setPosition(nextPosition);
                }
            }
        }

        // Second mercenary movement when player in battle and inside mercenary battle
        // radius
        if (playerInBattle && battleRadius.contains(player.getPosition())) {
            noMovement = swampTileNoMovement();
            if (noMovement == false) {
                if (nextPosition != null) {
                    if (nextPosition.equals(player.getPosition()) && ally == true) {
                    } else {
                        super.setPosition(player.getPosition());
                    }
                }
            }
        }
    }

    @Override
    public double getHealth() {
        return enemyHealth;
    }

    @Override
    public double getAttack() {
        return enemyAttack;
    }

    @Override
    public OffensiveWeapon getOffensiveWeapon() {
        return null;
    }

    @Override
    public List<Item> drop(String gamemode) {
        List<Item> loot = new ArrayList<Item>();
        if (this.getHasArmour() != null) {
            loot.add(this.getHasArmour());
        }

        Random random = new Random();
        if (random.nextInt(1000) < 20) {
            OneRing oneRing = new OneRing("one_ring");
            loot.add(oneRing);
        }

        Random random2 = new Random();
        if (gamemode.equals("Hard")) {
            if (random2.nextInt(1000) < 50) {
                Anduril anduril = new Anduril("anduril");
                loot.add(anduril);
            }
        } else {
            if (random2.nextInt(1000) < 20) {
                Anduril anduril = new Anduril("anduril");
                loot.add(anduril);
            }
        }
        return loot;
    }

    /**
     * Returns true if the mercenary has armour
     * 
     * @return boolean
     */
    public boolean dropRateArmour() {
        Random random = new Random();
        if (random.nextInt(1000) < 100) {
            return true;
        }
        return false;
    }

    /**
     * Getter for has.armour
     * 
     * @return
     */
    public ArmourInv getHasArmour() {
        return this.hasArmour;
    }

    @Override
    public void setHealthAfterDamage(double newHealth, Boolean weakness) {
        enemyHealth = newHealth;
    }

    @Override
    public OffensiveWeapon getOffensiveWeapon(String weakness) {
        return null;
    }

    @Override
    public double getDefenceReduction() {
        return 0;
    }

    @Override
    public String getWeaponWeakness() {
        return null;
    }

    @Override
    public double getArmourAttack() {
        return 0;
    }

    /**
     * Implements Dijkstras algorithm
     * 
     * @return Map<Position, Position>
     */
    public Map<Position, Position> Dijkstras() {
        Map<Position, Double> dist = new HashMap<>();
        Map<Position, Position> prev = new HashMap<>();
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                Position position = new Position(j, i);
                dist.put(position, Double.POSITIVE_INFINITY);
                prev.put(position, null);
            }
        }
        Position source = super.getPosition();
        dist.put(source, 0.0);
        Queue<Position> q = new LinkedList();

        q.add(source);
        while (q.isEmpty() == false) {
            Position u = q.peek();
            List<Position> adjacentPositions = u.getAdjacentPositions();
            for (int i = 0; i < adjacentPositions.size(); i++) {
                Position v = adjacentPositions.get(i);
                if (i % 2 != 0) {
                    if (invalidPosition(adjacentPositions.get(i)) == false) {
                        double totalCost = dist.get(u) + 1.0 + swampTilePositionMatch(v);
                        if (totalCost < dist.get(v)) {
                            dist.put(v, dist.get(u) + 1.0 + swampTilePositionMatch(v));
                            prev.put(v, u);
                            q.add(v);
                        }
                    }
                }
            }
            q.remove(u);
        }
        return prev;

    }

    /**
     * Reads Dijkstras algorithm and returns the next position that the mercenary
     * should take
     * 
     * @param destination
     * @param prev
     * @return Position
     */
    private Position interpretDijkstras(Position destination, Map<Position, Position> prev) {
        Position source = super.getPosition();
        if (prev.get(destination) == null) {
            return null;
        } else if (prev.get(destination) == source) {
            return destination;
        }
        Position nextPosition = prev.get(destination);
        while (true) {
            if (prev.get(nextPosition).equals(source)) {
                break;
            } else {
                nextPosition = prev.get(nextPosition);
            }
        }
        return nextPosition;
    }

    /**
     * Checks if the mercenary can move into the Position
     * 
     * @param position
     * @return Boolean
     */
    private Boolean invalidPosition(Position position) {
        for (Entity entity : allEntities) {
            if (entity.getPosition().equals(position)) {
                if (entity.isCollidable()) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Checks if the position matches with a swamp tile and returns the swamp tile's
     * movement factor if match
     * 
     * @param position
     * @return
     */
    private double swampTilePositionMatch(Position position) {
        for (SwampTile tile : allSwampTiles) {
            if (tile.getPosition().equals(position)) {
                return tile.getMovementFactor();
            }
        }
        return 0;
    }

    /**
     * Getter for ally boolean
     * @return boolean
     */
    public boolean getAlly() {
        return ally;
    }

    /**
     * Setter for ally boolean
     * @param ally
     */
    public void setAlly(boolean ally) {
        this.ally = ally;
    }

    @Override
    public void interactAction(Player player, Dungeon playingDungeon) {
        if (player.getPosition().getAdjacentPositions().contains(super.getPosition())
                && player.getInventory().itemCount("treasure") > 0) {
            player.getInventory().removeInventory(player.getInventory().itemGetter("treasure"));
            player.addAlly(this);
            allyTick = -1;
            setAlly(true);
        }
    }

    /**
     * Mind control mercenaries through the sceptre
     * Adds the mercenary to allies list for 10 ticks
     */
    public void mindControl(Player player, Dungeon playingDungeon) {
        player.addAlly(this);
        setAlly(true);
        allyTick = 10;
    }

    /** Sets the mercenary isAlly to true if mercenary is an ally
     * 
     * @param isAlly
     */
    public void setAlly(Boolean isAlly) {
        this.ally = isAlly;
    }

    @Override
    public void updateEnemyMovementInvincible(Player player) {
        if (this.ally) {
            updateEnemyMovement();
        }
        Position distance = Position.calculatePositionBetween(this.getPosition(), player.getPosition());
        Map<Integer, Direction> randomDirections = new HashMap<Integer, Direction>();
        int i = 1;
        if (distance.getX() > 0) {
            randomDirections.put(i, Direction.LEFT);
            i++;
        } else if (distance.getX() < 0) {
            randomDirections.put(i, Direction.RIGHT);
            i++;
        } else if (distance.getX() == 0) {
            randomDirections.put(i, Direction.RIGHT);
            i++;
            randomDirections.put(i, Direction.LEFT);
            i++;
        }

        if (distance.getY() > 0) {
            randomDirections.put(i, Direction.UP);
            i++;
        } else if (distance.getY() < 0) {
            randomDirections.put(i, Direction.DOWN);
            i++;
        } else if (distance.getY() == 0) {
            randomDirections.put(i, Direction.UP);
            i++;
            randomDirections.put(i, Direction.DOWN);
            i++;
        }

        Random random = new Random();
        int randomNumber = random.nextInt(i - 1) + 1;

        Direction randomZombieDirection = randomDirections.get(randomNumber);

        for (SwampTile tile : allSwampTiles) {
            int count = tile.getMovementFactor();

            if (tile.getPosition().equals(super.getPosition()) && (movementCount != count)) {
                setMovementCount(movementCount + 1);
                return;
            } else if (tile.getPosition().equals(super.getPosition()) && (movementCount == count)) {
                setMovementCount(1);
                break;
            }
        }

        if (super.canMove(randomZombieDirection, allEntities)) {
            super.moveTo(randomZombieDirection);
        }
    }

    /**
     * Checks if the Mercenary can move if in swamp tile
     * 
     * @return Boolean
     */
    public Boolean swampTileNoMovement() {
        for (SwampTile tile : allSwampTiles) {
            int count = tile.getMovementFactor();
            if (tile.getPosition().equals(super.getPosition()) && (movementCount != count)) {
                setMovementCount(movementCount + 1);
                return true;
            } else if (tile.getPosition().equals(super.getPosition()) && (movementCount == count)) {
                setMovementCount(1);
                break;
            }
        }
        return false;
    }
}
