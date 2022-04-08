package dungeonmania.entity;

import dungeonmania.util.Position;

public class SwampTile extends Entity {
    private int movementFactor;

    /**
     * Swamp Tile is an enetity that inherits from Entity Super Class
     * 
     * @param id
     * @param type
     * @param position
     * @param isInteractable
     * @param isCollidable
     * @param movementFactor
     */
    public SwampTile(int id, String type, Position position, boolean isInteractable, boolean isCollidable,
            int movementFactor) {
        super(id, type, position, isInteractable, isCollidable);
        this.movementFactor = movementFactor;
    }

    /**
     * Get Movement Factor from Json file
     * 
     * @return
     */
    public int getMovementFactor() {
        return movementFactor;
    }

}
