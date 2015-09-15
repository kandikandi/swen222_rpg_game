package control;

import model.Location;
import model.StrategyActor;


/**
 * Player movement strategy.
 *
 */
public  class PlayerMoveStrategy extends MovementStrategy {
    private  StrategyActor strategyActor;
    private final GameController controller;

    public PlayerMoveStrategy(GameController controller) {
        this.controller = controller;
    }

    @Override
    public void setActor(StrategyActor actor){
        this.strategyActor = actor;
    }

    @Override
    public void executeMove() {
        boolean[] keyArray = controller.keyArray;

        if (keyArray[0]) {
            strategyActor.move(DIR.UP);
        }
        if (keyArray[1]) {
            strategyActor.move(DIR.DOWN);
        }
        if (keyArray[2]) {
            strategyActor.move(DIR.LEFT);
        }
        if (keyArray[3]) {
            strategyActor.move(DIR.RIGHT);
        }
    }
}

