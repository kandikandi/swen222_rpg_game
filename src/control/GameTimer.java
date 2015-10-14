package control;

/**GameTimer is the game clock. It is responsible for updating the servers gamestate with enemy movement
 * as well as instructing the server to send updates to its clients on a regular loop.
 * @authors mcleankand && lategacuan
 */
public class GameTimer extends Thread {


	private EnemyController enemyController;
	private ServerControl serverControl;

	public GameTimer(EnemyController enemyController, ServerControl serverControl) {

		this.serverControl = serverControl;
		this.enemyController = enemyController;
	}


	@Override
	public void run() {
		while (true) {
			try {
				Thread.sleep(33);

				enemyController.update();
				serverControl.updateClients();


			} catch (Exception e) {
				System.out.println("GameTimer Error");
				e.printStackTrace();
			}
		}
	}
}