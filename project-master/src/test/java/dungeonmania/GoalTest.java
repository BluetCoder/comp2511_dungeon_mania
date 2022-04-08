package dungeonmania;

//import static org.junit.jupiter.api.Assertions.assertTrue;
//import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertIterableEquals;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import dungeonmania.response.models.DungeonResponse;
import dungeonmania.util.Direction;

public class GoalTest {
    
    // Checks that it completes exit goal
    @Test
    public void IntroExitGoalTest() throws IllegalArgumentException, IOException {
        DungeonManiaController controller = new DungeonManiaController();
        DungeonResponse newGame = controller.newGame("intro", "Peaceful");
        assertEquals(newGame.getDungeonId(), "0");
        assertEquals(newGame.getDungeonName(), "intro");
        assertEquals(newGame.getGoals(), "exit");
        
        DungeonResponse tick = controller.tick(null, Direction.RIGHT);
        assertEquals(tick.getGoals(), "exit");

        // moves to exit
        tick = controller.tick(null, Direction.RIGHT);
        assertEquals(tick.getGoals(), "exit");
        tick = controller.tick(null, Direction.RIGHT);
        assertEquals(tick.getGoals(), "exit");
        tick = controller.tick(null, Direction.RIGHT);
        assertEquals(tick.getGoals(), "exit");
        tick = controller.tick(null, Direction.RIGHT);

        // goal should be completed for exit
        assertEquals(tick.getGoals(), "");
    }

    // Checks that it completes boulder goal
    @Test
    public void IntroBoulders() throws IllegalArgumentException, IOException {
        DungeonManiaController controller = new DungeonManiaController();
        DungeonResponse newGame = controller.newGame("introBoulders", "Peaceful");
        assertEquals(newGame.getDungeonId(), "0");
        assertEquals(newGame.getDungeonName(), "introBoulders");
        assertEquals(newGame.getGoals(), "boulders");
        
        DungeonResponse tick = controller.tick(null, Direction.RIGHT);
        assertEquals(tick.getGoals(), "boulders");

        // moves boulder to switch
        tick = controller.tick(null, Direction.RIGHT);
        assertEquals(tick.getGoals(), "");
    }

    // Checks that it completes treasure goal
    @Test
    public void IntroTreasure() throws IllegalArgumentException, IOException {
        DungeonManiaController controller = new DungeonManiaController();
        DungeonResponse newGame = controller.newGame("introTreasure", "Peaceful");
        assertEquals(newGame.getDungeonId(), "0");
        assertEquals(newGame.getDungeonName(), "introTreasure");
        assertEquals(newGame.getGoals(), "treasure");
        
        // check the treasure goal is completed
        // treasure is beside player
        DungeonResponse tick = controller.tick(null, Direction.RIGHT);
        assertEquals(tick.getGoals(), "");
    }

    // Checks that two goals can be completed
    @Test
    public void IntroTreasureBoulders() throws IllegalArgumentException, IOException {
        DungeonManiaController controller = new DungeonManiaController();
        DungeonResponse newGame = controller.newGame("introTreasureBoulders", "Peaceful");
        assertEquals(newGame.getDungeonId(), "0");
        assertEquals(newGame.getDungeonName(), "introTreasureBoulders");
        //assertEquals(newGame.getGoals(), "bouldersANDtreasure");
        
        // treasure goal is completed but not boulder
        DungeonResponse tick = controller.tick(null, Direction.RIGHT);
        //assertEquals(tick.getGoals(), "bouldersANDtreasure");
        
        // both treasure and boulder goal completed
        tick = controller.tick(null, Direction.RIGHT);
        assertEquals(tick.getGoals(), "");
    }

    // Checks that three goals can be completed
    @Test
    public void IntroExitAndTreasureAndBoulders() throws IllegalArgumentException, IOException {
        DungeonManiaController controller = new DungeonManiaController();
        DungeonResponse newGame = controller.newGame("introBouldersAndTreasureAndExit", "Peaceful");
        assertEquals(newGame.getDungeonId(), "0");
        assertEquals(newGame.getDungeonName(), "introBouldersAndTreasureAndExit");
        assertEquals(newGame.getGoals(), "(exit AND (treasure AND boulders))");
        DungeonResponse tick = controller.tick(null, Direction.RIGHT);
        
        // treasure and boulder goal completed but not exit
        for (int i = 0; i < 3; i++) {
            tick = controller.tick(null, Direction.RIGHT);
        }

        assertEquals(newGame.getGoals(), "(exit AND (treasure AND boulders))");
        
        // treasure and boulder goal completed but not exit
        for (int i = 0; i < 2; i++) {
            tick = controller.tick(null, Direction.LEFT);
        }

        // All three goals should be completed
        assertEquals(tick.getGoals(), "");
    }

    // Checks that three goals can be completed
    @Test
    public void IntroExitAndTreasureOrBoulders() throws IllegalArgumentException, IOException {
        DungeonManiaController controller = new DungeonManiaController();
        DungeonResponse newGame = controller.newGame("introExitAndTreasureOrBoulders", "Peaceful");
        assertEquals(newGame.getDungeonId(), "0");
        assertEquals(newGame.getDungeonName(), "introExitAndTreasureOrBoulders");
        assertEquals(newGame.getGoals(), "(exit AND (treasure OR boulders))");
        DungeonResponse tick = controller.tick(null, Direction.RIGHT);
        
        for (int i = 0; i < 1; i++) {
            tick = controller.tick(null, Direction.RIGHT);
        }

        // treasure and exit goal is completed
        assertEquals(tick.getGoals(), "");
    }
}
