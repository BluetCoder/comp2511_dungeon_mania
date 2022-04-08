package dungeonmania.entity;

import dungeonmania.util.Position;

public class TimeTurner extends Entity {

    /**
     * Time Turner is an entity that extends from Entity Super Class
     * 
     * @param id
     * @param type
     * @param position
     * @param isInteractable
     * @param isCollidable
     */
    public TimeTurner(int id, String type, Position position, boolean isInteractable, boolean isCollidable) {
        super(id, type, position, isInteractable, isCollidable);
    }

}
