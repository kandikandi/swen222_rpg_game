package control;

import factory.TEST_IMAGE;
import model.*;
import ui.GameCanvas;
import ui.GameFrame;
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
	public static final String TITLE = "ECS alpha 0.1";

	public static void main(String[] args) {
		// Setting this property may help prevent/stop JPanel GUI flickering
		System.setProperty("sun.awt.noerasebackground", "true");
		// This EventQueue stuff might help to stop bugs in Swing
		EventQueue.invokeLater(new Runnable() {
			public void run() {

				// create master control controller first
				GameController controller = new GameController();

				// Set up the gameFrame
				JFrame gameFrame = new GameFrame(TITLE, F_WIDTH, F_HEIGHT,
						controller);

				GameCanvas gameCanvas = new GameCanvas(C_WIDTH, C_HEIGHT);
				gameFrame.getContentPane().add(gameCanvas);
				gameFrame.pack();

				KeyListener gameKeyListner = new GameKeyListener(controller);
				gameFrame.addKeyListener(gameKeyListner);

				// temp moving object
				MovementStrategy ms = new PlayerMoveStrategy(controller);
				Position loc = new Position(300, 400);
				ActorStrategy tempPlayer = new Player(ID.PLAYER, loc,
						TEST_IMAGE.MR_PLUM.getImage(), false, true, 45);
				tempPlayer.setMoveStrat(ms);
				controller.addActor(tempPlayer);

				// create and add systems in order they need to be executed
				SDraw drawSystem = new SDraw(controller, (GameCanvas) gameCanvas);
				controller.addSystem(drawSystem);

				// create time to control systems loop
				GameTimer timer = new GameTimer(controller);
				timer.start();

			}
		});

	}
}
