package dungeonmania.entity;

public interface EnemyObserver {
    /**
     * Changes enemy position when player moves by calling the method for enemy
     * movement.
     */
    public void updateEnemyMovement();

    /**
     * Changes enemy position when player moves by calling the method for enemy
     * movement while player is invincible.
     */
    public void updateEnemyMovementInvincible(Player player);
}
