package dungeonmania;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.io.IOException;

import javax.swing.text.Position;

import org.junit.jupiter.api.Test;
import dungeonmania.response.models.DungeonResponse;
import dungeonmania.util.Direction;


public class BuildTest {
    @Test
    public void Build() throws IllegalArgumentException, IOException {
        DungeonManiaController controller = new DungeonManiaController();
        DungeonResponse newGame = controller.newGame("build", "Peaceful");        
        assertEquals(newGame.getDungeonName(), "build");
        assertEquals(newGame.getInventory().size(), 0);
        assertEquals(newGame.getEntities().size(), 13);
        
        DungeonResponse tick = controller.tick(null, Direction.LEFT);
        for (int i = 0; i < 3; i++) {
            tick = controller.tick(null, Direction.LEFT);
        }
        // Get armour
        tick = controller.tick(null, Direction.UP);
        assertEquals(tick.getInventory().size(), 1);

        // Get Sunstone
        tick = controller.tick(null, Direction.UP);
        assertEquals(tick.getInventory().size(), 2);
        assertEquals(tick.getBuildables().get(0), "midnight_armour");
        

        // Get Treasure
        tick = controller.tick(null, Direction.LEFT);
        assertEquals(tick.getInventory().size(), 3);

        // Get Key
        tick = controller.tick(null, Direction.UP);
        assertEquals(tick.getInventory().size(), 4);
        assertEquals(tick.getBuildables().size(), 1);
        
        // Get Arrow
        tick = controller.tick(null, Direction.UP);
        assertEquals(tick.getInventory().size(), 5);
        assertEquals(tick.getBuildables().size(), 1);

        // Get Arrow
        tick = controller.tick(null, Direction.RIGHT);
        assertEquals(tick.getInventory().size(), 6);
        assertEquals(tick.getBuildables().size(), 2);

        // Get Arrow
        tick = controller.tick(null, Direction.RIGHT);
        assertEquals(tick.getInventory().size(), 7);
        assertEquals(tick.getBuildables().size(), 2);

        // Get Wood
        tick = controller.tick(null, Direction.UP);
        assertEquals(tick.getInventory().size(), 8);
        assertEquals(tick.getBuildables().size(), 3);

        // Get Wood
        tick = controller.tick(null, Direction.LEFT);
        assertEquals(tick.getInventory().size(), 9);
        assertEquals(tick.getBuildables().size(), 4);
        
        // Get Wood
        tick = controller.tick(null, Direction.LEFT);
        assertEquals(tick.getInventory().size(), 10);
        assertEquals(tick.getBuildables().size(), 4);

        // Build midnight armour. Sunstone does not get consumed
        DungeonResponse build = controller.build("midnight_armour");
        assertEquals(build.getInventory().size(), 10);
        assertEquals(build.getBuildables().size(), 3);

        // Sceptre. One wood, one treasure and sunstone
        build = controller.build("sceptre");
        assertEquals(build.getInventory().size(), 9);
        assertEquals(build.getBuildables().size(), 3);

        // bow. one wood, two arrow 
        build = controller.build("bow");
        assertEquals(build.getInventory().size(), 6);
        assertEquals(build.getBuildables().size(), 1);

        // sceptre. one wood, one key and sunstone
        build = controller.build("sceptre");
        assertEquals(build.getInventory().size(), 5);
        assertEquals(build.getBuildables().size(), 0);
    }

