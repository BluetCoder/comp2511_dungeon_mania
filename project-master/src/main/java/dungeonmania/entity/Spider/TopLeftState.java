package dungeonmania.entity.Spider;

import dungeonmania.util.Direction;

public class TopLeftState implements SpiderMovement {
    Spider spider;

    /**
     * Passes spider class so it can move spider.
     * 
     * @param spider
     */
    public TopLeftState(Spider spider) {
        this.spider = spider;
    }

    @Override
    public void setSpiderPosition() {
        if (spider.isClockwise()) {
            spider.moveTo(Direction.RIGHT);
            spider.setMovementTopMiddle();
        } else {
            spider.moveTo(Direction.DOWN);
            spider.setMovementMiddleLeft();
        }
    }

    @Override
    public Direction getNextDirection() {
        if (spider.isClockwise()) {
            return Direction.RIGHT;
        } else {
            return Direction.DOWN;
        }
    }
}
