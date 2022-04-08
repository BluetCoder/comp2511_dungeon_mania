package dungeonmania;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;

import javax.swing.text.Position;

import org.junit.jupiter.api.Test;
import dungeonmania.response.models.DungeonResponse;
import dungeonmania.util.Direction;


public class MercenaryTest {
    @Test
    public void MercenaryMoveAndFight() throws IllegalArgumentException, IOException {
        DungeonManiaController controller = new DungeonManiaController();
        DungeonResponse newGame = controller.newGame("advanced", "Standard");        
        assertEquals(newGame.getDungeonName(), "advanced");
        assertEquals(newGame.getEntities().get(119).getType(), "mercenary");
        assertEquals(newGame.getEntities().get(19).getType(), "player");
        assertEquals(newGame.getEntities().get(19).getType(), "player");
        dungeonmania.util.Position playerPosition = newGame.getEntities().get(19).getPosition();
        
        // mercenary beside the player
        DungeonResponse tick = controller.tick(null, Direction.UP);
        for (int i = 0; i < 4; i++) {
            tick = controller.tick(null, Direction.UP);
        }
        dungeonmania.util.Position mercenaryPosition = tick.getEntities().get(119).getPosition();
        assertEquals(tick.getEntities().get(119).getType(), "mercenary");
        assertTrue(mercenaryPosition.getAdjacentPositions().contains(playerPosition));
        tick = controller.tick(null, Direction.UP);

        // mercenary died
        assertEquals(newGame.getEntities().size(), 120);
        assertEquals(tick.getEntities().size(), 119);
    }

    @Test
    public void MercenaryInteract() throws IllegalArgumentException, IOException {
        DungeonManiaController controller = new DungeonManiaController();
        DungeonResponse newGame = controller.newGame("introMercenary", "Standard");
        assertEquals(newGame.getDungeonName(), "introMercenary");
        assertEquals(newGame.getEntities().get(22).getType(), "mercenary");
        assertEquals(newGame.getEntities().get(20).getType(), "player");
        
        
        // mercenary beside the player
        DungeonResponse tick = controller.tick(null, Direction.RIGHT);
        assertEquals(tick.getEntities().size(), 22);
        assertEquals(tick.getEntities().get(21).getType(), "mercenary");
        dungeonmania.util.Position mercenaryPosition = tick.getEntities().get(21).getPosition();
        dungeonmania.util.Position playerPosition = tick.getEntities().get(20).getPosition();
        assertTrue(mercenaryPosition.getAdjacentPositions().contains(playerPosition));
        DungeonResponse interact = controller.interact(String.valueOf(22));
        assertEquals(interact.getEntities().get(21).getType(), "mercenary");

        tick = controller.tick(null, Direction.UP);

        // mercenary is an ally and does not die
        assertEquals(newGame.getEntities().size(), 23);
        assertEquals(interact.getEntities().size(), 22);
        assertEquals(tick.getEntities().get(21).getType(), "mercenary");
        assertEquals(tick.getEntities().size(), 22);

    }
}
