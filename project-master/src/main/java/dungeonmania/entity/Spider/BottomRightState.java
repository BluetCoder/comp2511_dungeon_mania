package dungeonmania.entity.Spider;

import dungeonmania.util.Direction;

public class BottomRightState implements SpiderMovement {
    Spider spider;

    /**
     * Passes spider class so it can move spider.
     * 
     * @param spider
     */
    public BottomRightState(Spider spider) {
        this.spider = spider;
    }

    @Override
    public void setSpiderPosition() {
        if (spider.isClockwise()) {
            spider.moveTo(Direction.LEFT);
            spider.setMovementBottomMiddle();
        } else {
            spider.moveTo(Direction.UP);
            spider.setMovementMiddleRight();
        }
    }

    @Override
    public Direction getNextDirection() {
        if (spider.isClockwise()) {
            return Direction.LEFT;
        } else {
            return Direction.UP;
        }
    }
}
