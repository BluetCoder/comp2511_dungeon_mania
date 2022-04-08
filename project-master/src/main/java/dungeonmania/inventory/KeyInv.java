package dungeonmania.inventory;

public class KeyInv extends Item {
    private int keyNum;
    /**
     * KeyInv class that inherits Item super class
     * 
     * @param type the type of Item
    */

    public KeyInv(String type, int keyNum) {
        super(type);
        this.keyNum = keyNum;
    }

    public int getKeyNum() {
        return keyNum;
    }
    
}
