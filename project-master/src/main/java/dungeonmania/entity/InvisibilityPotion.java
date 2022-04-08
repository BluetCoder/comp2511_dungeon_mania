package dungeonmania.entity;

import dungeonmania.util.Position;

public class InvisibilityPotion extends Entity {
    /**
     * InvisibilityPotion class that inherits Entity super class
     * 
     * @param id
     * @param type
     * @param position
     * @param isInteractable
     * @param isCollidable
     */
    public InvisibilityPotion(int id, String type, Position position, boolean isInteractable, boolean isCollidable) {
        super(id, type, position, isInteractable, isCollidable);
    }
}
