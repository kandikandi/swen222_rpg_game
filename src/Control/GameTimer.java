package Control;

import javax.swing.JFrame;

import View.GameCamera;
import View.Renderer;
import ui.GameCanvas;
import ui.GameFrame;

/**
 * Created by cuan on 9/9/15.
 */
public class GameTimer extends Thread {

	private Renderer renderer;
	private EnemyController enemyController;
	
	private GameCamera camera;

	public GameTimer(Renderer rend, EnemyController enemyController) {
		this.renderer = rend;
		this.enemyController = enemyController;
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
				
				
				enemyController.update();
				
				camera.performSystem();
				
				renderer.renderScene();

			} catch (Exception e) {
				System.out.println("GameTimer Error");
				e.printStackTrace();
			}
		}
	}
}