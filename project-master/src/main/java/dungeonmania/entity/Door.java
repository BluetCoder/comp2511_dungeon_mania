package dungeonmania.entity;

import dungeonmania.util.Position;

public class Door extends Entity {
    private int keyNum;
    /**
     * Door class that inherits Entity super class
     * 
     * @param id
     * @param type
     * @param position
     * @param isInteractable
     * @param isCollidable
     */
    public Door(int id, String type, Position position, boolean isInteractable, boolean isCollidable, int keyNum) {
        super(id, type, position, isInteractable, isCollidable);
        this.keyNum = keyNum;
    }

    public int getKeyNum() {
        return keyNum;
    }
}
