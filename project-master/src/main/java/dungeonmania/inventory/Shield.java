package dungeonmania.inventory;

import java.util.Iterator;

import dungeonmania.entity.Player;

public class Shield extends Item implements BuildInterface, DefensiveWeapon {
    int durability = 4;

    /**
     * Shield class that inherits Item super class
     * 
     * @param type the type of Item
     */
    public Shield(String type) {
        super(type);
    }

    @Override
    /**
     * Creates a shield and adds it to Player Inventory Then removes the components
     * of used from Inventory Prioritises the use of treasure over key as a
     * component
     */
    public void build(Player player) {
        player.getInventory().addInventory(this);
        Iterator<Item> woodItems = player.getInventory().getInventory().iterator();
        int woodCount = 2;
        while (woodItems.hasNext() && woodCount > 0) {
            Item item = woodItems.next();
            if (item.getType().equals("wood")) {
                woodItems.remove();
                woodCount--;
            }
        }
        if (!player.getInventory().itemExists("sun_stone")) {
            Iterator<Item> keyTreasureItems = player.getInventory().getInventory().iterator();
            while (keyTreasureItems.hasNext()) {
                Item item = keyTreasureItems.next();
                if (item.getType().equals("key") && !player.getInventory().itemExists("treasure")) {
                    keyTreasureItems.remove();
                    break;
                } else if (item.getType().equals("treasure")) {
                    keyTreasureItems.remove();
                    break;
                }
            }
        }

    }

    @Override
    public double getDamageReduction() {
        return 2;
    }

    @Override
    public int getDurability() {
        return durability;
    }

    @Override
    public void reduceDurability() {
        durability--;
    }

    @Override
    public double getArmourAttackIncrease() {
        return 0;
    }
}
