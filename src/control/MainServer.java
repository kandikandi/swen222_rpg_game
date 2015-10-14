package control;

import java.awt.EventQueue;

import save.DataStorage;
import model.GameState;

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





	public static void shutDownServer(){
		System.out.println("MainServer: shutDownServer() called");
		socketServer.shutDownServer();
		System.exit(0);
	}

	public static void main(String[] args) {
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

	//TODO for you bonnie
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


