package dungeonmania;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.io.IOException;
import org.junit.jupiter.api.Test;
import dungeonmania.response.models.DungeonResponse;
import dungeonmania.util.Direction;

public class BouldersMovementTest {
    // Checks that boulder movement is correct
    @Test
    public void BouldersMovement() throws IllegalArgumentException, IOException {
        DungeonManiaController controller = new DungeonManiaController();
        DungeonResponse newGame = controller.newGame("boulders", "Peaceful");        
        assertEquals(newGame.getDungeonName(), "boulders");
        assertEquals(newGame.getGoals(), "boulders");
        
        DungeonResponse tick = controller.tick(null, Direction.RIGHT);
        tick = controller.tick(null, Direction.RIGHT);
        
        // check that player moves along with boulder
        assertEquals(tick.getEntities().get(42).getType(), "player");
        assertEquals(tick.getEntities().get(42).getPosition().getX(), 4);
        assertEquals(tick.getEntities().get(42).getPosition().getY(), 2);

        // move boulder to wall
        assertEquals(tick.getEntities().get(43).getType(), "boulder");
        assertEquals(tick.getEntities().get(43).getPosition().getX(), 5);
        assertEquals(tick.getEntities().get(43).getPosition().getY(), 2);
        tick = controller.tick(null, Direction.RIGHT);

        // boulder can't move because of wall
        tick = controller.tick(null, Direction.RIGHT);
        assertEquals(tick.getEntities().get(43).getType(), "boulder");
        assertEquals(tick.getEntities().get(43).getPosition().getX(), 5);
        assertEquals(tick.getEntities().get(43).getPosition().getY(), 2);

         // player does not move
         assertEquals(tick.getEntities().get(42).getType(), "player");
         assertEquals(tick.getEntities().get(42).getPosition().getX(), 4);
         assertEquals(tick.getEntities().get(42).getPosition().getY(), 2);

        // boulder can't move because of boulder
        tick = controller.tick(null, Direction.DOWN);
        assertEquals(tick.getEntities().get(44).getType(), "boulder");
        assertEquals(tick.getEntities().get(44).getPosition().getX(), 4);
        assertEquals(tick.getEntities().get(44).getPosition().getY(), 3);

        // player does not move
        assertEquals(tick.getEntities().get(42).getType(), "player");
        assertEquals(tick.getEntities().get(42).getPosition().getX(), 4);
        assertEquals(tick.getEntities().get(42).getPosition().getY(), 2);
    }
}
