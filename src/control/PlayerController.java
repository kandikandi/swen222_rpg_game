package control;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * The observer that waits for keypresses and sends them to the client to send on
 *
 * @author mcleankand
 */
public class PlayerController implements KeyListener {


    private ClientControl thisPlayer;


    public PlayerController(ClientControl client) {
        thisPlayer = client;

    }


    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                thisPlayer.sendKeyPress("UP");
                break;

            case KeyEvent.VK_DOWN:
                thisPlayer.sendKeyPress("DOWN");
                break;

            case KeyEvent.VK_LEFT:
                thisPlayer.sendKeyPress("LEFT");
                break;

            case KeyEvent.VK_RIGHT:
                thisPlayer.sendKeyPress("RIGHT");
                break;

            case KeyEvent.VK_SPACE:
                //System.out.println("PlayerController keyPressed: SPACE");
                thisPlayer.sendKeyPress("SPACEDOWN");
                break;
            case KeyEvent.VK_R:
                System.out.println("PlayerController keyPressed: R");
                Main.ROTATION+=1;
                if (Main.ROTATION >= 4){Main.ROTATION = 0;}
                break;
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_SPACE:
                //System.out.println("PlayerController keyPressed: SPACE");
                thisPlayer.sendKeyPress("SPACEUP");
                break;
        }

    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

}
