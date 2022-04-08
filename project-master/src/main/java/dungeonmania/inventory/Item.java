package dungeonmania.inventory;

public class Item {
    /**
     * This is the name for type of Item
     */
    private String type;

    /**
     * Constructor for Item class
     * 
     * @param type the type of item
     */
    public Item(String type) {
        this.type = type;
    }

    /**
     * Getter for the type of Item
     * 
     * @return type of Item
     */
    public String getType() {
        return type;
    }
}