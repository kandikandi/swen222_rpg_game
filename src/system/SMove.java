package system;

import component.CPosition;
import entity.GameEntity;
import game.GameController;

import java.util.Iterator;


/**
 * Created by cuan on 9/8/15.
 */
public class SMove implements GameSystem {
    private final GameController gameController;

    public SMove(GameController gameController) {
        this.gameController = gameController;
    }


    @Override
    public void performSystem() {
        boolean[] keyArray = gameController.keyArray;

        GameEntity entity;
        Iterator<GameEntity> it = gameController.getEntityIterator();
        while (it.hasNext()) {
            entity = it.next();
            if (entity.containsCompID(1)) {
                CPosition pos = (CPosition) entity.getComp(1);
                if (keyArray[0]) {
                    pos.ypos -= 3;
                }
                if (keyArray[1]) {
                    pos.ypos += 3;
                }
                if (keyArray[2]) {
                    pos.xpos -= 3;
                }
                if (keyArray[3]) {
                    pos.xpos += 3;
                }
            }
        }

    }



}
