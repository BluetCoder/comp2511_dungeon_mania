package dungeonmania.entity;

import java.util.Arrays;
import java.util.List;

import dungeonmania.dungeon.Dungeon;
import dungeonmania.util.Position;

public class Bomb extends Entity {

    private final List<String> bombProof = Arrays.asList("player", "exit");

    /**
     * Bomb class that inherits Entity super class
     * 
     * @param id
     * @param type
     * @param position
     * @param isInteractable
     * @param isCollidable
     */
    public Bomb(int id, String type, Position position, boolean isInteractable, boolean isCollidable) {
        super(id, type, position, isInteractable, isCollidable);
    }

    /**
     * When bomb explodes removes all entities unless specified in assumptions, in a
     * 3x3 radius around the bomb
     * 
     * @param positionsRemovedByBomb
     * @param entities
     * @param dungeon
     */
    public void explode(List<Position> positionsRemovedByBomb, List<Entity> entities, Dungeon dungeon) {
        for (Position p : positionsRemovedByBomb) {
            List<Entity> entitiesToRemove = dungeon.getEntitiesByPosition(p);
            for (Entity e : entitiesToRemove) {
                if (!bombProof.contains(e.getType())) {
                    entities.remove(e);
                }
            }
        }

        entities.remove(this);
    }
}
