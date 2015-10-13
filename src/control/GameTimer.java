package control;

import view.GameCamera;
import view.Renderer;

/**
 * Created by cuan on 9/9/15.
 */
public class GameTimer extends Thread {

//	private GameCamera camera;
//	private Renderer renderer;
	private EnemyController enemyController;
	private GameServer gameServer;

	public GameTimer(EnemyController enemyController, GameServer gameServer) {
//		this.camera = camera;
//		this.renderer = rend;
		this.gameServer = gameServer;
		this.enemyController = enemyController;
	}


	@Override
	public void run() {
		while (true) {
			try {
				Thread.sleep(40);

				//enemyController.update();
				//gameServer.updateClients();
				//camera.performSystem();

			} catch (Exception e) {
				System.out.println("GameTimer Error");
				e.printStackTrace();
			}
		}
	}
}