package dungeonmania.entity;

public class InvisibleState implements PlayerState {
    String state;
    int remainingTime;

    /**
     * Constructor for InvisibleState
     * 
     * @param state         the player state
     * @param remainingTime the remaining ticks for state
     */
    public InvisibleState() {
        this.state = "invisible";
        this.remainingTime = 50;
    }

    /**
     * Getter for remainingTime
     * 
     * @return number for the remaining ticks
     */
    public int getRemainingTime() {
        return remainingTime;
    }

    @Override
    public void reduceRemainingTime() {
        this.remainingTime = getRemainingTime() - 1;
    }

    @Override
    public void toInvincible(Player player) {
        player.setPlayerState(new InvincibleState());
    }

    @Override
    public void toInvisibile(Player player) {
        player.setPlayerState(new InvisibleState());
    }

    @Override
    public void toNormal(Player player) {
        player.setPlayerState(new NormalState());
    }

    @Override
    public String getState() {
        return this.state;
    }
}