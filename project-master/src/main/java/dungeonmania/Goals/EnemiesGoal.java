package dungeonmania.Goals;

import java.util.List;

import dungeonmania.entity.Entity;
import dungeonmania.entity.Player;

public class EnemiesGoal implements GoalComposite {
    private List<Entity> allEntities;
    private String goalName = "enemies";

    /**
     * Enemies goal is a class that checks for enemy goal completion
     * 
     * @param allEntities
     */
    public EnemiesGoal(List<Entity> allEntities) {
        this.allEntities = allEntities;
    }

    /**
     * Checks if enemiesGoal is completed by ensuring that no more enemies exist in
     * the enitity array in the current tick state
     */
    @Override
    public boolean checkGoalCompleted() {
        Player player = null;
        for (Entity entity : allEntities) {
            if (entity.getType().equals("player")) {
                player = (Player) entity;
                break;
            }
        }
        if (player == null) {
            return false;
        }
        for (Entity entity : allEntities) {
            if (entity.getType().equals("spider") || entity.getType().equals("zombie_toast") || entity.getType().equals("zombie_toast_spawner") || entity.getType().equals("mercenary")) {
                if (!player.getAllies().contains(entity)){
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public String getGoalName() {
        return goalName;
    }

}
