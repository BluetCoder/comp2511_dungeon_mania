package dungeonmania;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import java.io.IOException;


import dungeonmania.response.models.DungeonResponse;
import dungeonmania.util.Direction;

public class ZombieSpawnerTests {
    // Checks that it spawns zombies at 20 ticks peaceful
    @Test
    public void ZombieSpawnerPeaceful() throws IllegalArgumentException, IOException {
        DungeonManiaController controller = new DungeonManiaController();
        DungeonResponse newGame = controller.newGame("zombieSpawner", "Peaceful");
        assertEquals(newGame.getDungeonId(), "0");
        assertEquals(newGame.getDungeonName(), "zombieSpawner");
        assertEquals(newGame.getEntities().size(), 120);
        assertEquals(newGame.getGoals(), "(enemies AND treasure)");
        
        // check the treasure goal is completed
        // treasure is beside player
        DungeonResponse tick = controller.tick(null, Direction.UP);
        tick = controller.tick(null, Direction.UP);
        assertEquals(tick.getEntities().size(), 120);
        tick = controller.tick(null, Direction.UP);
        assertEquals(tick.getEntities().size(), 120);
        tick = controller.tick(null, Direction.UP);
        assertEquals(tick.getEntities().size(), 120);
        tick = controller.tick(null, Direction.UP);
        assertEquals(tick.getEntities().size(), 120);
        tick = controller.tick(null, Direction.UP);
        assertEquals(tick.getEntities().size(), 120);
        tick = controller.tick(null, Direction.UP);
        assertEquals(tick.getEntities().size(), 120);
        tick = controller.tick(null, Direction.UP);
        assertEquals(tick.getEntities().size(), 120);
        tick = controller.tick(null, Direction.UP);
        assertEquals(tick.getEntities().size(), 120);
        tick = controller.tick(null, Direction.UP);
        assertEquals(tick.getEntities().size(), 120);
        tick = controller.tick(null, Direction.UP);
        assertEquals(tick.getEntities().size(), 120);
        tick = controller.tick(null, Direction.UP);
        assertEquals(tick.getEntities().size(), 120);
        tick = controller.tick(null, Direction.UP);
        assertEquals(tick.getEntities().size(), 120);
        tick = controller.tick(null, Direction.UP);
        assertEquals(tick.getEntities().size(), 120);
        tick = controller.tick(null, Direction.UP);
        assertEquals(tick.getEntities().size(), 120);
        tick = controller.tick(null, Direction.UP);
        assertEquals(tick.getEntities().size(), 120);
        tick = controller.tick(null, Direction.UP);
        assertEquals(tick.getEntities().size(), 120);
        tick = controller.tick(null, Direction.UP);
        assertEquals(tick.getEntities().size(), 120);
        tick = controller.tick(null, Direction.UP);
        assertEquals(tick.getEntities().size(), 120);
        
        // after 20 movements 
        tick = controller.tick(null, Direction.UP);
        assertEquals(tick.getEntities().size(), 122);  
    }

