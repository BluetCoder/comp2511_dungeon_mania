package dungeonmania.Goals;

import java.util.List;

import dungeonmania.entity.Entity;

public class TreasureGoal implements GoalComposite {
    private List<Entity> allEntities;
    private String goalName = "treasure";

    /**
     * Treasure goal class checks for treausre goal completion
     * 
     * @param allEntities
     */
    public TreasureGoal(List<Entity> allEntities) {
        this.allEntities = allEntities;
    }

    /**
     * Checks if the treasureGoal is completed by enssuring that no more treasure
     * exist in the list entity array in the current tick state
     */
    @Override
    public boolean checkGoalCompleted() {
        for (Entity entity : allEntities) {
            if (entity.getType().equals("treasure")) {
                return false;
            }
        }
        return true;
    }

    @Override
    public String getGoalName() {
        return goalName;
    }

}
