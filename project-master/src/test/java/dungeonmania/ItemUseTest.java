package dungeonmania;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.io.IOException;
import org.junit.jupiter.api.Test;

import dungeonmania.entity.Player;
import dungeonmania.response.models.DungeonResponse;
import dungeonmania.response.models.ItemResponse;
import dungeonmania.util.Direction;

public class ItemUseTest {
    @Test
    public void HealthPotionTest() {
        DungeonManiaController controller = new DungeonManiaController();
        DungeonResponse newGame = controller.newGame("potion", "Standard");
        DungeonResponse tick = controller.tick(null, Direction.RIGHT);
        assertEquals(tick.getEntities().get(19).getPosition().getX(), 2);
        assertEquals(tick.getEntities().get(19).getPosition().getY(), 1);
        tick = controller.tick(null, Direction.RIGHT); 
        assertEquals(tick.getEntities().get(19).getPosition().getX(), 3);
        assertEquals(tick.getEntities().get(19).getPosition().getY(), 1);
        tick = controller.tick(null, Direction.RIGHT); 
        assertEquals(tick.getEntities().get(19).getPosition().getX(), 4);
        assertEquals(tick.getEntities().get(19).getPosition().getY(), 1);
        tick = controller.tick(null, Direction.RIGHT); 
        assertEquals(tick.getEntities().get(19).getPosition().getX(), 5);
        assertEquals(tick.getEntities().get(19).getPosition().getY(), 1);
        tick = controller.tick(null, Direction.RIGHT); 
        assertEquals(tick.getEntities().get(19).getPosition().getX(), 6);
        assertEquals(tick.getEntities().get(19).getPosition().getY(), 1);
        tick = controller.tick(null, Direction.RIGHT); 
        assertEquals(tick.getEntities().get(19).getPosition().getX(), 7);
        assertEquals(tick.getEntities().get(19).getPosition().getY(), 1);
        tick = controller.tick(null, Direction.RIGHT); 
        assertEquals(tick.getEntities().get(19).getPosition().getX(), 8);
        assertEquals(tick.getEntities().get(19).getPosition().getY(), 1);
        tick = controller.tick(null, Direction.RIGHT); 
        assertEquals(tick.getEntities().get(19).getPosition().getX(), 9);
        assertEquals(tick.getEntities().get(19).getPosition().getY(), 1);
        tick = controller.tick(null, Direction.LEFT); 
        assertEquals(tick.getEntities().get(19).getPosition().getX(), 8);
        assertEquals(tick.getEntities().get(19).getPosition().getY(), 1);
        tick = controller.tick(null, Direction.LEFT); 
        assertEquals(tick.getEntities().get(19).getPosition().getX(), 7);
        assertEquals(tick.getEntities().get(19).getPosition().getY(), 1);
        assertEquals(tick.getInventory().size(), 4);
        tick = controller.tick("3", null);
        assertEquals(tick.getInventory().size(), 3);
    }

    @Test
    public void InvincibilityPotionTest() {
        DungeonManiaController controller = new DungeonManiaController();
        DungeonResponse newGame = controller.newGame("potion", "Standard");
        DungeonResponse tick = controller.tick(null, Direction.RIGHT);
        assertEquals(tick.getEntities().get(19).getPosition().getX(), 2);
        assertEquals(tick.getEntities().get(19).getPosition().getY(), 1);
        tick = controller.tick(null, Direction.RIGHT); 
        assertEquals(tick.getEntities().get(19).getPosition().getX(), 3);
        assertEquals(tick.getEntities().get(19).getPosition().getY(), 1);
        tick = controller.tick(null, Direction.RIGHT); 
        assertEquals(tick.getEntities().get(19).getPosition().getX(), 4);
        assertEquals(tick.getEntities().get(19).getPosition().getY(), 1);
        tick = controller.tick(null, Direction.RIGHT); 
        assertEquals(tick.getEntities().get(19).getPosition().getX(), 5);
        assertEquals(tick.getEntities().get(19).getPosition().getY(), 1);
        tick = controller.tick(null, Direction.RIGHT); 
        assertEquals(tick.getEntities().get(19).getPosition().getX(), 6);
        assertEquals(tick.getEntities().get(19).getPosition().getY(), 1);
        tick = controller.tick(null, Direction.RIGHT); 
        assertEquals(tick.getEntities().get(19).getPosition().getX(), 7);
        assertEquals(tick.getEntities().get(19).getPosition().getY(), 1);
        tick = controller.tick(null, Direction.RIGHT); 
        assertEquals(tick.getEntities().get(19).getPosition().getX(), 8);
        assertEquals(tick.getEntities().get(19).getPosition().getY(), 1);
        tick = controller.tick(null, Direction.RIGHT); 
        assertEquals(tick.getEntities().get(19).getPosition().getX(), 9);
        assertEquals(tick.getEntities().get(19).getPosition().getY(), 1);
        assertEquals(tick.getInventory().size(), 4);
        tick = controller.tick("2", null);
        assertEquals(tick.getInventory().size(), 3);
    }

