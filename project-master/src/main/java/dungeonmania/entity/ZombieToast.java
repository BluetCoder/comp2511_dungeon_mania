package dungeonmania.entity;

import java.util.List;
import java.util.Map;
import static java.util.Map.entry;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import dungeonmania.inventory.*;
import dungeonmania.util.Direction;
import dungeonmania.util.Position;

/**
 * Subclass that extends from entity, also implements two inetrfaces allowing
 * zombies to do battle and act as an enemy
 */
public class ZombieToast extends Entity implements EnemyObserver, BattleInterface {
    private List<Entity> allEntities;
    private List<SwampTile> allSwampTiles;

    private double enemyHealth;
    private double enemyAttack = 1;
    private int movementCount;
    private ArmourInv hasArmour;

    /**
     * ZombieToast class that inherits Entity super class
     * 
     * @param type
     * @param position
     * @param isInteractable
     * @param isCollidable
     * @param allSwampTile
     */
    public ZombieToast(int id, String type, Position position, boolean isInteractable, boolean isCollidable,
            List<Entity> allEntities, List<SwampTile> allSwampTiles) {
        super(id, type, position, isInteractable, isCollidable);
        this.allEntities = allEntities;
        this.allSwampTiles = allSwampTiles;
        this.enemyHealth = 1000;
        this.movementCount = 1;
        this.hasArmour = this.setHasArmour();
    }

    /**
     * Retunrs whether or not zombie toast spawns with armour stats
     * 
     * @return
     */
    public ArmourInv setHasArmour() {
        if (this.dropRateArmour()) {
            ArmourInv armour = new ArmourInv("armour");
            return armour;
        }
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
     * Chance of zombie spawning with armour under the right conditions, returns
     * boolean
     * 
     * @return
     */
    public boolean dropRateArmour() {
        Random random = new Random();
        return (random.nextInt(1000) < 100);
    }

    /**
     * Get armour from zombie
     * 
     * @return
     */
    public ArmourInv getHasArmour() {
        return this.hasArmour;
    }

    /**
     * Get Movement count for swamp tile
     * 
     * @return
     */
    public int getMovementCount() {
        return movementCount;
    }

    /**
     * Set movement count for swamp tile
     * 
     * @param ticks
     */
    public void setMovementCount(int ticks) {
        this.movementCount = ticks;
    }

    @Override
    public void updateEnemyMovement() {
        Random random = new Random();
        int randomNumber = random.nextInt(4 - 1 + 1) + 1;

        Map<Integer, Direction> randomDirections = Map.ofEntries(entry(1, Direction.UP), entry(2, Direction.DOWN),
                entry(3, Direction.LEFT), entry(4, Direction.RIGHT));

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
        enemyHealth = newHealth;
    }

    @Override
    public OffensiveWeapon getOffensiveWeapon(String weakness) {
        return null;
    }

    @Override
    public double getDefenceReduction() {
        if (this.getHasArmour() != null) {
            return this.getHasArmour().getDamageReduction();
        }
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
