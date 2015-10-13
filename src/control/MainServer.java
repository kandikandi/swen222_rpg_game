package control;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

import view.GameCamera;
import ui.GameCanvas;
import ui.GameFrame;
import model.GameState;
import model.Actor;
import model.Enemy;
import view.Renderer;

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
	private static GameServer socketServer;

	public static void shutDownServer(){
		System.out.println("MainServer: shutDownServer() called");
		socketServer.shutDownServer();
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				boolean isServer = true;
				socketServer = new GameServer();
				socketServer.start();
				GameState gameState = new GameState(isServer);
				socketServer.setGame(gameState);
				EnemyController enemyController = new EnemyController(gameState);
				GameTimer gameTimer = new GameTimer(enemyController, socketServer);
				//gameTimer.start();

			}

		});
	}
}


