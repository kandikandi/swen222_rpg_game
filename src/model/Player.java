package model;

import control.DIR;
import control.MovementStrategy;

import java.awt.*;

/**
 * Created by cuan on 9/15/15.
 */
public class Player extends StrategyActor{
    MovementStrategy movementStrategy;
    private final int speed = 3;

    public Player(ID id, Location location, Image image, boolean collidable, boolean drawable) {
        super(id, location, image, collidable, drawable);
    }

    @Override
    public void tick() {
        movementStrategy.executeMove();
    }

    @Override
    public void move(DIR dir) {
        switch (dir){
            case UP:
                location.setyPos(location.getyPos() - 3);
                break;
            case DOWN:
                location.setyPos(location.getyPos() + 3);
                break;
            case LEFT:
                location.setxPos(location.getxPos() - 3);
                break;
            case RIGHT:
                location.setxPos(location.getxPos() + 3);
                break;
        }
    }

    @Override
    public boolean canMove(DIR dir) {
        return false;
    }

    @Override
    public void setMoveStrat(MovementStrategy ms) {
        this.movementStrategy = ms;
    }

    @Override
    public MovementStrategy getMoveStrat() {
        return movementStrategy;
    }
}
