package control;

import model.ActorStrategy;

/**
 * Strategies will be executed when Strategy-Actor is called to tick()
 * by the GameController
 */
public abstract class MovementStrategy {
    abstract public void executeMove();
    abstract public void setActor(ActorStrategy actor);
}
