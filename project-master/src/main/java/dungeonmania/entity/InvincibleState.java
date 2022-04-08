package dungeonmania.entity;

public class InvincibleState implements PlayerState {
    String state;
    int remainingTime;

    /**
     * Constructor for Invincible State
     * 
     * @param state         the state of the player
     * @param remainingTime the number of ticks for the potion to last
     */
    public InvincibleState() {
        this.state = "invincible";
        this.remainingTime = 25;
    }

    /**
     * Getter for remainingTime
     * 
     * @return int of remaining ticks
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