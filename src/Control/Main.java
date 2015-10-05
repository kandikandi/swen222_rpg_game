package Control;

import java.awt.EventQueue;
import java.awt.event.KeyListener;
import java.util.List;

import javax.swing.JOptionPane;

import View.GameCamera;
import ui.GameCanvas;
import ui.GameFrame;
import Model.GameState;
import View.Renderer;

public class Main {

	public static final int F_WIDTH = 1000;
	public static final int F_HEIGHT = 600;
	public static final int C_WIDTH = 800;
	public static final int C_HEIGHT = 600;
	public static final int TILE_SIZE = 40;
	public static final int NUM_TILE_COL = C_WIDTH / TILE_SIZE;
	public static final int NUM_TILE_ROW = C_HEIGHT / TILE_SIZE;
	public static final int NUM_WORLD_TILES = NUM_TILE_COL + NUM_TILE_ROW;
	public static final boolean TEST_MODE = true;
	public static final String TITLE = "ECS BETA";
	public static final int PLAYER_SIZE = 45;
	public static final int ITEM_SIZE = 30;

	private static GameClient socketClient;
	private static GameServer socketServer;
//	private static boolean isServer = false;
	private static PlayerController playerController;


	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
			boolean isServer = false;
			/**CONTROL**/
			//TODO if exceptions, might be because key-listener is created in static method(main)
					//SERVER STUFF
			if(JOptionPane.showConfirmDialog(null, "Run server?")==0){
				isServer = true;
				socketServer = new GameServer();
				socketServer.start();
			}

			GameState gameState = new GameState(isServer);
			if(isServer){socketServer.setGame(gameState);}

			socketClient = new GameClient("localhost", gameState);
			socketClient.start();

			PlayerController playerController = new PlayerController(socketClient);

			String username = "00"+JOptionPane.showInputDialog(null,"enter username");
			//TODO fix up packet system
			PacketLogin loginPacket = new PacketLogin(username.getBytes());
			loginPacket.writeData(socketClient);

			/**VIEW*/
			// Set up the gameFrame
			//TODO: Bonnie added this extra argument!
			GameFrame gameFrame = new GameFrame(TITLE, F_WIDTH, F_HEIGHT, socketServer);
			//Setup UI
			GameCanvas gameCanvas = new GameCanvas(gameFrame, C_WIDTH, C_HEIGHT);
			gameFrame.getContentPane().add(gameCanvas);
			gameFrame.pack();
			gameFrame.addKeyListener(playerController);
			GameCamera	camera = new GameCamera(gameState, socketClient.getClientNum());
			Renderer renderer = new Renderer(camera, gameCanvas);
			GameTimer gameTimer = new GameTimer(renderer, camera);
			gameTimer.start();
			}
		});

	}
}
