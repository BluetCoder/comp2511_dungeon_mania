package dungeonmania.entity.Spider;

import dungeonmania.util.Direction;

public class TopMiddleState implements SpiderMovement {
    Spider spider;

    /**
     * Passes spider class so it can move spider.
     * 
     * @param spider
     */
    public TopMiddleState(Spider spider) {
        this.spider = spider;
    }

    @Override
    public void setSpiderPosition() {
        if (spider.isClockwise()) {
            spider.moveTo(Direction.RIGHT);
            spider.setMovementTopRight();
        } else {
            spider.moveTo(Direction.LEFT);
            spider.setMovementTopLeft();
        }
    }
    @ Override
    public Direction getNextDirection() {
        if (spider.isClockwise()) {
            return Direction.RIGHT;
        } else {
            return Direction.LEFT;
        }
    }

}