    @Test
    public void InvisibilityPotionTest() {
        DungeonManiaController controller = new DungeonManiaController();
        DungeonResponse newGame = controller.newGame("potion", "Standard");
        DungeonResponse tick = controller.tick(null, Direction.RIGHT);
        assertEquals(tick.getEntities().get(19).getPosition().getX(), 2);
        assertEquals(tick.getEntities().get(19).getPosition().getY(), 1);
        tick = controller.tick(null, Direction.RIGHT); 
        assertEquals(tick.getEntities().get(19).getPosition().getX(), 3);
        assertEquals(tick.getEntities().get(19).getPosition().getY(), 1);
        tick = controller.tick(null, Direction.RIGHT); 
        assertEquals(tick.getEntities().get(19).getPosition().getX(), 4);
        assertEquals(tick.getEntities().get(19).getPosition().getY(), 1);
        tick = controller.tick(null, Direction.RIGHT); 
        assertEquals(tick.getEntities().get(19).getPosition().getX(), 5);
        assertEquals(tick.getEntities().get(19).getPosition().getY(), 1);
        tick = controller.tick(null, Direction.RIGHT); 
        assertEquals(tick.getEntities().get(19).getPosition().getX(), 6);
        assertEquals(tick.getEntities().get(19).getPosition().getY(), 1);
        tick = controller.tick(null, Direction.RIGHT); 
        assertEquals(tick.getEntities().get(19).getPosition().getX(), 7);
        assertEquals(tick.getEntities().get(19).getPosition().getY(), 1);
        tick = controller.tick(null, Direction.RIGHT); 
        assertEquals(tick.getEntities().get(19).getPosition().getX(), 8);
        assertEquals(tick.getEntities().get(19).getPosition().getY(), 1);
        tick = controller.tick(null, Direction.RIGHT); 
        assertEquals(tick.getEntities().get(19).getPosition().getX(), 9);
        assertEquals(tick.getEntities().get(19).getPosition().getY(), 1);
        assertEquals(tick.getInventory().size(), 4);
        tick = controller.tick("1", null);
        assertEquals(tick.getInventory().size(), 3);
    }

    @Test
    public void BombTest() {
        DungeonManiaController controller = new DungeonManiaController();
        DungeonResponse newGame = controller.newGame("potion", "Standard");
        DungeonResponse tick = controller.tick(null, Direction.RIGHT);
        assertEquals(tick.getEntities().get(19).getPosition().getX(), 2);
        assertEquals(tick.getEntities().get(19).getPosition().getY(), 1);
        tick = controller.tick(null, Direction.RIGHT); 
        assertEquals(tick.getEntities().get(19).getPosition().getX(), 3);
        assertEquals(tick.getEntities().get(19).getPosition().getY(), 1);
        tick = controller.tick(null, Direction.RIGHT); 
        assertEquals(tick.getEntities().get(19).getPosition().getX(), 4);
        assertEquals(tick.getEntities().get(19).getPosition().getY(), 1);
        tick = controller.tick(null, Direction.RIGHT); 
        assertEquals(tick.getEntities().get(19).getPosition().getX(), 5);
        assertEquals(tick.getEntities().get(19).getPosition().getY(), 1);
        tick = controller.tick(null, Direction.RIGHT); 
        assertEquals(tick.getEntities().get(19).getPosition().getX(), 6);
        assertEquals(tick.getEntities().get(19).getPosition().getY(), 1);
        tick = controller.tick(null, Direction.RIGHT); 
        assertEquals(tick.getEntities().get(19).getPosition().getX(), 7);
        assertEquals(tick.getEntities().get(19).getPosition().getY(), 1);
        tick = controller.tick(null, Direction.RIGHT); 
        assertEquals(tick.getEntities().get(19).getPosition().getX(), 8);
        assertEquals(tick.getEntities().get(19).getPosition().getY(), 1);
        tick = controller.tick(null, Direction.RIGHT); 
        assertEquals(tick.getEntities().get(19).getPosition().getX(), 9);
        assertEquals(tick.getEntities().get(19).getPosition().getY(), 1);
        tick = controller.tick(null, Direction.RIGHT); 
        assertEquals(tick.getEntities().get(19).getPosition().getX(), 10);
        assertEquals(tick.getEntities().get(19).getPosition().getY(), 1);
        tick = controller.tick(null, Direction.DOWN); 
        assertEquals(tick.getEntities().get(19).getPosition().getX(), 10);
        assertEquals(tick.getEntities().get(19).getPosition().getY(), 2);
        tick = controller.tick(null, Direction.DOWN); 
        assertEquals(tick.getEntities().get(19).getPosition().getX(), 10);
        assertEquals(tick.getEntities().get(19).getPosition().getY(), 3);
        tick = controller.tick(null, Direction.DOWN); 
        assertEquals(tick.getEntities().get(19).getPosition().getX(), 10);
        assertEquals(tick.getEntities().get(19).getPosition().getY(), 4);
        tick = controller.tick(null, Direction.RIGHT); 
        assertEquals(tick.getEntities().get(19).getPosition().getX(), 11);
        assertEquals(tick.getEntities().get(19).getPosition().getY(), 4);
        tick = controller.tick(null, Direction.RIGHT); 
        assertEquals(tick.getEntities().get(19).getPosition().getX(), 12);
        assertEquals(tick.getEntities().get(19).getPosition().getY(), 4);
        tick = controller.tick(null, Direction.RIGHT); 
        assertEquals(tick.getEntities().get(19).getPosition().getX(), 13);
        assertEquals(tick.getEntities().get(19).getPosition().getY(), 4);
        tick = controller.tick(null, Direction.RIGHT); 
        assertEquals(tick.getEntities().get(19).getPosition().getX(), 14);
        assertEquals(tick.getEntities().get(19).getPosition().getY(), 4);
        int size1 = tick.getEntities().size();
        assertEquals(tick.getInventory().size(), 5);
        tick = controller.tick("4", null);
        assertEquals(tick.getInventory().size(), 4);
        int size2 = tick.getInventory().size();
        assertNotEquals(size1, size2);
    }
}