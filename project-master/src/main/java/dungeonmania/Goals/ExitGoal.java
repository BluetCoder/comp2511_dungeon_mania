package dungeonmania.Goals;

import java.util.List;

import dungeonmania.entity.Entity;
import dungeonmania.util.Position;

public class ExitGoal implements GoalComposite {
    private List<Entity> allEntities;
    private String goalName = "exit";

    /**
     * Exit Goal checks for exit goal completetion
     * 
     * @param allEntities
     */
    public ExitGoal(List<Entity> allEntities) {
        this.allEntities = allEntities;
    }

    /**
     * Checks if exitGoal is completed by ensuring that the player's postion and
     * exit position is the same, in the current tick state.
     */
    @Override
    public boolean checkGoalCompleted() {
        Position playerPosition = null;
        Position exitPosition = null;
        for (Entity entity : allEntities) {
            if (entity.getType().equals("player")) {
                playerPosition = entity.getPosition();
            }
            if (entity.getType().equals("exit")) {
                exitPosition = entity.getPosition();
            }
        }
        if (playerPosition != null && playerPosition.equals(exitPosition)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String getGoalName() {
        return goalName;
    }

}
