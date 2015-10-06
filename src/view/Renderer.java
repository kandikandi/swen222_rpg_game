package view;

import control.Main;
import model.Actor;
import model.Player;
import model.Tile;
import ui.GameCanvas;

import java.awt.*;
import java.awt.List;
import java.awt.image.BufferedImage;
import java.util.*;

/**
 * Created by cuan on 9/8/15.
 */
public final class Renderer {

    private final GameCamera camera;
    private final GameCanvas gameCanvas;
    private BufferedImage buffImg;
    private Shape blackFill;
    private Graphics2D g2d;

    public Renderer(GameCamera camera, GameCanvas gameCanvas) {
        this.camera = camera;
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

        Player player = camera.getPlayerActor();

        int playerX = player.getPosition().getxPos();
        int playerY = player.getPosition().getyPos();
        final int HALF_C_WIDTH = Main.C_WIDTH / 2;
        final int HALF_C_HEIGHT = Main.C_HEIGHT / 2;


        // Look for all entities that contain the two components required to
        // draw it
        camera.getTileView().forEach(tile -> {
            Image image = TileAssets.getAssetImage(tile.getAsciiCode());
            int x = tile.getPosition().getxPos() - playerX + HALF_C_WIDTH;
            int y = tile.getPosition().getyPos() - playerY + HALF_C_HEIGHT;
            g2d.drawImage(image, x, y, Main.TILE_SIZE, Main.TILE_SIZE, null);
        });


        camera.getActorView()
                .stream()
                .filter(actor -> actor.isDrawable())
                .forEach(actor -> {
                    Image image = ActorAssets.getAssetImage(actor.getAsciiCode());
                    int x = actor.getPosition().getxPos() - playerX + HALF_C_WIDTH;
                    int y = actor.getPosition().getyPos() - playerY + HALF_C_HEIGHT;
                    g2d.drawImage(image, x, y, null);
                });
        gameCanvas.receiveBuffImage(buffImg);
    }

}
