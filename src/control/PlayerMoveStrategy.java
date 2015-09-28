package control;

import model.Position;
import model.ActorStrategy;


/**
 * Player movement strategy.
 *
 */
public  class PlayerMoveStrategy extends MovementStrategy {
    private  ActorStrategy strategyActor;
    private final GameKeyListener keyListener;

    public PlayerMoveStrategy(GameKeyListener keyListener) {
        this.keyListener = keyListener;
    }

    @Override
    public void setActor(ActorStrategy actor){
        this.strategyActor = actor;
    }

    @Override
    public void executeMove() {
        boolean[] keyArray = keyListener.keyArray;

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
        if (keyArray[4]) {
            strategyActor.setAttack(true);
        }
    }
}

