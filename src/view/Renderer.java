package view;

import control.GlobalConst;
import model.*;
import ui.GameCanvas;

import java.awt.*;
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
    private final boolean DRAW_HITBOXES = false;

    public Renderer( GameCanvas gameCanvas) {
        camera = new GameCamera();
        this.gameCanvas = gameCanvas;
        buffImg = new BufferedImage(GlobalConst.C_WIDTH, GlobalConst.C_HEIGHT,BufferedImage.TYPE_INT_ARGB);
        g2d = buffImg.createGraphics();
        blackFill = new Rectangle(GlobalConst.C_WIDTH, GlobalConst.C_HEIGHT);

    }

    /**
     * Flushes the buffered imageName by filling it with black
     */
    private void drawBackground() {
        g2d.setColor(Color.BLACK);
        g2d.fill(blackFill);
    }

    /**
     *This method repositions all Actors and Tiles around the origin, which
     * is the player being controlled
     *
     */
    public void renderScene(GameState gameState, int playerNum) {
        // paint scene background black
        drawBackground();

        Player player = gameState.findPlayer(playerNum);
        if(player==null){
            return;
        }

        //first find player position, this will become the origin
        final int originX = player.getPosition().getxPos();
        final int originY = player.getPosition().getyPos();
        final int tileSize = GlobalConst.TILE_SIZE;

        camera.getTileView(gameState, playerNum).forEach(tile -> {
            int relativeX = tile.getPosition().getxPos() - originX;
            int relativeY = tile.getPosition().getyPos() - originY;
            // rotate about origin, taking into account tiles size
            int x = RotationCalculator.getScreenX(relativeX,relativeY, tileSize, tileSize);
            int y = RotationCalculator.getScreenY(relativeX,relativeY, tileSize, tileSize);
            Image image = TileAssets.getAssetImage(tile.getAsciiCode());
            g2d.drawImage(image, x, y, GlobalConst.TILE_SIZE, GlobalConst.TILE_SIZE, null);
        });

        //now do same with actors
        Comparator<Actor> actorComparator = RotationCalculator.getActorComparator();
        camera.getActorView(gameState, playerNum)
                .stream().sorted(actorComparator)
                .filter(actor -> actor.isDrawable())
                .forEach(actor -> {
                    int width = actor.getBoundingBox().width;
                    int height = actor.getBoundingBox().height;
                    int relativeX = actor.getPosition().getBoundingBox().x - originX;
                    int relativeY = actor.getPosition().getBoundingBox().y - originY;
                    //rotate bounding boxes around origin
                    int x = RotationCalculator.getScreenX(relativeX,relativeY,width,height);
                    int y = RotationCalculator.getScreenY(relativeX,relativeY,width,height);
                    //work out images local (0,0) through bounding box offset
                    int imageX = x - actor.getBoundingBox().getXOffset();
                    int imageY = y - actor.getBoundingBox().getYOffset();
                    Image image = ActorAssets.getAssetImage(actor.getAsciiCode());
                    g2d.drawImage(image, imageX, imageY, null);

                    if (DRAW_HITBOXES) {
                        g2d.setColor(new Color(155, 255, 99));
                        g2d.fillRect(x,y,width,height);
                    }
                });
        gameCanvas.receiveBuffImage(buffImg); //send image to UI for renderering
    }

}