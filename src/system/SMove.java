package system;

import component.CPosition;
import entity.Entity;
import game.GameController;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Iterator;


/**
 * Created by cuan on 9/8/15.
 */
public class SMove implements KeyListener, GameSystem {
    private final GameController gameController;
    private final int NUM_KEYS = 4;
    boolean[] keyArray = new boolean[NUM_KEYS];

    public SMove(GameController gameController) {
        this.gameController = gameController;
    }

    @Override
    public void performSystem() {
        Entity entity;
        Iterator<Entity> it = gameController.getEntityIterator();
        while (it.hasNext()) {
            entity = it.next();
            if (entity.containsCompID(1)) {
                CPosition pos = (CPosition) entity.getComp(1);
                if (keyArray[0]) {
                    pos.ypos -= 10;
                }
                if (keyArray[1]) {
                    pos.ypos += 10;
                }
                if (keyArray[2]) {
                    pos.xpos -= 10;
                }
                if (keyArray[3]) {
                    pos.xpos += 10;
                }
            }
        }

    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                keyArray[0] = true;
                //System.out.println("SMove UP pressed");
                break;

            case KeyEvent.VK_DOWN:
                keyArray[1] = true;
                //System.out.println("SMove DOWN pressed");
                break;

            case KeyEvent.VK_LEFT:
                keyArray[2] = true;
                //System.out.println("SMove LEFT pressed");
                break;

            case KeyEvent.VK_RIGHT:
                keyArray[3] = true;
                //System.out.println("SMove RIGHT pressed");
                break;
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                keyArray[0] = false;
                //System.out.println("SMove UP pressed");
                break;

            case KeyEvent.VK_DOWN:
                keyArray[1] = false;
                //System.out.println("SMove DOWN pressed");
                break;

            case KeyEvent.VK_LEFT:
                keyArray[2] = false;
                //System.out.println("SMove LEFT pressed");
                break;

            case KeyEvent.VK_RIGHT:
                keyArray[3] = false;
                //System.out.println("SMove RIGHT pressed");
                break;
        }

    }
}
