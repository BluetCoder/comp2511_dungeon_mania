package dungeonmania.entity;

import dungeonmania.inventory.OffensiveWeapon;

public interface BattleInterface {

    /**
     * Returns the health of the enemy
     * 
     * @return
     */
    public double getHealth();

    /**
     * Returns that attack damage of the enemy
     * 
     * @return
     */
    public double getAttack();

    /**
     * Method used to set moving entity health after a battle round has taken place
     * 
     * @param newHealth New Health that we set entities health at
     * @param weakness  Boolean that returns true of false based on whether or not
     *                  entity has a weakness to equipment type
     */
    public void setHealthAfterDamage(double newHealth, Boolean weakness);

    /**
     * Method to priotise the weapon that has the weakness buff during battle rounds
     * 
     * @param weakness
     * @return
     */
    public OffensiveWeapon getOffensiveWeapon(String weakness);

    /**
     * Method to get weapon from player's inventory to use in battle
     * 
     * @return
     */
    public OffensiveWeapon getOffensiveWeapon();

    /**
     * Return damage reduction based on armour and shield propeties
     * 
     * @return
     */
    public double getDefenceReduction();

    /**
     * Returns string that correalates to weapong type, specfically applies to
     * weapns that have a weakness buff
     * 
     * @return
     */
    public String getWeaponWeakness();

    /**
     * For midnight armour, armour not only applies a damage redutcion but increases
     * users attack stats, returns value of attack modifier
     * 
     * @return
     */
    public double getArmourAttack();

}
