package dungeonmania.entity.Spider;

import dungeonmania.util.Direction;

public class BottomMiddleState implements SpiderMovement {
    Spider spider;

    /**
     * Passes spider class so it can move spider.
     * 
     * @param spider
     */
    public BottomMiddleState(Spider spider) {
        this.spider = spider;
    }

    @Override
    public void setSpiderPosition() {
        if (spider.isClockwise()) {
            spider.moveTo(Direction.LEFT);
            spider.setMovementBottomLeft();
        } else {
            spider.moveTo(Direction.RIGHT);
            spider.setMovementBottomRight();
        }
    }

    @Override
    public Direction getNextDirection() {
        if (spider.isClockwise()) {
            return Direction.LEFT;
        } else {
            return Direction.RIGHT;
        }
    }

}
