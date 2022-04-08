package dungeonmania.inventory;

import dungeonmania.entity.Player;

public class MidnightArmour extends Item implements BuildInterface, DefensiveWeapon {
    int durability = 8;

    /**
     * ArmourInv class that inherits ArmourInv class
     * 
     * @param type the type of Item
     */
    public MidnightArmour(String type) {
        super(type);
    }

    @Override
    public void build(Player player) {
        player.getInventory().addInventory(this);
        player.getInventory().removeInventory(player.getInventory().itemGetter("armour"));
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
        return 1.25;
    }
}
