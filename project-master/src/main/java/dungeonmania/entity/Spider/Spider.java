package dungeonmania.entity.Spider;

import java.util.List;
import java.util.ArrayList;
import java.util.Random;

import dungeonmania.entity.BattleInterface;
import dungeonmania.entity.Boulder;
import dungeonmania.entity.EnemyObserver;
import dungeonmania.entity.Entity;
import dungeonmania.entity.Player;
import dungeonmania.entity.SwampTile;
import dungeonmania.inventory.*;
import dungeonmania.util.Position;

public class Spider extends Entity implements EnemyObserver, BattleInterface{
    SpiderMovement topMiddle = new TopMiddleState(this);
    SpiderMovement topRight = new TopRightState(this);
    SpiderMovement topLeft = new TopLeftState(this);
    SpiderMovement middleLeft = new MiddleLeftState(this);
    SpiderMovement middleRight = new MiddleRightState(this);
    SpiderMovement bottomLeft = new BottomLeftState(this);
    SpiderMovement bottomMiddle = new BottomMiddleState(this);
    SpiderMovement bottomRight = new BottomRightState(this);
    SpiderMovement middle = new MiddleState(this);
    SpiderMovement currentPosition = middle;
    private double enemyHealth;
    private double enemyAttack = 1;
    private int movementCount;
    private List<SwampTile> allSwampTiles;
    private List<Boulder> allBoulders;
    private boolean clockwise;

    /**
     * Params mainly for entity super class
     * 
     * @param type
     * @param position
     * @param isInteractable
     * @param isCollidable
     * @param allSwampTiles
     */
    public Spider(int id, String type, Position position, boolean isInteractable, boolean isCollidable,
            List<SwampTile> allSwampTiles, List<Boulder> allBoulders) {
        super(id, type, position, isInteractable, isCollidable);
        this.enemyHealth = 1000;
        this.movementCount = 1;
        this.allSwampTiles = allSwampTiles;
        this.allBoulders = allBoulders;
        this.clockwise = true;
    }

    public int getMovementCount() {
        return movementCount;
    }

    public void setMovementCount(int ticks) {
        this.movementCount = ticks;
    }

    public boolean isClockwise() {
        return clockwise;
    }

    public void setClockwise(boolean clockwise) {
        this.clockwise = clockwise;
    }

    /**
     * Sets state to Top Right state Indicates that spider is in Top right position
     * from Middle
     */
    public void setMovementTopRight() {
        this.currentPosition = topRight;
    }

    /**
     * Sets state to Top Left state Indicates that spider is in Top Left position
     * from Middle
     */
    public void setMovementTopLeft() {
        this.currentPosition = topLeft;
    }

    /**
     * Sets state to Top Middle state Indicates that spider is in Top Middle
     * position from Middle
     */
    public void setMovementTopMiddle() {
        this.currentPosition = topMiddle;
    }

    /**
     * Sets state to Middle Left state Indicates that spider is in Middle Left
     * position from Middle
     */
    public void setMovementMiddleLeft() {
        this.currentPosition = middleLeft;
    }

    /**
     * Sets state to Middle Right state Indicates that spider is in Middle Right
     * position from Middle
     */
    public void setMovementMiddleRight() {
        this.currentPosition = middleRight;
    }

    /**
     * Sets state to Bottom Right state Indicates that spider is in Bottom Right
     * position from Middle
     */
    public void setMovementBottomRight() {
        this.currentPosition = bottomRight;
    }

    /**
     * Sets state to Bottom Left state Indicates that spider is in Bottom Left
     * position from Middle
     */
    public void setMovementBottomLeft() {
        this.currentPosition = bottomLeft;
    }

    /**
     * Sets state to Bottom Middle state Indicates that spider is in Bottom Middle
     * position from Middle
     */
    public void setMovementBottomMiddle() {
        this.currentPosition = bottomMiddle;
    }

    @Override
    public void updateEnemyMovement() {

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

        for (Boulder boulder : allBoulders) {
            Position checkForBoulder = this.getPosition().translateBy(currentPosition.getNextDirection());
            if (boulder.getPosition().equals(checkForBoulder)) {
                setClockwise(!clockwise);
            }
        }

        currentPosition.setSpiderPosition();
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
    public void setHealthAfterDamage(double newHealth, Boolean weakness) {
        enemyHealth = newHealth;

    }

    @Override
    public OffensiveWeapon getOffensiveWeapon(String weakness) {
        return null;
    }

    @Override
    public OffensiveWeapon getOffensiveWeapon() {
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

    @Override
    public List<Item> drop(String gamemode) {
        List<Item> loot = new ArrayList<Item>();
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

    @Override
    public void updateEnemyMovementInvincible(Player player) {
        updateEnemyMovement();
    }
}
