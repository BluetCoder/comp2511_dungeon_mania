package dungeonmania.entity;

import java.util.Arrays;
import java.util.List;

import dungeonmania.dungeon.Dungeon;
import dungeonmania.util.Direction;
import dungeonmania.util.Position;

public class Portal extends Entity {
    private String colour;

    /**
     * Portal class that inherits Entity super class
     * 
     * @param id
     * @param type
     * @param position
     * @param isInteractable
     * @param isCollidable
     */
    public Portal(int id, String type, Position position, boolean isInteractable, boolean isCollidable, String colour) {
        super(id, type, position, isInteractable, isCollidable);
        this.colour = colour;
    }

    /**
     * Get colour of portal, portal pairs are based on colour matches
     * 
     * @return
     */
    public String getColour() {
        return colour;
    }

    /**
     * Check portal positon in reference to player's position
     * 
     * @param player
     * @return
     */
    public boolean checkPortalPosition(Player player) {
        if (player.getPosition().equals(super.getPosition())) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Teleports player to corresponding portal based on player movement direction
     * 
     * @param movementDirection
     * @return
     */
    public Position teleportPlayer(Direction movementDirection) {
        Position newPosition = super.getPosition().translateBy(movementDirection.getOffset());
        return newPosition;
    }

    public boolean checkBlockingEntities(Position newPlayerPostion, Dungeon playingDungeon) {
        List<String> entitiesBlockPortal = Arrays.asList("wall", "door", "zombie_toast_spawner", "boulder");

        for (Entity entity : playingDungeon.getEntities()) {
            String entityType = entity.getType();
            Position entityPosition = entity.getPosition();
            if (entityPosition.equals(newPlayerPostion) && (entitiesBlockPortal.contains(entityType))) {
                return false;
            }
        }
        return true;
    }
}
