package dungeonmania.entity;

import java.util.Arrays;
import java.util.List;

import dungeonmania.util.Direction;
import dungeonmania.util.Position;

/**
 * Boulder Subclass that extends entity and takes majority of its methods from
 * the Entity class
 */
public class Boulder extends Entity {
    /**
     * Boulder class that inherits Entity super class
     * @param id
     * @param type
     * @param position
     * @param isInteractable
     * @param isCollidable
    */
    public Boulder(int id, String type, Position position, boolean isInteractable, boolean isCollidable) {
        super(id, type, position, isInteractable, isCollidable);
    }

    /**
     * 
     * Method is used to check if boulder movement is viable and checks that no
     * other entities are blocking the movement of the boulder
     * 
     * @param direction   Takes in player movement direction and is used to mimic
     *                    the same directional movement for boulders under the
     *                    conidtion that a player is adjacent to the boulder and is
     *                    moving towards the boulder
     * @param allEntities List of all entities currently in dungeon, used to iterate
     *                    through list and cross check with conditions for boulder
     *                    movement
     * @return Boolean statement that states when the method is called on whether or
     *         not the boulder movement is valid and can be continued
     */
    
    @Override
    public boolean canMove(Direction direction, List<Entity> allEntities) {
        Position changeInPosition = direction.getOffset();
        Position newBoulderPosition = super.getPosition().translateBy(changeInPosition);

        List<String> entitiesBlockBoulder = Arrays.asList("wall", "portal", "door", "zombie_toast_spawner", "boulder");

        for (Entity entity : allEntities) {
            String entityType = entity.getType();
            Position entityPosition = entity.getPosition();
            /**
            * If the boulder to be moved overlaps with either a wall, portal, door,
            * zombie toast spawner and another boulder itself, then the boulder cannot move (return false)
            */
            if (entityPosition.equals(newBoulderPosition) && (entitiesBlockBoulder.contains(entityType))) {
                return false;
            }
        }
        /**
         * If no interference with the entities in the dungeon, then it can move (return true)
         */
        return true;
    }

}
