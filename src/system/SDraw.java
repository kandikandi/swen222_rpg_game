package system;

import control.GameController;
import model.Actor;
import model.GameObject;
import model.GameState;
import model.Tile;
import control.Main;
import ui.GameCanvas;
import ui.GameFrame;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.List;

/**
 * Created by cuan on 9/8/15.
 */
public final class SDraw implements GameSystem {

	private final GameState gameState;
	private final GameCanvas gameCanvas;
	private BufferedImage buffImg;
	private Shape blackFill;
	private Graphics2D g2d;

	public SDraw(GameState gameState, GameCanvas gameCanvas) {
		this.gameState = gameState;
		this.gameCanvas = gameCanvas;
		buffImg = new BufferedImage(Main.C_WIDTH, Main.C_HEIGHT,
				BufferedImage.TYPE_INT_ARGB);
		g2d = buffImg.createGraphics();
		blackFill = new Rectangle(Main.C_WIDTH, Main.C_HEIGHT);

	}

	/**
	 * Flushes the buffered image by filling it with black
	 */
	private void drawBackground() {
		g2d.setColor(Color.BLACK);
		g2d.fill(blackFill);
	}

	@Override
	public void performSystem() {
		// paint scene background black
		drawBackground();
		Tile[][] world = gameState.getWorld();

		// Look for all entities that contain the two components required to
		// draw it
		for (int row = 0; row < Main.NUM_TILE_ROW; row++) {
			for (int col = 0; col < Main.NUM_TILE_COL; col++) {
				Tile tile = world[row][col];
				Image image = tile.getImage();
				int x = tile.getPosition().getxPos();
				int y = tile.getPosition().getyPos();
				int width = Main.TILE_SIZE;
				g2d.drawImage(tile.getImage(), x, y, width, width, null);

			}
		}
		List<GameObject> gameObjects = gameState.getAllGameObjects();
		for (GameObject go : gameObjects) {

			Image image = go.getImage();
			int x = go.getPosition().getxPos();
			int y = go.getPosition().getyPos();

			if ((go.isDrawable())) { // added test for whether should be drawn
				g2d.drawImage(image, x, y, null);
			}
		}
		gameCanvas.receiveBuffImage(buffImg);
	}

}
