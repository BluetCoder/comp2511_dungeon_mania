package dungeonmania.entity;

public interface PlayerState {
    /**
     * Changes player state to invincible
     * 
     * @param player
     */
    public void toInvincible(Player player);

    /**
     * Changes player state to invisible
     * 
     * @param player
     */
    public void toInvisibile(Player player);

    /**
     * Changes the player state to normal
     * 
     * @param player
     */
    public void toNormal(Player player);

    /**
     * Getter for remaining time
     * 
     * @return int of remaining ticks
     */
    public int getRemainingTime();

    /**
     * Reduces the ticks remaining state
     */
    public void reduceRemainingTime();

    /**
     * Getter for state
     * 
     * @return string that represents the state
     */
    public String getState();
}