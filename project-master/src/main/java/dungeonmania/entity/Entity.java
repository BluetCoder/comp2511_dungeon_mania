package dungeonmania.entity;

import java.util.List;

import dungeonmania.inventory.Item;
import dungeonmania.util.Direction;
import dungeonmania.util.Position;

public class Entity implements DropInterface {
    private int id;
    private String type;
    private Position position;
    private boolean isInteractable;
    private boolean isCollidable;
    
    public Entity(int id, String type, Position position, boolean isInteractable, boolean isCollidable) {
        this.id = id;
        this.type = type;
        this.position = position;
        this.isInteractable = isInteractable;
        this.isCollidable = isCollidable;
    }

    
    public List<Item> drop() {
        return null;
    }

    /**
     * Get entity ID
     * @return
     */
    public int getId() {
        return id;
    }
    
    /** 
     * Returns the entity's colliding nature, where other entities can either collide
     * into it (true) or pass through it (false)
     * @return boolean
     */
    public boolean isCollidable() {
        return isCollidable;
    }

    public void setCollidable(boolean isCollidable) {
        this.isCollidable = isCollidable;
    }

    /** 
     * Returns the entity type
     * @return String
     */
    public String getType() {
        return this.type;
    }

    /** 
     * Returns the entity position
     * @return Position
     */
    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    /** 
     * Moves and sets the entity to a new position from the specified direction,
     * passed in as a parameter
     * @param direction
     */
    public void moveTo(Direction direction) {
        Position changeInPosition = direction.getOffset();
        Position newPosition = position.translateBy(changeInPosition);
        this.position = newPosition;
    }

    /** 
     * Checks whether the entity is able to move to the specified direction, passed
     * in as a parameter, amongst the other entities in the dungeon
     * @param direction
     * @param allEntities
     * @return boolean
     */
    public boolean canMove(Direction direction, List<Entity> allEntities) {
        Position changeInPosition = direction.getOffset();
        Position newPosition = position.translateBy(changeInPosition); 

        for (Entity entity: allEntities) {
            /** If the entity to be moved overlaps with another entity which is collidable
             * then the entity cannot move (return false)
            */
            if (entity.getPosition().equals(newPosition) && entity.isCollidable()) {
                return false;
            }
        }
        /**
         * If no interference with the entities in the dungeon, then it can move (return true)
         */
        return true;
    }
    
    /** 
     * Returns the entity's interacting nature, if interactable (true) and if
     * not interactable (false)
     * @return boolean
     */
    public boolean isInteractable() {
        return isInteractable;
    }

    @Override
    public List<Item> drop(String gamemode) {
        // TODO Auto-generated method stub
        return null;
    }

}
