package control;

import control.GameController;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.Serializable;

/**
 * Created by cuan on 9/10/15.
 */
public class GameKeyListener implements KeyListener, Serializable {
    private final int NUM_KEYS = 5;
    private final boolean[] keyArray = new boolean[NUM_KEYS];

    protected boolean[] getKeyArray(){
        return keyArray;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                keyArray[0] = true;
                break;

            case KeyEvent.VK_DOWN:
                keyArray[1] = true;
                break;

            case KeyEvent.VK_LEFT:
                keyArray[2] = true;
                break;

            case KeyEvent.VK_RIGHT:
                keyArray[3] = true;
                break;

            case KeyEvent.VK_SPACE:
                keyArray[4] = true;
                break;
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                keyArray[0] = false;
                break;

            case KeyEvent.VK_DOWN:
                keyArray[1] = false;
                break;

            case KeyEvent.VK_LEFT:
                keyArray[2] = false;
                break;

            case KeyEvent.VK_RIGHT:
                keyArray[3] = false;
                break;

            case KeyEvent.VK_SPACE:
                keyArray[4] = false;
                break;
        }

    }

    @Override
    public void keyTyped(KeyEvent e) {
    }
}
