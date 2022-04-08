package dungeonmania.inventory;

public class Anduril extends Item implements OffensiveWeapon {

    int durability = 8;

    /**
     * Anduril class that inherits Item super class
     * 
     * @param type the type of Item
     */
    public Anduril(String type) {
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
