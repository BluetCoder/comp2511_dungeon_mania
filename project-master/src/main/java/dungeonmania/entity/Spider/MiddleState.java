package dungeonmania.entity.Spider;

import dungeonmania.util.Direction;

public class MiddleState implements SpiderMovement {
    Spider spider;

    /**
     * Passes spider class so it can move spider. Middle state is the Spider's
     * Initial position.
     * 
     * @param spider
     */
    public MiddleState(Spider spider) {
        this.spider = spider;
    }

    @Override
    public void setSpiderPosition() {
        if (spider.isClockwise()) {
            spider.moveTo(Direction.UP);
            spider.setMovementTopMiddle();
        } else {
            spider.moveTo(Direction.DOWN);
            spider.setMovementBottomMiddle();
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
