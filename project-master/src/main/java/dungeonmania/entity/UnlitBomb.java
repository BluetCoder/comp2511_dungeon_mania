package dungeonmania.entity;

import dungeonmania.util.Position;

public class UnlitBomb extends Entity {
    /**
     * Bomb class that inherits Entity super class
     * 
     * @param id
     * @param type
     * @param position
     * @param isInteractable
     * @param isCollidable
     */
    public UnlitBomb(int id, String type, Position position, boolean isInteractable, boolean isCollidable) {
        super(id, type, position, isInteractable, isCollidable);
    }
}
