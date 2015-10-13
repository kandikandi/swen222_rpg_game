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
                thisPlayer.sendKeyPress(getRotatedUp());
                break;

            case KeyEvent.VK_DOWN:
                thisPlayer.sendKeyPress(getRotatedDown());
                break;

            case KeyEvent.VK_LEFT:
                thisPlayer.sendKeyPress(getRotatedLeft());
                break;

            case KeyEvent.VK_RIGHT:
                thisPlayer.sendKeyPress(getRotatedRight());
                break;

            case KeyEvent.VK_SPACE:
                thisPlayer.sendKeyPress("SPACEDOWN");
                break;
            case KeyEvent.VK_R:
                Main.ROTATION += 1;
                if (Main.ROTATION >= 4) {
                    Main.ROTATION = 0;
                }
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

    private String getRotatedUp() {
        switch (Main.ROTATION) {
            case 0:
                return "UP";
            case 1:
                return "RIGHT";
            case 2:
                return "DOWN";
            case 3:
                return "LEFT";
            default:
                return "UP";
        }
    }
    private String getRotatedDown(){
        switch (Main.ROTATION){
            case 0:
                return  "DOWN";
            case 1:
                return  "LEFT";
            case 2:
                return  "UP";
            case 3:
                return "RIGHT";
            default:
                return  "DOWN";
        }
    }
    private String getRotatedLeft(){
        switch (Main.ROTATION){
            case 0:
                return  "LEFT";
            case 1:
                return  "UP";
            case 2:
                return  "RIGHT";
            case 3:
                return "DOWN";
            default:
                return  "LEFT";
        }
    }
    private String getRotatedRight(){
        switch (Main.ROTATION){
            case 0:
                return  "RIGHT";
            case 1:
                return  "DOWN";
            case 2:
                return  "LEFT";
            case 3:
                return "UP";
            default:
                return  "RIGHT";
        }
    }

}
