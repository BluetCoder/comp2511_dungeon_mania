package dungeonmania.inventory;

import dungeonmania.entity.Player;

public class InvincibilityPotionInv extends Item implements PotionInterface {

    /**
     * InvinciblityPotionInv class that inherits Item super class
     * 
     * @param type the type of Item
     */
    public InvincibilityPotionInv(String type) {
        super(type);
    }

    @Override
    public void drink(Player player) {
        player.getPlayerState().toInvincible(player);
        player.getInventory().removeInventory(this);
    }
}