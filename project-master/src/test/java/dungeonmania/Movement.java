package dungeonmania;

//import static org.junit.jupiter.api.Assertions.assertTrue;
//import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertIterableEquals;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import dungeonmania.response.models.DungeonResponse;
import dungeonmania.util.Direction;

public class Movement {
    @Test
    // Check player movement
    public void playerMovement() throws IllegalArgumentException, IOException {
        DungeonManiaController controller = new DungeonManiaController();
        DungeonResponse newGame = controller.newGame("intro", "Peaceful");
        assertEquals(newGame.getDungeonId(), "0");
        assertEquals(newGame.getDungeonName(), "intro");
        assertEquals(newGame.getGoals(), "exit");
        
        // Move to the right
        assertEquals(newGame.getEntities().get(19).getPosition().getX(), 1);
        assertEquals(newGame.getEntities().get(19).getPosition().getY(), 1);
        DungeonResponse tick = controller.tick(null, Direction.RIGHT);
        assertEquals(tick.getEntities().get(19).getPosition().getX(), 2);
        assertEquals(tick.getEntities().get(19).getPosition().getY(), 1);

        // Move up. Should be blocked by wall
        tick = controller.tick(null, Direction.UP);
        assertEquals(tick.getEntities().get(19).getPosition().getX(), 2);
        assertEquals(tick.getEntities().get(19).getPosition().getY(), 1);
    }
}