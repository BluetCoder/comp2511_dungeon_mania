package dungeonmania.entity;

import dungeonmania.util.Position;

public class Exit extends Entity {
    /**
     * Exit class that inherits Entity super class
     * 
     * @param id
     * @param type
     * @param position
     * @param isInteractable
     * @param isCollidable
     */
    public Exit(int id, String type, Position position, boolean isInteractable, boolean isCollidable) {
        super(id, type, position, isInteractable, isCollidable);
    }
}
