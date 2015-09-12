package control;

import model.Location;
import model.MoveableActor;


/**
 * Created by cuan on 9/13/15.
 */
public  class PlayerMoveStrategy extends MovementStrategy {
    private final MoveableActor moveableActor;
    private final GameController controller;

    public PlayerMoveStrategy(MoveableActor moveableActor, GameController controller) {
        this.moveableActor = moveableActor;
        this.controller = controller;
    }

    @Override
    public void executeMove() {
        boolean[] keyArray = controller.keyArray;
        Location location = moveableActor.getLocation();

        if (keyArray[0]) {
            location.setyPos(location.getyPos() - 3);
        }
        if (keyArray[1]) {
            location.setyPos(location.getyPos() + 3);
        }
        if (keyArray[2]) {
            location.setxPos(location.getxPos() - 3);
        }
        if (keyArray[3]) {
            location.setxPos(location.getxPos() + 3);
        }
    }
}

