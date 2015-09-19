package control;

import control.GameController;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Created by cuan on 9/10/15.
 *
 *
 */
public class GameKeyListener implements KeyListener {
    //private GameController gameController;
    private final int NUM_KEYS = 4;
    public boolean[] keyArray = new boolean[NUM_KEYS];

    public GameKeyListener(/*GameController gameController*/){
        /*this.gameController = gameController;*/
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                /*gameController.*/keyArray[0] = true;
                break;

            case KeyEvent.VK_DOWN:
                /*gameController.*/keyArray[1] = true;
                break;

            case KeyEvent.VK_LEFT:
                /*gameController.*/keyArray[2] = true;
                break;

            case KeyEvent.VK_RIGHT:
               /*gameController.*/keyArray[3] = true;
                break;
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                /*gameController.*/keyArray[0] = false;
                break;

            case KeyEvent.VK_DOWN:
                /*gameController.*/keyArray[1] = false;
                break;

            case KeyEvent.VK_LEFT:
                /*gameController.*/keyArray[2] = false;
                break;

            case KeyEvent.VK_RIGHT:
                /*gameController.*/keyArray[3] = false;
                break;
        }

    }

    @Override
    public void keyTyped(KeyEvent e) {
    }
}
