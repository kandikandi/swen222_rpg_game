package system;


import model.Actor;
import model.Tile;
import control.Main;
import ui.GameCanvas;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.List;


/**
 * Created by cuan on 9/8/15.
 */
public final class SDraw implements GameSystem{

    private final GameCamera camera;
    private final GameCanvas gameCanvas;
    private BufferedImage buffImg;
    private Shape blackFill;
    private Graphics2D g2d;

    public SDraw(GameCamera camera, GameCanvas gameCanvas) {
        this.camera = camera;
        this.gameCanvas = gameCanvas;
        buffImg = new BufferedImage(Main.C_WIDTH, Main.C_HEIGHT, BufferedImage.TYPE_INT_ARGB);
        g2d = buffImg.createGraphics();
        blackFill = new Rectangle(Main.C_WIDTH, Main.C_HEIGHT);
    }

    /**
     * Flushes the buffered image by filling it with black
     */
    private void drawBackground(){
        g2d.setColor(Color.BLACK);
        g2d.fill(blackFill);
    }

    @Override
    public void performSystem() {
        //paint scene background black
        drawBackground();
        Actor player = camera.getPlayerActor();
        int playerX = player.getPosition().getxPos();
        int playerY = player.getPosition().getyPos();
        final int HALF_C_WIDTH = Main.C_WIDTH/2;
        final int HALF_C_HEIGHT = Main.C_HEIGHT/2;

        List<Tile> tiles = camera.getTileList();
        for(Tile tile: tiles){
            Image image = tile.getImage();
            int x = tile.getPosition().getxPos() - playerX + HALF_C_WIDTH;
            int y = tile.getPosition().getyPos() - playerY + HALF_C_HEIGHT;
            g2d.drawImage(image,x,y,Main.TILE_SIZE,Main.TILE_SIZE,null);
        }

        List<Actor> actors = camera.getActorList();
        for(Actor actor: actors){
            Image image = actor.getImage();
            int x = actor.getPosition().getxPos() - playerX + HALF_C_WIDTH;
            int y = actor.getPosition().getyPos()- playerY + HALF_C_HEIGHT;
            g2d.drawImage(image,x,y,null);
        }
        gameCanvas.receiveBuffImage(buffImg);
    }

}


