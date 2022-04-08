package dungeonmania.inventory;

import java.util.Iterator;
import java.util.List;

import dungeonmania.dungeon.Dungeon;
import dungeonmania.entity.Entity;
import dungeonmania.entity.Mercenary;
import dungeonmania.entity.Player;
import dungeonmania.util.*;

public class Sceptre extends Item implements BuildInterface {
    /**
     * Bow class that inherits Item super class
     * 
     * @param type the type of Item
     */

    public Sceptre(String type) {
        super(type);
    }

    @Override
    /**
     * Creates a bow and adds it to Player Inventory Then removes the components of
     * used from Inventory
     */
    public void build(Player player) {
        player.getInventory().addInventory(this);
        if (player.getInventory().itemExists("wood")) {
            Iterator<Item> woodItems = player.getInventory().getInventory().iterator();
            while (woodItems.hasNext()) {
                Item item = woodItems.next();
                if (item.getType().equals("wood")) {
                    woodItems.remove();
                    break;
                }
            }
        } else {
            int arrowCount = 2;
            Iterator<Item> arrowItems = player.getInventory().getInventory().iterator();
            while (arrowItems.hasNext() && arrowCount > 0) {
                Item item = arrowItems.next();
                if (item.getType().equals("arrow")) {
                    arrowItems.remove();
                    arrowCount--;
                }
            }
        }

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

    /**
     * Mind control mercenaries adjacent to player
     * 
     * @param player
     * @param playingDungeon
     */
    public void mindControl(Player player, Dungeon playingDungeon) {
        List<Position> adjacentPositions = player.getPosition().getAdjacentPositions();
        for (Entity entity : playingDungeon.getEntities()) {
            if (adjacentPositions.contains(entity.getPosition()) && entity instanceof Mercenary) {
                if (entity.getType().equals("mercenary")) {

                    Mercenary victim = (Mercenary) entity;
                    victim.mindControl(player, playingDungeon);
                } // else if (entity.getPosition().equals("assassin")) {
                  // Assassin victim = (Assassin)entity;
                  // victim.mindControl(player, playingDungeon);
                  // }
            }
        }
    }
}
