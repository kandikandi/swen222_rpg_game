package system;

import component.CPosition;
import component.CSprite;
import entity.Entity;
import game.GameController;
import javax.swing.*;
import java.awt.*;
import java.util.Iterator;

/**
 * Created by cuan on 9/8/15.
 */
public final class SDraw  extends JPanel implements GameSystem{
   private final GameController controller;
   Toolkit toolkit = Toolkit.getDefaultToolkit();


   public SDraw(GameController controller){
      this.controller = controller;
      Dimension screenSize = toolkit.getScreenSize();
      int screenHeight = screenSize.height;
      this.setPreferredSize(new Dimension(screenHeight - 100, screenHeight - 100));

   }

   @Override
   public void paintComponent(Graphics g) {

      Graphics2D g2 = (Graphics2D) g;
      g2.fillRect(0, 0, this.getWidth(), this.getHeight());
      Iterator<Entity> it = controller.getEntityIterator();
      Entity entity;
      //CPosition = 1 CSprite = 2
       while(it.hasNext()){
         entity = it.next();
         if(entity.containsCompID(1) && entity.containsCompID(2)){
             CPosition pos = (CPosition) entity.getComp(1);
             CSprite sprite = (CSprite) entity.getComp(2);
             int x = pos.xpos; int y = pos.ypos;
             //test/
             int width = sprite.width; int height = sprite.height;
             g2.drawImage(sprite.image, x, y, width, height,null);

         }
      }
   }


    @Override
    public void performSystem() {
        //System.out.println("SDraw performSystem Called");
        this.repaint();
    }
}
