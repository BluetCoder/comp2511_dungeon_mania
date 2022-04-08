package dungeonmania.inventory;

import java.util.ArrayList;

public class Inventory {
    private ArrayList<Item> inventory;
    private boolean bowBuild;
    private boolean shieldBuild;
    private boolean sceptreBuild;
    private boolean midnightArmourBuild;

    public Inventory() {
        this.inventory = new ArrayList<Item>();
        this.bowBuild = false;
        this.shieldBuild = false;
        this.sceptreBuild = false;
        this.midnightArmourBuild = false;
    }

    /**
     * Getter for Player Inventory
     * 
     * @return List of Player's current Invenotyr
     */
    public ArrayList<Item> getInventory() {
        return this.inventory;
    }

    /**
     * Adds Item to Player Inventory
     * 
     * @param item Item to be added to Inventory
     */
    public void addInventory(Item item) {
        this.inventory.add(item);
    }

    /**
     * Remove Item from Player Inventory
     * 
     * @param item Item to be removed from Inventory
     */
    public void removeInventory(Item item) {
        this.inventory.remove(item);
    }

    /**
     * Checks if an Item exists in the Player Inventory
     * 
     * @param type type of Item
     * @return boolean on if the Item exists
     */
    public boolean itemExists(String type) {
        for (Item item : this.getInventory()) {
            if (item.getType().equals(type)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if the Player has sufficient materials to build certain Items Changes
     * the boolean for respective Items
     */
    public void canBuild() {
        int woodCount = itemCount("wood");
        int arrowCount = itemCount("arrow");
        int keyTreasureCount = itemCount("treasure") + itemCount("key");
        if (woodCount >= 1 && arrowCount >= 3) {
            this.setBowBuild(true);
        } else {
            this.setBowBuild(false);
        }

        if (woodCount >= 2 && keyTreasureCount >= 1) {
            this.setShieldBuild(true);
        } else if (woodCount >= 2 && this.itemExists("sun_stone")) {
            this.setShieldBuild(true);
        } else {
            this.setShieldBuild(false);
        }

        if (woodCount >= 1 || arrowCount >= 2) {
            if (keyTreasureCount >= 1 && this.itemExists("sun_stone")) {
                this.setSceptreBuild(true);
            } else {
                this.setSceptreBuild(false);
            }
        } else {
            this.setSceptreBuild(false);
        }

        if (this.itemExists("armour") && this.itemExists("sun_stone")) {
            this.setMidnightArmourBuild(true);
        } else {
            this.setMidnightArmourBuild(false);
        }

    }

    /**
     * Setter for bowBuild
     * 
     * @param builable boolean on if Bow is buildable
     */
    public void setBowBuild(boolean builable) {
        this.bowBuild = builable;
    }

    /**
     * Setter for shieldBuild
     * 
     * @param builable boolean on if Shield is buildable
     */
    public void setShieldBuild(boolean builable) {
        this.shieldBuild = builable;
    }

    /**
     * Setter for sceptreBuild
     * 
     * @param builable boolean on if Sceptre is buildable
     */
    public void setSceptreBuild(boolean builable) {
        this.sceptreBuild = builable;
    }

    /**
     * Setter for midnightArmourBuild
     * 
     * @param builable boolean on if midgnight Armour is buildable
     */
    public void setMidnightArmourBuild(boolean builable) {
        this.midnightArmourBuild = builable;
    }

    /**
     * Getter for bowBuild
     * 
     * @return boolean on if bow is buildable
     */
    public boolean getBowBuild() {
        return this.bowBuild;
    }

    /**
     * Getter for shieldBuild
     * 
     * @return boolean on if shield is buildable
     */
    public boolean getShieldBuild() {
        return this.shieldBuild;
    }

    /**
     * Getter for sceptreBuild
     * 
     * @return boolean on if sceptre is buildable
     */
    public boolean getSceptreBuild() {
        return this.sceptreBuild;
    }

    /**
     * Getter for midnightArmourBuild
     * 
     * @return boolean on if Minight Armour is buildable
     */
    public boolean getMidnightArmourBuild() {
        return this.midnightArmourBuild;
    }

    /**
     * Counter for treasure
     * 
     * @return int of treasure in inventory
     */
    public int itemCount(String type) {
        int counter = 0;
        for (Item item : this.getInventory()) {
            if (item.getType().equals(type)) {
                counter++;
            }
        }
        return counter;
    }

    /**
     * Getter for item of certain type
     */
    public Item itemGetter(String type) {
        for (Item item : this.getInventory()) {
            if (item.getType().equals(type)) {
                return item;
            }
        }
        return null;
    }
}
