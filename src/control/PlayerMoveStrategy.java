package control;

import model.Location;
import model.StrategyActor;


/**
 * Created by cuan on 9/13/15.
 */
public  class PlayerMoveStrategy extends MovementStrategy {
    private final StrategyActor strategyActor;
    private final GameController controller;

    public PlayerMoveStrategy(StrategyActor strategyActor, GameController controller) {
        this.strategyActor = strategyActor;
        this.controller = controller;
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

