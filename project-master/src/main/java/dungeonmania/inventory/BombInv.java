package dungeonmania.inventory;

import dungeonmania.dungeon.Dungeon;
import dungeonmania.entity.*;

public class BombInv extends Item {

    /**
     * BombInv class that inherits Item super class
     * 
     * @param type the type of Item
     */
    public BombInv(String type) {
        super(type);
    }

    /**
     * Place bomb from invenotry onto a tile in dungeon (Cannot pick up bomb once
     * placed)
     * 
     * @param playingDungeon
     */
    public void place(Dungeon playingDungeon) {
        Player player = playingDungeon.getPlayer();
        player.getInventory().removeInventory(this);
        Entity entity = new Bomb(playingDungeon.getEntityCount() + 1, "unlit_bomb", player.getPosition(), false, false);
        playingDungeon.getEntities().add(entity);
    }

}
