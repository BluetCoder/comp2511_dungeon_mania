package dungeonmania.entity.Spider;

import dungeonmania.util.Direction;

public class MiddleLeftState implements SpiderMovement {
    Spider spider;

    /**
     * Passes spider class so it can move spider.
     * 
     * @param spider
     */
    public MiddleLeftState(Spider spider) {
        this.spider = spider;
    }

    @Override
    public void setSpiderPosition() {
        if (spider.isClockwise()) {
            spider.moveTo(Direction.UP);
            spider.setMovementTopLeft();
        } else {
            spider.moveTo(Direction.DOWN);
            spider.setMovementBottomLeft();
        }
    }

    @Override
    public Direction getNextDirection() {
        if (spider.isClockwise()) {
            return Direction.UP;
        } else {
            return Direction.DOWN;
        }
    }
}
