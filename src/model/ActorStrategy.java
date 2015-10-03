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
    protected MovementStrategy movementStrategy;
    private int speed;
    boolean isMoving;
    private DIR movingDirection;


    public ActorStrategy(ID id, Position position, char imagePath, boolean collidable, boolean drawable, int boundingBoxSize) {
        super(id, position, imagePath, collidable, drawable, boundingBoxSize);
        isMoving = false;
    }


	/**
	 * This method accepts a direction as an argument and moves this object
	 * in that direction subject to collisions.
	 *
	 *
	 */
    abstract public void move(DIR dir);

    /**
     * Setter for movement strategy.
     *
     *
     */
    abstract public void setMoveStrat(MovementStrategy movementStrategy);
    /*public void setMoveStrat(MovementStrategy movementStrategy){
        this.movementStrategy = movementStrategy;
        //movementStrategy.setActor(this); // will this work..... nope why java why???
    }*/

    /**
     * Getter for movement strategy.
     *
     */
    public MovementStrategy getMoveStrat(){
        return movementStrategy;
    }

    abstract public void setAttack(boolean bool);

    public int getSpeed() {
        return speed;
    }

    public boolean isMoving() {
        return isMoving;
    }

    public DIR getDirection() {
        return movingDirection;
    }
}
