package system;

import control.GameController;
import model.Actor;
import model.Tile;
import control.Main;
import ui.GameFrame;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.List;


/**
 * Created by cuan on 9/8/15.
 */
public final class SDraw implements GameSystem{

    private final GameController controller;
    private final GameFrame gameFrame;
    private BufferedImage buffImg;
    private Shape blackFill;
    private Graphics2D g2d;



    public SDraw(GameController controller, GameFrame gameFrame) {
        this.controller = controller;
        this.gameFrame = gameFrame;
        buffImg = new BufferedImage(Main.G_WIDTH, Main.G_HEIGHT, BufferedImage.TYPE_INT_ARGB);
        g2d = buffImg.createGraphics();
        blackFill = new Rectangle(Main.G_WIDTH, Main.G_HEIGHT);


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
        Tile[][] world = controller.getWorld();

        // Look for all entities that contain the two components required to draw it
        for(int row=0; row<Main.NUM_TILE_ROW; row++){
            for(int col=0; col<Main.NUM_TILE_COL;col++){
                Tile tile = world[row][col];
                Image image = tile.getImage();
                int x = tile.getLocation().getxPos();
                int y = tile.getLocation().getyPos();
                int width = Main.TILE_SIZE;

                g2d.drawImage(tile.getImage(),x,y,width,width,null);
            }
        }
        List<Actor> actors = controller.getActors();
        for(Actor actor: actors){
            Image image = actor.getImage();
            int x = actor.getLocation().getxPos();
            int y = actor.getLocation().getyPos();
            g2d.drawImage(image,x,y,null);
        }
        gameFrame.receiveBuffImage(buffImg);
    }



}