    @Test
    public void BuildShield() throws IllegalArgumentException, IOException {
        DungeonManiaController controller = new DungeonManiaController();
        DungeonResponse newGame = controller.newGame("build", "Peaceful");        
        assertEquals(newGame.getDungeonName(), "build");
        assertEquals(newGame.getInventory().size(), 0);
        assertEquals(newGame.getEntities().size(), 13);
        
        DungeonResponse tick = controller.tick(null, Direction.LEFT);
        for (int i = 0; i < 3; i++) {
            tick = controller.tick(null, Direction.LEFT);
        }
        // Get armour
        tick = controller.tick(null, Direction.UP);
        assertEquals(tick.getInventory().size(), 1);

        // Get Sunstone
        tick = controller.tick(null, Direction.UP);
        assertEquals(tick.getInventory().size(), 2);
        assertEquals(tick.getBuildables().get(0), "midnight_armour");
        

        // Get Treasure
        tick = controller.tick(null, Direction.LEFT);
        assertEquals(tick.getInventory().size(), 3);

        // Get Key
        tick = controller.tick(null, Direction.UP);
        assertEquals(tick.getInventory().size(), 4);
        assertEquals(tick.getBuildables().size(), 1);
        
        // Get Arrow
        tick = controller.tick(null, Direction.UP);
        assertEquals(tick.getInventory().size(), 5);
        assertEquals(tick.getBuildables().size(), 1);

        // Get Arrow
        tick = controller.tick(null, Direction.RIGHT);
        assertEquals(tick.getInventory().size(), 6);
        assertEquals(tick.getBuildables().size(), 2);

        // Get Arrow
        tick = controller.tick(null, Direction.RIGHT);
        assertEquals(tick.getInventory().size(), 7);
        assertEquals(tick.getBuildables().size(), 2);

        // Get Wood
        tick = controller.tick(null, Direction.UP);
        assertEquals(tick.getInventory().size(), 8);
        assertEquals(tick.getBuildables().size(), 3);

        // Get Wood
        tick = controller.tick(null, Direction.LEFT);
        assertEquals(tick.getInventory().size(), 9);
        assertEquals(tick.getBuildables().size(), 4);
        
        // Get Wood
        tick = controller.tick(null, Direction.LEFT);
        assertEquals(tick.getInventory().size(), 10);
        assertEquals(tick.getBuildables().size(), 4);

        // Build midnight armour. Sunstone does not get consumed
        DungeonResponse build = controller.build("midnight_armour");
        assertEquals(build.getInventory().size(), 10);
        assertEquals(build.getBuildables().size(), 3);

        // Sceptre. One wood, one treasure and sunstone
        build = controller.build("sceptre");
        assertEquals(build.getInventory().size(), 9);
        assertEquals(build.getBuildables().size(), 3);

        // shield. two wood, one treasure
        build = controller.build("shield");
        assertEquals(build.getInventory().size(), 8);
        assertEquals(build.getBuildables().size(), 1);

        // Doesn't build
        build = controller.build("bow");
        assertEquals(build.getInventory().size(), 8);
        assertEquals(build.getBuildables().size(), 1);

        // sceptre. one wood, one key and sunstone
        build = controller.build("sceptre");
        assertEquals(build.getInventory().size(), 6);
        assertEquals(build.getBuildables().size(), 0);
    }

    @Test
    public void CantBuildMidnightArmour() throws IllegalArgumentException, IOException {
        DungeonManiaController controller = new DungeonManiaController();
        DungeonResponse newGame = controller.newGame("build", "Hard");        
        assertEquals(newGame.getDungeonName(), "build");
        assertEquals(newGame.getInventory().size(), 0);
        assertEquals(newGame.getEntities().size(), 13);
        
        DungeonResponse tick = controller.tick(null, Direction.LEFT);
        for (int i = 0; i < 3; i++) {
            tick = controller.tick(null, Direction.LEFT);
        }
        // Get armour
        tick = controller.tick(null, Direction.UP);
        assertEquals(tick.getInventory().size(), 1);

        // Get Sunstone
        tick = controller.tick(null, Direction.UP);
        assertEquals(tick.getInventory().size(), 2);
        assertEquals(tick.getBuildables().get(0), "midnight_armour");
        
        for (int i = 0; i < 10; i++) {
            tick = controller.tick(null, Direction.UP);
        }
        assertEquals(tick.getBuildables().size(), 0);


    }

    
}
