package dungeonmania.entity.Spider;

import dungeonmania.util.Direction;

public class TopRightState implements SpiderMovement {
    Spider spider;

    /**
     * Passes spider class so it can move spider.
     * 
     * @param spider
     */
    public TopRightState(Spider spider) {
        this.spider = spider;
    }

    @Override
    public void setSpiderPosition() {
        if (spider.isClockwise()) {
            spider.moveTo(Direction.DOWN);
            spider.setMovementMiddleRight();
        } else {
            spider.moveTo(Direction.LEFT);
            spider.setMovementTopMiddle();
        }
    }

    @Override
    public Direction getNextDirection() {
        if (spider.isClockwise()) {
            return Direction.DOWN;
        } else {
            return Direction.LEFT;
        }
    }

}
