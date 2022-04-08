package dungeonmania.entity;

import dungeonmania.util.Position;

public class TheOneRing extends Entity {
    /**
     * TheOneRing class that inherits Entity super class
     * 
     * @param type
     * @param position
     * @param isInteractable
     * @param isCollidable
     * @param id
     */
    public TheOneRing(int id, String type, Position position, boolean isInteractable, boolean isCollidable) {
        super(id, type, position, isInteractable, isCollidable);
    }
}
