package dungeonmania.entity;

import dungeonmania.util.Position;

public class FloorSwitch extends Entity {
    private boolean isToggledByBoulder;

    /**
     * FloorSwitch class that inherits Entity super class
     * 
     * @param id
     * @param type
     * @param position
     * @param isInteractable
     * @param isCollidable
     */
    public FloorSwitch(int id, String type, Position position, boolean isInteractable, boolean isCollidable) {
        super(id, type, position, isInteractable, isCollidable);
    }

    /**
     * Boolean that returns whether the switch has been toggled by a boulder
     * 
     * @return
     */
    public boolean isToggledByBoulder() {
        return isToggledByBoulder;
    }

    /**
     * Set the varibale to be tru or false based on position of boulder relative to
     * the switch
     * 
     * @param isToggledByBoulder
     */
    public void setToggledByBoulder(boolean isToggledByBoulder) {
        this.isToggledByBoulder = isToggledByBoulder;
    }

}
