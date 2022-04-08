package dungeonmania.inventory;

public interface DefensiveWeapon {
    /**
     * Returns damage redution stats
     * 
     * @return
     */
    public double getDamageReduction();

    /**
     * Returns item durability
     * 
     * @return
     */
    public int getDurability();

    /**
     * Method that reduces item durability
     */
    public void reduceDurability();

    /**
     * For specific armour get attack increase stat
     * 
     * @return
     */
    public double getArmourAttackIncrease();
}
