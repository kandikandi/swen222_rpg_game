package control;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * The observer that waits for keypresses and informs the client of
 *what they are in the form of a string to send on to the server
 * @author mcleankand
 */
public class PlayerController implements KeyListener {


    private ClientControl thisPlayer;


    public PlayerController(ClientControl client) {
        thisPlayer = client;

    }


    /**Key pressed will react to starting to attack(space), and movement (up,down,...)
     * and rotate hot key (r)
     */
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

    /**Key released responds only to the space bar being released, as this will indicate that
     * the player has stopped attacking
     */
    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_SPACE:
                thisPlayer.sendKeyPress("SPACEUP");
                break;
        }

    }

    /**Key typed refers to entering in a specific rotation
     *
     */
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
