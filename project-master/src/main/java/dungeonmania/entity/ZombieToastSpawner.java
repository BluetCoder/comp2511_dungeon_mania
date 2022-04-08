package dungeonmania.entity;

import java.util.ArrayList;
import java.util.List;

import dungeonmania.dungeon.Dungeon;
import dungeonmania.util.Direction;
import dungeonmania.util.Position;

/**
 * Subclass that extends from entity, also implements interface allowing us to
 * attach zombies movemennt when zombies are spanwed into the game
 */
public class ZombieToastSpawner extends Entity implements InteractableInterface {
    ArrayList<EnemyObserver> enemies = new ArrayList<EnemyObserver>();

    /**
     * ZombieToastSpawner class that inherits Entity super class
     * 
     * @param id
     * @param type
     * @param position
     * @param isInteractable
     * @param isCollidable
     */
    public ZombieToastSpawner(int id, String type, Position position, boolean isInteractable, boolean isCollidable) {
        super(id, type, position, isInteractable, isCollidable);
    }

    /**
     * This method when called checks that under the right conditions, a zombie will
     * spawn cardinally adjacent to the spawner
     * 
     * @param allEntities   List of all entities currently occuping a postion in the
     *                      playing dungeon
     * @param player        Player entity required to attach Zombie toast movement
     *                      fro when zombie toast is spawned in
     * @param id
     * @param allSwampTiles
     */
    public void canSpawnZombie(List<Entity> allEntities, Player player, int id, List<SwampTile> allSwampTiles,
            Dungeon dungeon) {
        Position spawnerPosition = this.getPosition();

        Position up = spawnerPosition.translateBy(Direction.UP.getOffset());
        Position down = spawnerPosition.translateBy(Direction.DOWN.getOffset());
        Position left = spawnerPosition.translateBy(Direction.LEFT.getOffset());
        Position right = spawnerPosition.translateBy(Direction.RIGHT.getOffset());

        List<Position> allPossiblePositions = new ArrayList<Position>();
        allPossiblePositions.add(up);
        allPossiblePositions.add(down);
        allPossiblePositions.add(left);
        allPossiblePositions.add(right);

        for (Entity newEntity : allEntities) {
            Position entityPosition = newEntity.getPosition();

            if (allPossiblePositions.contains(entityPosition)) {
                allPossiblePositions.remove(entityPosition);
            }
        }

        if (!allPossiblePositions.isEmpty()) {
            Position availableZombiePosition = allPossiblePositions.get(0);
            ZombieToast zombie = new ZombieToast(id, "zombie_toast", availableZombiePosition, true, false, allEntities,
                    allSwampTiles);
            dungeon.addEntityToDungeon(zombie);

            player.attach(zombie);
        }

    }

    @Override
    public void interactAction(Player player, Dungeon playingDungeon) {
        if (player.getInventory().itemExists("sword")
                && Position.isAdjacent(super.getPosition(), player.getPosition())) {
            playingDungeon.removeEntityFromDungeon(this);
        }
    }

}
