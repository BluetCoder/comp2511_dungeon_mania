package dungeonmania.inventory;

public class ArmourInv extends Item implements DefensiveWeapon {
    int durability = 4;

    /**
     * ArmourInv class that inherits Item super class
     * 
     * @param type the type of Item
     */
    public ArmourInv(String type) {
        super(type);
    }

    @Override
    public double getDamageReduction() {
        return 2;
    }

    @Override
    public int getDurability() {
        return durability;
    }

    @Override
    public void reduceDurability() {
        durability--;
    }

    @Override
    public double getArmourAttackIncrease() {
        return 0;
    }
}
