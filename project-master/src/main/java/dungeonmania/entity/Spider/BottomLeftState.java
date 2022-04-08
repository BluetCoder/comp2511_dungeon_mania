package dungeonmania.entity.Spider;

import dungeonmania.util.Direction;

public class BottomLeftState implements SpiderMovement {
    Spider spider;

    /**
     * Passes spider class so it can move spider.
     * 
     * @param spider
     */
    public BottomLeftState(Spider spider) {
        this.spider = spider;
    }

    @Override
    public void setSpiderPosition() {
        if (spider.isClockwise()) {
            spider.moveTo(Direction.UP);
            spider.setMovementMiddleLeft();
        } else {
            spider.moveTo(Direction.RIGHT);
            spider.setMovementBottomMiddle();
        }
    }

    @Override
    public Direction getNextDirection() {
        if (spider.isClockwise()) {
            return Direction.UP;
        } else {
            return Direction.RIGHT;
        }
    }
}
