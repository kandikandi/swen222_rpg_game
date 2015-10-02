package control;

import model.*;
import ui.GameCanvas;
import ui.GameFrame;
import save.DataStorage;
import system.SDraw;

import javax.swing.*;

import java.awt.*;
import java.awt.event.KeyListener;

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
	public static final String TITLE = "Bedtime Story";
	public static final int PLAYER_SIZE = 45;
	public static final int ITEM_SIZE = 30;

	public static void main(String[] args) {
		// Setting this property may help prevent/stop JPanel GUI flickering
		// System.setProperty("sun.awt.noerasebackground", "true");
		// This EventQueue stuff might help to stop bugs in Swing
		EventQueue.invokeLater(new Runnable() {
			public void run() {


				KeyListener gameKeyListener = new GameKeyListener();
				GameController gameController = new GameController((GameKeyListener)gameKeyListener);



				// Set up the gameFrame
				GameFrame gameFrame = new GameFrame(TITLE, F_WIDTH, F_HEIGHT);

				//Setup UI
				GameCanvas gameCanvas = new GameCanvas(gameController,gameFrame, C_WIDTH, C_HEIGHT);
				gameFrame.getContentPane().add(gameCanvas);
				gameFrame.pack();
				gameFrame.addKeyListener(gameKeyListener);




				// create and add systems in order they need to be executed
				SDraw drawSystem = new SDraw(gameController,gameCanvas);
				gameController.addSystem(drawSystem);

				//=================================================//
				//TODO: Bonnie here adding add some lines for save!
				DataStorage ds = new DataStorage(gameController);
				//TODO: Bonnie ends here!
				//=================================================//

				// create time to control systems loop
				GameTimer timer = new GameTimer(gameController);
				timer.start();

			}
		});

	}
}
