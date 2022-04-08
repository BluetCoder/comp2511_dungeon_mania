package dungeonmania.inventory;

import dungeonmania.entity.Player;

public class OneRing extends Item {
    /**
     * KeyInv class that inherits Item super class
     * 
     * @param type the type of Item
     */

    public OneRing(String type) {
        super(type);
    }

    /**
     * Method that implements one ring ructionality allowing players to respawn with
     * full health based on game mode
     * 
     * @param player
     */
    public void revive(Player player) {
        if (player.getHealth() != 4000 && !player.getGameMode().equals("Hard")) {
            player.setHealth(4000);
            player.getInventory().removeInventory(this);
        } else if (player.getHealth() != 2500 && player.getGameMode().equals("Hard")) {
            player.setHealth(2500);
            player.getInventory().removeInventory(this);
        }
    }
}
