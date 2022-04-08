package dungeonmania.entity.Spider;

import dungeonmania.util.Direction;

public interface SpiderMovement {
    /**
     * Sets Spider movement depending on state Also sets the next state
     */
    public void setSpiderPosition();

    /**
     * Gets the supposed next direction for the spiders rotating movement, depends
     * on clockwise/anticlockwise movement
     * 
     * @return
     */
    public Direction getNextDirection();
}
