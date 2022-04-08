package dungeonmania.Goals;

import java.util.ArrayList;
import java.util.List;

import dungeonmania.entity.Entity;
import dungeonmania.entity.FloorSwitch;

public class BoulderGoal implements GoalComposite {
    private List<Entity> allEntities;
    private String goalName = "boulders";

    /**
     * Boulder Goal checks conditions of boulder goal completion
     * 
     * @param allEntities
     */
    public BoulderGoal(List<Entity> allEntities) {
        this.allEntities = allEntities;
    }

    /**
     * Checks if the boulderGoal is completed by ensuring that the total number of
     * switches matches the number of switches that have been toggled by a boulder
     * in the current tick state
     */
    @Override
    public boolean checkGoalCompleted() {
        List<FloorSwitch> allFloorSwitches = new ArrayList<FloorSwitch>();
        for (Entity entity : allEntities) {
            if (entity.getType().equals("switch")) {
                FloorSwitch floorSwitch = (FloorSwitch) entity;
                allFloorSwitches.add(floorSwitch);
            }
        }

        int numTotalSwitches = allFloorSwitches.size();
        int numToggledSwitches = 0;

        for (FloorSwitch floorSwitch : allFloorSwitches) {
            if (floorSwitch.isToggledByBoulder()) {
                numToggledSwitches += 1;
            }
        }

        if (numToggledSwitches == numTotalSwitches) {
            return true;
        }

        return false;
    }

    @Override
    public String getGoalName() {
        return goalName;
    }

}
