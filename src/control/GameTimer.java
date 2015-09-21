package control;

import javax.swing.JFrame;

import model.GameState;
import model.Player;
import ui.GameCanvas;
import ui.GameFrame;

/**
 * Created by cuan on 9/9/15.
 */
public class GameTimer extends Thread {
	private final GameController gameController;


	public GameTimer(GameController gameController) {
		this.gameController = gameController;
	}



	/**
	 * Main game loop!
	 *
	 */
	public void run() {
		while (true) {
			try {
				Thread.sleep(20);
				gameController.executeAllSystems(); // updates every actor and
													// game system
			} catch (Exception e) {
				System.out.println("GameTimer Error");
				e.printStackTrace();
			}
		}
	}
}