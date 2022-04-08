package dungeonmania.entity.Spider;

import dungeonmania.util.Direction;

public class MiddleRightState implements SpiderMovement {
    Spider spider;

    /**
     * Passes spider class so it can move spider.
     * 
     * @param spider
     */
    public MiddleRightState(Spider spider) {
        this.spider = spider;
    }

    @Override
    public void setSpiderPosition() {
        if (spider.isClockwise()) {
            spider.moveTo(Direction.DOWN);
            spider.setMovementBottomRight();
        } else {
            spider.moveTo(Direction.UP);
            spider.setMovementTopRight();
        }
    }

    @Override
    public Direction getNextDirection() {
        if (spider.isClockwise()) {
            return Direction.DOWN;
        } else {
            return Direction.UP;
        }
    }
}
