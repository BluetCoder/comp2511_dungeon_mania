package dungeonmania.inventory;

public interface OffensiveWeapon {
    /**
     * Get weapon attack stats
     * 
     * @return
     */
    public double getAttackModifier();

    /**
     * Get weapon durability
     * 
     * @return
     */
    public int getDurability();

    /**
     * Method that reduces weapon durability
     */
    public void reduceDurability();
}
