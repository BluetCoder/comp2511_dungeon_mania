package dungeonmania.inventory;

import dungeonmania.entity.Player;

public class HealthPotionInv extends Item implements PotionInterface {

    /**
     * HealthPotionInv class that inherits Item super class
     * 
     * @param type the type of Item
     */
    public HealthPotionInv(String type) {
        super(type);
    }

    @Override
    /**
     * Use the health potion, restore to full health If already full health don't
     * use
     */
    public void drink(Player player) {
        if (player.getHealth() != 4000 && !player.getGameMode().equals("Hard")) {
            player.setHealth(4000);
            player.getInventory().removeInventory(this);
        } else if (player.getHealth() != 2500 && player.getGameMode().equals("Hard")) {
            player.setHealth(2500);
            player.getInventory().removeInventory(this);
        }
    }
}
