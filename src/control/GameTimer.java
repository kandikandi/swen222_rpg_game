package control;

/**
 * Created by cuan on 9/9/15.
 */
public class GameTimer extends Thread {

//	private GameCamera camera;
//	private Renderer renderer;
	private EnemyController enemyController;
	private ServerControl serverControl;

	public GameTimer(EnemyController enemyController, ServerControl serverControl) {
//		this.camera = camera;
//		this.renderer = rend;
		this.serverControl = serverControl;
		this.enemyController = enemyController;
	}


	@Override
	public void run() {
		while (true) {
			try {
				Thread.sleep(40);

				//enemyController.update();
				//serverControl.updateClients();
				//camera.performSystem();

			} catch (Exception e) {
				System.out.println("GameTimer Error");
				e.printStackTrace();
			}
		}
	}
}