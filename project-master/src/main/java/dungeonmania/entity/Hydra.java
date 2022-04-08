package dungeonmania.entity;

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import static java.util.Map.entry;

import java.util.Random;

import dungeonmania.inventory.*;
import dungeonmania.util.Direction;
import dungeonmania.util.Position;

public class Hydra extends Entity implements EnemyObserver, BattleInterface {
    private List<Entity> allEntities;
    private List<SwampTile> allSwampTiles;

    private double enemyHealth;
    private double enemyAttack = 1;
    private int movementCount;

    /**
     * Hydra class that inherits entity super class
     * @param id
     * @param type
     * @param position
     * @param isInteractable
     * @param isCollidable
     * @param allEntities
     * @param allSwampTiles
     */
    public Hydra(int id, String type, Position position, boolean isInteractable, boolean isCollidable, List<Entity> allEntities, List<SwampTile> allSwampTiles) {
        super(id, type, position, isInteractable, isCollidable);
        this.allEntities = allEntities;
        this.allSwampTiles = allSwampTiles;
        this.enemyHealth = 2000;
        this.movementCount = 1;
    }

    /**
     * Movement Count refers to the movement factor from swamp tile
     * @return
     */
    public int getMovementCount() {
        return movementCount;
    }

    /**
     * Set movement count to increase to approach the swamp tile factor or reset count for next entity
     * @param ticks
     */
    public void setMovementCount(int ticks) {
        this.movementCount = ticks;
    }


    /**
     * Random Movement
     */
    @Override
    public void updateEnemyMovement() {
        Random random = new Random();
        int randomNumber = random.nextInt(4 - 1 + 1) + 1;

        Map<Integer, Direction> randomDirections = Map.ofEntries(entry(1, Direction.UP), entry(2, Direction.DOWN),
                entry(3, Direction.LEFT), entry(4, Direction.RIGHT));

        Direction randomHydraDirection = randomDirections.get(randomNumber);

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

        if (super.canMove(randomHydraDirection, allEntities)) {
            super.moveTo(randomHydraDirection);
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
    public void setHealthAfterDamage(double newHealth, Boolean weakness) {
        Random random = new Random();
		int value = random.nextInt(2 + 1) + 1;
        
        if (weakness || (!weakness && value != 1)) {
            enemyHealth = newHealth;
        } else {
            enemyHealth += 2000;
        }
        
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
        return "anduril";
    }

    @Override
    public double getArmourAttack() {
        return 0;
    }

    @Override
    public List<Item> drop() {
        List<Item> loot = new ArrayList<Item>();
        Random random = new Random();
        if (random.nextInt(100) < 5) {
            OneRing oneRing = new OneRing("one_ring");
            loot.add(oneRing);
        }

        Random random2 = new Random();
        if (random2.nextInt(100) < 10) {
            Anduril anduril = new Anduril("anduril");
            loot.add(anduril);
        }

        return loot;
    }

    @Override
    public void updateEnemyMovementInvincible(Player player) {
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

}