    // Checks that it spawns zombies at 20 ticks for standard
    @Test
    public void ZombieSpawnerStandard() throws IllegalArgumentException, IOException {
        DungeonManiaController controller = new DungeonManiaController();
        DungeonResponse newGame = controller.newGame("zombieSpawner", "Standard");
        assertEquals(newGame.getDungeonId(), "0");
        assertEquals(newGame.getDungeonName(), "zombieSpawner");
        assertEquals(newGame.getEntities().size(), 120);
        assertEquals(newGame.getGoals(), "(enemies AND treasure)");
        
        // check the treasure goal is completed
        // treasure is beside player
        DungeonResponse tick = controller.tick(null, Direction.UP);
        tick = controller.tick(null, Direction.UP);
        assertEquals(tick.getEntities().size(), 120);
        tick = controller.tick(null, Direction.UP);
        assertEquals(tick.getEntities().size(), 120);
        tick = controller.tick(null, Direction.UP);
        assertEquals(tick.getEntities().size(), 120);
        tick = controller.tick(null, Direction.UP);
        assertEquals(tick.getEntities().size(), 120);
        tick = controller.tick(null, Direction.UP);
        assertEquals(tick.getEntities().size(), 120);
        tick = controller.tick(null, Direction.UP);
        assertEquals(tick.getEntities().size(), 120);
        tick = controller.tick(null, Direction.UP);
        assertEquals(tick.getEntities().size(), 120);
        tick = controller.tick(null, Direction.UP);
        assertEquals(tick.getEntities().size(), 120);
        tick = controller.tick(null, Direction.UP);
        assertEquals(tick.getEntities().size(), 120);
        tick = controller.tick(null, Direction.UP);
        assertEquals(tick.getEntities().size(), 120);
        tick = controller.tick(null, Direction.UP);
        assertEquals(tick.getEntities().size(), 120);
        tick = controller.tick(null, Direction.UP);
        assertEquals(tick.getEntities().size(), 120);
        tick = controller.tick(null, Direction.UP);
        assertEquals(tick.getEntities().size(), 120);
        tick = controller.tick(null, Direction.UP);
        assertEquals(tick.getEntities().size(), 120);
        tick = controller.tick(null, Direction.UP);
        assertEquals(tick.getEntities().size(), 120);
        tick = controller.tick(null, Direction.UP);
        assertEquals(tick.getEntities().size(), 120);
        tick = controller.tick(null, Direction.UP);
        assertEquals(tick.getEntities().size(), 120);
        tick = controller.tick(null, Direction.UP);
        assertEquals(tick.getEntities().size(), 120);
        
        // after 20 movements 
        tick = controller.tick(null, Direction.UP);
        assertEquals(tick.getEntities().size(), 122);  
    }

    // Checks that it spawns zombies at 20 ticks for hard
    @Test
    public void ZombieSpawnerHard() throws IllegalArgumentException, IOException {
        DungeonManiaController controller = new DungeonManiaController();
        DungeonResponse newGame = controller.newGame("zombieSpawner", "Hard");
        assertEquals(newGame.getDungeonId(), "0");
        assertEquals(newGame.getDungeonName(), "zombieSpawner");
        assertEquals(newGame.getEntities().size(), 120);
        assertEquals(newGame.getGoals(), "(enemies AND treasure)");
        
        // check the treasure goal is completed
        // treasure is beside player
        DungeonResponse tick = controller.tick(null, Direction.UP);
        tick = controller.tick(null, Direction.UP);
        assertEquals(tick.getEntities().size(), 120);
        tick = controller.tick(null, Direction.UP);
        assertEquals(tick.getEntities().size(), 120);
        tick = controller.tick(null, Direction.UP);
        assertEquals(tick.getEntities().size(), 120);
        tick = controller.tick(null, Direction.UP);
        assertEquals(tick.getEntities().size(), 120);
        tick = controller.tick(null, Direction.UP);
        assertEquals(tick.getEntities().size(), 120);
        tick = controller.tick(null, Direction.UP);
        assertEquals(tick.getEntities().size(), 120);
        tick = controller.tick(null, Direction.UP);
        assertEquals(tick.getEntities().size(), 120);
        tick = controller.tick(null, Direction.UP);
        assertEquals(tick.getEntities().size(), 120);
        tick = controller.tick(null, Direction.UP);
        assertEquals(tick.getEntities().size(), 120);
        tick = controller.tick(null, Direction.UP);
        assertEquals(tick.getEntities().size(), 120);
        tick = controller.tick(null, Direction.UP);
        assertEquals(tick.getEntities().size(), 120);
        tick = controller.tick(null, Direction.UP);
        assertEquals(tick.getEntities().size(), 120);
        tick = controller.tick(null, Direction.UP);
        assertEquals(tick.getEntities().size(), 120);
        
        // spawn zombies at 15 ticks
        tick = controller.tick(null, Direction.UP);
        assertEquals(tick.getEntities().size(), 122);


        tick = controller.tick(null, Direction.UP);
        tick = controller.tick(null, Direction.UP);
        tick = controller.tick(null, Direction.UP);
        tick = controller.tick(null, Direction.UP);
        
    }
}
