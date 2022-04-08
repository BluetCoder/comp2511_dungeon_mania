package dungeonmania;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.io.IOException;

import javax.swing.text.Position;

import org.junit.jupiter.api.Test;
import dungeonmania.response.models.DungeonResponse;
import dungeonmania.util.Direction;


public class PortalsTest {
    @Test
    public void Portal() throws IllegalArgumentException, IOException {
        DungeonManiaController controller = new DungeonManiaController();
        DungeonResponse newGame = controller.newGame("portals", "Standard");        
        assertEquals(newGame.getDungeonName(), "portals");
        
        DungeonResponse tick = controller.tick(null, Direction.DOWN);
        dungeonmania.util.Position portalPosition1 = tick.getEntities().get(5).getPosition();
        tick = controller.tick(null, Direction.RIGHT);

        dungeonmania.util.Position playerPosition = tick.getEntities().get(33).getPosition();
        assertEquals(portalPosition1,  tick.getEntities().get(5).getPosition());
        assertEquals(playerPosition,  tick.getEntities().get(33).getPosition());
        tick = controller.tick(null, Direction.RIGHT);
        
        dungeonmania.util.Position newPlayerPosition = tick.getEntities().get(33).getPosition();
        assertEquals(newPlayerPosition.getX(), 7);
        assertEquals(newPlayerPosition.getY(), 2);
    }

    @Test
    public void PortalBlocker() throws IllegalArgumentException, IOException {
        DungeonManiaController controller = new DungeonManiaController();
        DungeonResponse newGame = controller.newGame("portalsAndBoulder", "Standard");        
        assertEquals(newGame.getDungeonName(), "portalsAndBoulder");
        
        DungeonResponse tick = controller.tick(null, Direction.DOWN);
        tick = controller.tick(null, Direction.DOWN);
        tick = controller.tick(null, Direction.RIGHT);
        tick = controller.tick(null, Direction.RIGHT);
        dungeonmania.util.Position playerPosition = tick.getEntities().get(34).getPosition();
        assertEquals(playerPosition.getX(), 3);
        assertEquals(playerPosition.getY(), 3);
        tick = controller.tick(null, Direction.UP);
        dungeonmania.util.Position newPlayerPosition = tick.getEntities().get(34).getPosition();
        assertEquals(newPlayerPosition.getX(), 3);
        assertEquals(newPlayerPosition.getY(), 2);
    }
}
