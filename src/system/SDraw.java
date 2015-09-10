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


    public SDraw(GameController controller, GameFrame gameFrame) {
        this.controller = controller;
        this.gameFrame = gameFrame;

    }

    @Override
    public void performSystem() {
        //System.out.println("SDraw perform system");
        Iterator<GameEntity> it = controller.getEntityIterator();
        //System.out.println(it.hasNext());
        GameEntity entity;
        //CPosition = 1 CSprite = 2
        while (it.hasNext()) {
            //System.out.println("SDraw while1");
            entity = it.next();
            if (entity.containsCompID(1) && entity.containsCompID(2)) {
                CPosition pos = (CPosition) entity.getComp(1);
                CSprite sprite = (CSprite) entity.getComp(2);
                //System.out.println("SDraw while2");
                int x = pos.xpos;
                int y = pos.ypos;
                int width = sprite.width;
                int height = sprite.height;
                Image image = sprite.image;

                drawScene(image, x, y);
                gameFrame.receiveBuffImage(buffImg);

            } //else System.out.println("Entity not drawable ID:");
        }
        //System.out.println("Sdraw2");
    }


    private BufferedImage buffImg = new BufferedImage(Main.G_WIDTH, Main.G_HEIGHT, BufferedImage.TYPE_INT_ARGB);
    private void drawScene(Image src, int x, int y) {
        if(buffImg == null){
            setupBuffImage();
        } else {
            Graphics2D g2d = buffImg.createGraphics();
            g2d.drawImage(src, x, y, null);
        }
    }

    private void setupBuffImage() {
        BufferedImage buffImg = new BufferedImage(Main.G_WIDTH, Main.G_HEIGHT, BufferedImage.TYPE_INT_ARGB);
    }
}


