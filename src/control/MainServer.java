package control;

import java.awt.EventQueue;

import save.DataStorage;
import model.GameState;

/**Responsible for starting up the server and initialising the game state, along with starting the game timer.
 * This class has no UI, it is a background process only.
 * @author mcleankand
 *
 */
public class MainServer {

	public static final int F_WIDTH = 1000;
	public static final int F_HEIGHT = 600;
	public static final int C_WIDTH = 800;
	public static final int C_HEIGHT = 600;
	public static final int TILE_SIZE = 40;
	public static final int NUM_TILE_COL = C_WIDTH / TILE_SIZE;
	public static final int NUM_TILE_ROW = C_HEIGHT / TILE_SIZE;
	public static final int NUM_WORLD_TILES = NUM_TILE_COL + NUM_TILE_ROW;
	public static final boolean TEST_MODE = true;
	public static final int PLAYER_SIZE = 45;
	public static final int ITEM_SIZE = 30;
	public static final int PLAYER_ONE_START_LOCATION_X = 75;
	public static final int PLAYER_ONE_START_LOCATION_Y = 75;
	public static final int PLAYER_TWO_START_LOCATION_X = 125;
	public static final int PLAYER_TWO_START_LOCATION_Y = 75;
	public static final boolean DRAW_HITBOXES = false;
	private static ServerControl socketServer;


	public MainServer(){
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				boolean isServer = true;
				socketServer = new ServerControl();
				socketServer.start();
				GameState gameState = new GameState(isServer);
				socketServer.setGame(gameState);
				EnemyController enemyController = new EnemyController(gameState);
				GameTimer gameTimer = new GameTimer(enemyController, socketServer);
				gameTimer.start();
			}

		});
	}

	/**For saving and loading games, server stores the most up to date version of the game state
	 *
	 */

	public GameState getGameForSave(){
		return socketServer.getGameState();
	}

	public void save() {
		DataStorage.save(getGameForSave());
	}

	public void load() {
		socketServer.getGameState().setActors(DataStorage.load().getActors());
	}
}


