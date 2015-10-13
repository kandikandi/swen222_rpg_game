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

        if(Main.TEST_MODE){
            int tilesReceived = camera.getTileView(gameState,playerNum).size();
            int actorReceived = camera.getActorView(gameState,playerNum).size();
            System.out.println("Renderer recieved Tiles: "+tilesReceived+"   Actors: "+actorReceived);
        }

        int playerX = player.getPosition().getxPos();
        int playerY = player.getPosition().getyPos();
        final int HALF_C_WIDTH = Main.C_WIDTH / 2;
        final int HALF_C_HEIGHT = Main.C_HEIGHT / 2;

        Comparator<Actor> actorComparator = new Comparator<Actor>() {
            @Override
            public int compare(Actor actorA, Actor actorB) {
                BoundingBox bboxA = actorA.getBoundingBox();
                BoundingBox bboxB = actorB.getBoundingBox();
                if(bboxA.getMaxY() > bboxB.getMaxY()){return 1;}
                else if(bboxA.getMaxY() < bboxB.getMaxY()){return -1;}
                else if(bboxA.getMaxX() > bboxB.getMaxX()){return 1;}
                else if(bboxA.getMaxX() < bboxB.getMaxX()){return -1;}
                return 0;
            }
        };

        // Look for all entities that contain the two components required to
        // draw it
        camera.getTileView(gameState, playerNum).forEach(tile -> {
            Image image = TileAssets.getAssetImage(tile.getAsciiCode());
            int x = tile.getPosition().getxPos() - playerX + HALF_C_WIDTH;
            int y = tile.getPosition().getyPos() - playerY + HALF_C_HEIGHT;
            //System.out.println("Renderer: tileX: "+x+" tileY: "+y);
            g2d.drawImage(image, x, y, Main.TILE_SIZE, Main.TILE_SIZE, null);
        });


        camera.getActorView(gameState, playerNum)
                .stream().sorted(actorComparator)
                .filter(actor -> actor.isDrawable())
                .forEach(actor -> {
                    Image image = ActorAssets.getAssetImage(actor.getAsciiCode());
                    int x = actor.getPosition().getxPos() - playerX + HALF_C_WIDTH;
                    int y = actor.getPosition().getyPos() - playerY + HALF_C_HEIGHT;
                    g2d.drawImage(image, x, y, null);

                    if (Main.DRAW_HITBOXES) {
                        g2d.setColor(new Color(255, 126, 241));
                        g2d.fillRect(actor.getBoundingBox().x - playerX + HALF_C_WIDTH,
                                actor.getBoundingBox().y - playerY + HALF_C_HEIGHT,
                                actor.getBoundingBox().width,
                                actor.getBoundingBox().height);
                    }

                });
        gameCanvas.receiveBuffImage(buffImg);
    }

}