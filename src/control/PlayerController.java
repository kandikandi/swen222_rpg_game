package control;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class PlayerController implements KeyListener  {


	private GameClient thisPlayer;


	public PlayerController(GameClient client){
		thisPlayer = client;

	}


	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()){
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
			System.out.println("PlayerController keyPressed: SPACE");
			thisPlayer.sendKeyPress("SPACE");
            break;
		}

	}

	@Override
	public void keyReleased(KeyEvent e) {

		/*switch (e.getKeyCode()){
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
        	thisPlayer.sendKeyPress("SPACE");
            break;
		}*/
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

}
