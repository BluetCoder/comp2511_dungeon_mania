package dungeonmania;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import java.io.IOException;


import dungeonmania.response.models.DungeonResponse;
import dungeonmania.util.Direction;

public class SpiderMovementTest {
    // Checks that it spawns zombies at 20 ticks peaceful
    @Test
    public void testSpiderMovementNoBoulder() throws IllegalArgumentException, IOException {
        DungeonManiaController controller = new DungeonManiaController();
        DungeonResponse newGame = controller.newGame("intro", "Standard");
        DungeonResponse tick = null;

        for (int i = 0; i < 25; i++) {
            tick = controller.tick(null, Direction.UP);
        }
        assertEquals(tick.getEntities().get(21).getType(), "spider");
        int middleX = tick.getEntities().get(21).getPosition().getX();
        int middleY = tick.getEntities().get(21).getPosition().getY();

        tick = controller.tick(null, Direction.UP);
        int topMiddleX = tick.getEntities().get(21).getPosition().getX();
        int topMiddleY = tick.getEntities().get(21).getPosition().getY();
        // Check after tick spider moves up from 'middle' position
        assertEquals(topMiddleX, middleX);
        assertEquals(topMiddleY, middleY - 1);

        tick = controller.tick(null, Direction.UP);
        int topRightX = tick.getEntities().get(21).getPosition().getX();
        int topRightY = tick.getEntities().get(21).getPosition().getY();
        // Check after tick spider moves right from 'top middle' position
        assertEquals(topRightX, topMiddleX + 1);
        assertEquals(topRightY, topMiddleY);

        tick = controller.tick(null, Direction.UP);
        int middleRightX = tick.getEntities().get(21).getPosition().getX();
        int middleRightY = tick.getEntities().get(21).getPosition().getY();
        // Check after tick spider moves down from 'top right' position
        assertEquals(middleRightX, topRightX);
        assertEquals(middleRightY, topMiddleY + 1);

        tick = controller.tick(null, Direction.UP);
        int bottomRightX = tick.getEntities().get(21).getPosition().getX();
        int bottomRightY = tick.getEntities().get(21).getPosition().getY();
        // Check after tick spider moves down from 'middle right' position
        assertEquals(bottomRightX, middleRightX);
        assertEquals(bottomRightY, middleRightY + 1);
    }

}