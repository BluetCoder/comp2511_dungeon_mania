package dungeonmania.Goals;

public interface GoalComposite {
    /**
     * Checks if the goal condition for a particular dungeon has been met
     * 
     * @return true if so, and false if not
     */
    public boolean checkGoalCompleted();

    /**
     * Method to get goal type by grabbing name of goal strategy
     * 
     * @return goalName (Boulder, Enenmies, Exit, Treasure)
     */
    public String getGoalName();
}
