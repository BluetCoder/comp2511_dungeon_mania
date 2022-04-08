package dungeonmania.entity;

import dungeonmania.dungeon.Dungeon;

public interface InteractableInterface {
    /**
     * Method that triggers interact action in dungeon controller if enttity is
     * interactable
     * 
     * @param player
     * @param dungeon
     */
    public void interactAction(Player player, Dungeon dungeon);
}
