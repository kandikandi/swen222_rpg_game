package model;

import control.DIR;
import control.MovementStrategy;
import java.awt.*;

/**
 * Created by cuan on 9/13/15.
 *
 * An ActorStrategy extends Actor and allows us to define Actor behaviour
 * depending on whether it is player-controlled, or controlled by the system,
 * and allows for non-player behaviour to be easily extended.
 *
 */
public abstract class ActorStrategy extends Actor {
    private MovementStrategy movementStrategy;

    public ActorStrategy(ID id, Position position, Image image, boolean collidable, boolean drawable, int boundingBoxSize) {
        super(id, position, image, collidable, drawable, boundingBoxSize);
//        this.movementStrategy = movementStrategy;
    }



    abstract public void move(DIR dir);

    /**
     * This method accepts a direction as an argument and returns whether
     * this Actor can move in that direction.
     *
     * @param dir
     * @return whether can move in that direction
     */
    abstract public boolean canMove(DIR dir);


    /**
     * Setter for movement strategy.
     *
     * @param moveStrat
     */
    abstract public void setMoveStrat(MovementStrategy moveStrat);

    /**
     * Getter for movement strategy.
     *
     * @return
     */
    abstract public MovementStrategy getMoveStrat();

}
