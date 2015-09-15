package model;

import control.DIR;
import control.MovementStrategy;
import java.awt.*;

/**
 * Created by cuan on 9/15/15.
 */
public class Player extends StrategyActor{
    MovementStrategy movementStrategy;
    private final int speed = 6;

    public Player(ID id, Location location, Image image, boolean collidable, boolean drawable) {
        super(id, location, image, collidable, drawable);
    }

    @Override
    public void setMoveStrat(MovementStrategy moveStrat) {
        this.movementStrategy = moveStrat;
        moveStrat.setActor(this);
    }

    @Override
    public void tick() {
        movementStrategy.executeMove();
    }

    @Override
    public void move(DIR dir) {
        switch (dir){
            case UP:
                location.setyPos(location.getyPos() - speed);
                break;
            case DOWN:
                location.setyPos(location.getyPos() + speed);
                break;
            case LEFT:
                location.setxPos(location.getxPos() - speed);
                break;
            case RIGHT:
                location.setxPos(location.getxPos() + speed);
                break;
        }
    }

    @Override
    public boolean canMove(DIR dir) {
        return false;
    }


    @Override
    public MovementStrategy getMoveStrat() {
        return movementStrategy;
    }
}
