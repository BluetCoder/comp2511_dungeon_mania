package dungeonmania;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import java.io.IOException;

import dungeonmania.response.models.DungeonResponse;
import dungeonmania.util.Direction;

public class BombTest {
    // Checks that the bomb explodes and thus the entities list goes down
    @Test
    public void SimpleBombTest() throws IllegalArgumentException, IOException {
        DungeonManiaController controller = new DungeonManiaController();
        controller.newGame("introBomb", "Peaceful");
        DungeonResponse tick = controller.tick(null, Direction.RIGHT);
        tick = controller.tick(null, Direction.RIGHT);
        assertEquals(tick.getEntities().size(), 22);
        tick = controller.tick(null, Direction.RIGHT);
        assertEquals(tick.getEntities().size(), 22);
        tick = controller.tick("0", Direction.NONE);
        assertEquals(tick.getEntities().size(), 14);
    }
}