package dungeonmania.inventory;

import dungeonmania.entity.Player;

public interface BuildInterface {
    /**
     * For specific buildable entities, method allows them to use items from
     * inventory to craft offensive/defensive equipment, removes item from inventory
     * unless stated otherwise
     * 
     * @param player
     */
    public void build(Player player);
}
