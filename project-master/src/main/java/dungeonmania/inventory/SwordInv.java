package dungeonmania.inventory;

public class SwordInv extends Item implements OffensiveWeapon {
    /**
     * SwordInv class that inherits Item super class
     * 
     * @param type the type of Item
     */
    int durability = 4;

    public SwordInv(String type) {
        super(type);
    }

    @Override
    public double getAttackModifier() {
        return 1.5;
    }

    @Override
    public int getDurability() {
        return durability;
    }

    @Override
    public void reduceDurability() {
        durability--;
    }
}
