package control;

import model.Position;
import model.ActorStrategy;


/**
 * Player movement strategy.
 *
 */
public  class PlayerMoveStrategy extends MovementStrategy {
    private  ActorStrategy strategyActor;
    private final GameController gameController;
    private final GameKeyListener keyListener;

    public PlayerMoveStrategy(GameController gameController) {
        this.gameController = gameController;
        keyListener = gameController.getKeyListener();
    }

    @Override
    public void setActor(ActorStrategy actor){
        this.strategyActor = actor;
    }

    @Override
    public void executeMove() {
        //System.out.println("PlayerMoveStrategy: executeMove");
        boolean[] keyArray = keyListener.getKeyArray();

        if (keyArray[0]) {
            strategyActor.move(DIR.UP);
            //strategyActor.isMo
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
        if (!keyArray[4]) {
            strategyActor.setAttack(false);
        }
    }
}

