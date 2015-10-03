package Control;

import javax.swing.JFrame;

import View.Renderer;
import ui.GameCanvas;
import ui.GameFrame;

/**
 * Created by cuan on 9/9/15.
 */
public class GameTimer extends Thread {

	private Renderer renderer;

	public GameTimer(Renderer rend) {
		this.renderer = rend;
	}

	/**
	 * Main game loop!
	 *
	 */
	@Override
	public synchronized void run() {
		while (true) {
			try {
				Thread.sleep(10);
				renderer.renderScene();

			} catch (Exception e) {
				System.out.println("GameTimer Error");
				e.printStackTrace();
			}
		}
	}
}