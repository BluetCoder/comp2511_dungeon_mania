package dungeonmania;

//import static org.junit.jupiter.api.Assertions.assertTrue;
//import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertIterableEquals;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import dungeonmania.response.models.DungeonResponse;

public class NewGame {
    @Test
    public void newGameIntro() throws IllegalArgumentException, IOException {
        DungeonManiaController controller = new DungeonManiaController();
        DungeonResponse newGame = controller.newGame("intro", "Peaceful");
        assertEquals(newGame.getDungeonId(), "0");
        assertEquals(newGame.getDungeonName(), "intro");
        assertEquals(newGame.getGoals(), "exit");
    }
}