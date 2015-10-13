package view;

import control.Main;
import model.*;
import ui.GameCanvas;
import view.GameCamera;
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

    public Renderer( GameCanvas gameCanvas) {
        camera = new GameCamera();
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

    public void renderScene(GameState gameState, int playerNum) {
        //System.out.println("Renderer renderscene()");
        // paint scene background black
        drawBackground();

        Player player = gameState.findPlayer(playerNum);
        if(player==null){
            System.out.println("Renderer cant find player: "+playerNum);
            return;
        }

        /*if(Main.TEST_MODE){
            int tilesReceived = camera.getTileView(gameState,playerNum).size();
            int actorReceived = camera.getActorView(gameState,playerNum).size();
            System.out.println("Renderer recieved Tiles: "+tilesReceived+"   Actors: "+actorReceived);
        }*/

        //first find player position, this will become the origin
        int originX = player.getPosition().getxPos();//+(ActorAssets.PLAYER.getWidth()/2);
        int originY = player.getPosition().getyPos();//+(ActorAssets.PLAYER.getHeight()/2);
        final int HALF_C_WIDTH = Main.C_WIDTH / 2;
        final int HALF_C_HEIGHT = Main.C_HEIGHT / 2;


        // Look for all entities that contain the two components required to
        // draw it
        camera.getTileView(gameState, playerNum).forEach(tile -> {
            Image image = TileAssets.getAssetImage(tile.getAsciiCode());
            int relativeX = tile.getPosition().getxPos() /*+ (Main.TILE_SIZE/2)*/ - originX;
            int relativeY = tile.getPosition().getyPos() /*+ (Main.TILE_SIZE/2)*/ - originY;
            int x = RotationCalculator.getScreenX(relativeX,relativeY,Main.TILE_SIZE,Main.TILE_SIZE)+ HALF_C_WIDTH;
            int y = RotationCalculator.getScreenY(relativeX,relativeY,Main.TILE_SIZE,Main.TILE_SIZE) + HALF_C_HEIGHT;
            g2d.drawImage(image, x, y, Main.TILE_SIZE, Main.TILE_SIZE, null);
        });

        Comparator<Actor> actorComparator = RotationCalculator.getActorComparator();
        camera.getActorView(gameState, playerNum)
                .stream().sorted(actorComparator)
                .filter(actor -> actor.isDrawable())
                .forEach(actor -> {
                    Image image = ActorAssets.getAssetImage(actor.getAsciiCode());
                    int width = ActorAssets.getAssetName(actor.getAsciiCode()).getWidth();
                    int height = ActorAssets.getAssetName(actor.getAsciiCode()).getHeight();
                    int relativeX = actor.getPosition().getxPos() /*+ (width/2)*/ - originX;
                    int relativeY = actor.getPosition().getyPos() /*+ (height/2)*/ - originY;
                    int x = RotationCalculator.getScreenX(relativeX,relativeY,width,height) + HALF_C_WIDTH;
                    int y = RotationCalculator.getScreenY(relativeX,relativeY,width,height) + HALF_C_HEIGHT;
                    g2d.drawImage(image, x, y, null);

                    if (Main.DRAW_HITBOXES) {
                        /*int rX = actor.getPosition().getxPos() + actor.getBoundingBox().getXOffset() - originX;
                        int rY = actor.getPosition().getyPos() + actor.getBoundingBox().getYOffset() - originY;*/
                        int rX = actor.getPosition().getBoundingBox().x - originX;
                        int rY = actor.getPosition().getBoundingBox().y - originY;
                        int w = actor.getBoundingBox().width;
                        int h = actor.getBoundingBox().height;
                        int x2 = RotationCalculator.getScreenX(rX,rY,w,height) + HALF_C_WIDTH;
                        int y2 = RotationCalculator.getScreenY(rX,rY,h,height) + HALF_C_HEIGHT;
                        g2d.setColor(new Color(255, 126, 241));
                        g2d.fillRect(x2,y2,w,h);
                    }

                });
        gameCanvas.receiveBuffImage(buffImg);
    }

}