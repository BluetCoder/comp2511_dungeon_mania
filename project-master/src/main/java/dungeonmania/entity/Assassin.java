package dungeonmania.entity;

import dungeonmania.dungeon.Dungeon;
import dungeonmania.util.Position;

public class Assassin extends Mercenary {
    private double enemyAttack = 2.5;

    /**
     * Constructor for Assassin
     * 
     * @param id
     * @param type
     * @param position
     * @param isInteractable
     * @param isCollidable
     */
    public Assassin(int id, String type, Position position, boolean isInteractable, boolean isCollidable) {
        super(id, type, position, isInteractable, isCollidable);
        super.setHealthAfterDamage(2500, null);
    }

    @Override
    public double getAttack() {
        return enemyAttack;
    }

    @Override
    public void interactAction(Player player, Dungeon playingDungeon) {
        if (player.getPosition().getAdjacentPositions().contains(super.getPosition())) {
            if (player.getInventory().itemCount("treasure") > 0) {
                player.getInventory().removeInventory(player.getInventory().itemGetter("treasure"));
                player.addAlly(this);
                super.setAlly(true);
            } else if (player.getInventory().itemCount("one_ring") > 0) {
                player.getInventory().removeInventory(player.getInventory().itemGetter("one_ring"));
                player.addAlly(this);
                super.setAlly(true);
            }
        }
    }

    @Override
    public String getWeaponWeakness() {
        return "anduril";
    }
}
