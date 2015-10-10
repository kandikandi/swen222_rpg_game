package control;

import view.GameCamera;
import view.Renderer;

/**
 * Created by cuan on 9/9/15.
 */
public class GameTimer extends Thread {

	private GameCamera camera;
	private Renderer renderer;
	//private EnemyController enemyController;

	public GameTimer(GameCamera camera, Renderer rend/*, EnemyController enemyController*/) {
		this.camera = camera;
		this.renderer = rend;
		//this.enemyController = enemyController;
	}

	/**
	 * Main game loop!
	 *
	 */
	@Override
	public void run() {
		while (true) {
			try {
				Thread.sleep(10);


				//enemyController.update();

				//not needed camera.performSystem();

				//renderer.renderScene();

			} catch (Exception e) {
				System.out.println("GameTimer Error");
				e.printStackTrace();
			}
		}
	}
}