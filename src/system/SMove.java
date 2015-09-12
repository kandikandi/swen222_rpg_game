package system;

import control.GameController;
import model.AbstractActor;
import model.AbstractGameObject;
import model.Location;


/**
 * Created by cuan on 9/8/15.
 */
public class SMove implements GameSystem {
    private final GameController gameController;
    private final AbstractGameObject actor;

    public SMove(GameController gameController, AbstractGameObject actor) {
        this.gameController = gameController;
        this.actor = actor;
    }


    @Override
    public void performSystem() {
        boolean[] keyArray = gameController.keyArray;
        Location loc = actor.getLocation();


        if (keyArray[0]) {
            loc.setyPos(loc.getyPos()-3);
        }
        if (keyArray[1]) {
            loc.setyPos(loc.getyPos()+3);
        }
        if (keyArray[2]) {
            loc.setxPos(loc.getxPos()-3);
        }
        if (keyArray[3]) {
            loc.setxPos(loc.getxPos()+3);
        }


    }


}
