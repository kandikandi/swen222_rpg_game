package control;

import model.StrategyActor;

/**
 * Strategies will be executed when Strategy-Actor is called to tick()
 * by the GameController
 */
public abstract class MovementStrategy {
    abstract public void executeMove();
    abstract public void setActor(StrategyActor actor);
}
