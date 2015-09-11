package system;

import component.CPosition;
import component.CSprite;
import entity.GameEntity;
import game.GameController;
import game.Main;
import ui.GameFrame;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Iterator;

/**
 * Created by cuan on 9/8/15.
 */
public final class SDraw implements GameSystem {

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
        Iterator<GameEntity> it = controller.getEntityIterator();
        GameEntity entity;

        // Look for all entities that contain the two components required to draw it
        while (it.hasNext()) {
            entity = it.next();
            //CPosition = 1 CSprite = 2
            if (entity.containsCompID(1) && entity.containsCompID(2)) {
                //Drawable entity found, cast and get data
                CPosition pos = (CPosition) entity.getComp(1);
                CSprite sprite = (CSprite) entity.getComp(2);

                int x = pos.xpos;
                int y = pos.ypos;
                int width = sprite.width;
                int height = sprite.height;
                Image image = sprite.image;

                //draw entities to buffered image and send to canvas/frame
                g2d.drawImage(image, x, y, null);
                gameFrame.receiveBuffImage(buffImg);
            }
        }
    }



}


