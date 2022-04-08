package dungeonmania;

//import static org.junit.jupiter.api.Assertions.assertTrue;
//import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertIterableEquals;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import dungeonmania.response.models.DungeonResponse;
import dungeonmania.util.Direction;

public class SaveGameTest {
    @Test
    // Checks save game with correct input
    public void SaveGame() throws IllegalArgumentException, IOException {
        DungeonManiaController controller = new DungeonManiaController();
        DungeonResponse newGame = controller.newGame("intro", "Peaceful");
        assertEquals(newGame.getDungeonId(), "0");
        assertEquals(newGame.getDungeonName(), "intro");
        assertEquals(newGame.getEntities().get(19).getPosition().getX(), 1);
        assertEquals(newGame.getEntities().get(19).getPosition().getY(), 1);
        
        // moves the character to the right
        DungeonResponse tick = controller.tick(null, Direction.RIGHT);
        assertEquals(tick.getEntities().get(19).getPosition().getX(), 2);
        assertEquals(tick.getEntities().get(19).getPosition().getY(), 1);
        
        // saves game
        DungeonResponse saveGame = controller.saveGame("intro");
        assertEquals(tick.getEntities().get(19).getPosition().getX(), saveGame.getEntities().get(19).getPosition().getX());
        assertEquals(tick.getEntities().get(19).getPosition().getY(), saveGame.getEntities().get(19).getPosition().getY());

    }
}