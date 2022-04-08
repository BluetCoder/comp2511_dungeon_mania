package dungeonmania.entity;

import dungeonmania.util.Position;

public class Key extends Entity {
    private int keyNum;
    /**
     * Key class that inherits Entity super class
     * 
     * @param id
     * @param type
     * @param position
     * @param isInteractable
     * @param isCollidable
     */
    public Key(int id, String type, Position position, boolean isInteractable, boolean isCollidable, int keyNum) {
        super(id, type, position, isInteractable, isCollidable);
        this.keyNum = keyNum;
    }

    public int getKeyNum() {
        return keyNum;
    }

}
