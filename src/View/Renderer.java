package View;

import Control.Main;
import Model.Actor;
import Model.GameState;
import Model.Tile;
import ui.GameCanvas;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.List;

/**
 * Created by cuan on 9/8/15.
 */
public final class Renderer {

	private final GameState gameState;
	private final GameCanvas gameCanvas;
	private BufferedImage buffImg;
	private Shape blackFill;
	private Graphics2D g2d;

	public Renderer(GameState gameState, GameCanvas gameCanvas) {
		this.gameState = gameState;
		this.gameCanvas = gameCanvas;
		buffImg = new BufferedImage(Main.C_WIDTH, Main.C_HEIGHT,
				BufferedImage.TYPE_INT_ARGB);
		g2d = buffImg.createGraphics();
		blackFill = new Rectangle(Main.C_WIDTH, Main.C_HEIGHT);

	}

	/**
	 * Flushes the buffered imageName by filling it with black
	 */
	private void drawBackground() {
		g2d.setColor(Color.BLACK);
		g2d.fill(blackFill);
	}

	public void renderScene() {
		//System.out.println("Renderer renderscene()");
		// paint scene background black
		drawBackground();
		Tile[][] world = gameState.getWorld();

		// Look for all entities that contain the two components required to
		// draw it
		for (int row = 0; row < Main.NUM_TILE_ROW; row++) {
			for (int col = 0; col < Main.NUM_TILE_COL; col++) {
				Tile tile = world[row][col];
				//Image image = tile.getImage();
				Image image = TileAssets.getAssetImage(tile.getAsciiCode());
				int x = tile.getPosition().getxPos();
				int y = tile.getPosition().getyPos();
				int width = Main.TILE_SIZE;
				g2d.drawImage(image, x, y, width, width, null);

			}
		}
		List<Actor> actors = gameState.getActors();
		for (Actor actor : actors) {
			if(actor.isDrawable()){
				Image image = ActorAssets.getAssetImage(actor.getAsciiCode());
				int x = actor.getPosition().getxPos();
				int y = actor.getPosition().getyPos();
				g2d.drawImage(image, x, y, null);
			}
		}
		gameCanvas.receiveBuffImage(buffImg);
	}

}
