package dungeonmania;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.io.IOException;

import javax.swing.text.Position;

import org.junit.jupiter.api.Test;
import dungeonmania.response.models.DungeonResponse;
import dungeonmania.util.Direction;


public class HydraTest {
    @Test
    public void Hydra() throws IllegalArgumentException, IOException {
        DungeonManiaController controller = new DungeonManiaController();
        DungeonResponse newGame = controller.newGame("advanced", "Hard");        
        assertEquals(newGame.getDungeonName(), "advanced");
        assertEquals(newGame.getEntities().size(), 120);
        
        DungeonResponse tick = controller.tick(null, Direction.RIGHT);
        for (int i = 0; i < 49; i++) {
            tick = controller.tick(null, Direction.RIGHT);
        }
        assertEquals(tick.getEntities().size(), 121);
        assertEquals(tick.getEntities().get(120).getType(), "hydra");
        dungeonmania.util.Position hydraPosition1 = tick.getEntities().get(120).getPosition();
        tick = controller.tick(null, Direction.RIGHT);
        tick = controller.tick(null, Direction.RIGHT);
        tick = controller.tick(null, Direction.RIGHT);
        assertNotEquals(hydraPosition1, tick.getEntities().get(120).getPosition());
        
        for (int i = 0; i < 49; i++) {
            tick = controller.tick(null, Direction.RIGHT);
        }   
    }
}
