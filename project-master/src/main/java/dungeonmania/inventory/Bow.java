package dungeonmania.inventory;

import java.util.Iterator;

import dungeonmania.entity.Player;

public class Bow extends Item implements BuildInterface, OffensiveWeapon {

    int durability = 4;
    
    /**
     * Bow class that inherits Item super class
     * 
     * @param type the type of Item
     */

    public Bow(String type) {
        super(type);
    }

    @Override
    /**
     * Creates a bow and adds it to Player Inventory Then removes the components of
     * used from Inventory
     */
    public void build(Player player) {
        player.getInventory().addInventory(this);
        Iterator<Item> woodItems = player.getInventory().getInventory().iterator();
        while (woodItems.hasNext()) {
            Item item = woodItems.next();
            if (item.getType().equals("wood")) {
                woodItems.remove();
                break;
            }
        }
        int arrowCount = 3;
        Iterator<Item> arrowItems = player.getInventory().getInventory().iterator();
        while (arrowItems.hasNext() && arrowCount > 0) {
            Item item = arrowItems.next();
            if (item.getType().equals("arrow")) {
                arrowItems.remove();
                arrowCount--;
            }
        }
    }

    @Override
    public double getAttackModifier() {
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
}
