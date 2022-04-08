package dungeonmania.inventory;

import dungeonmania.entity.Player;

public interface PotionInterface {
    /**
     * Method that allows players to consume potions and remove item from inventory
     * 
     * @param player
     */
    public void drink(Player player);
}
