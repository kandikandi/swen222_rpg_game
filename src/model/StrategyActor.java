package model;

import control.DIR;
import control.MovementStrategy;
import java.awt.*;

/**
 * Created by cuan on 9/13/15.
 */
public abstract class StrategyActor extends Actor {
    private MovementStrategy movementStrategy;

    public StrategyActor(ID id, Location location, Image image, boolean collidable, boolean drawable) {
        super(id, location, image, collidable, drawable);
    }

    abstract public void setMoveStrat(MovementStrategy moveStrat);

    abstract public void move(DIR dir);

    abstract public boolean canMove(DIR dir);

    abstract public MovementStrategy getMoveStrat();

}
