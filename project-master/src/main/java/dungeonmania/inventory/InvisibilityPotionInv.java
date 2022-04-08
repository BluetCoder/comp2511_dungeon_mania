package dungeonmania.inventory;

import dungeonmania.entity.Player;

public class InvisibilityPotionInv extends Item implements PotionInterface {

    /**
     * InvisibilityPotionInv class that inherits Item super class
     * 
     * @param type the type of Item
     */
    public InvisibilityPotionInv(String type) {
        super(type);
    }

    @Override
    public void drink(Player player) {
        player.getPlayerState().toInvincible(player);
        player.getInventory().removeInventory(this);
    }
}