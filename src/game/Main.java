package game;

import component.CPosition;
import component.CSprite;
import component.GameComponent;
import entity.GameEntity;
import system.SDraw;
import system.SMove;
import ui.GameFrame;
import ui.GameKeyListener;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;


public class Main {
    public static final int G_WIDTH = 800;
    public static final int G_HEIGHT = 600;
    public static final String TITLE = "ECS alpha 0.1";

    public static void main(String[] args) {
        // This EventQueue stuff might help to stop bugs in Swing
        EventQueue.invokeLater(new Runnable() {
            public void run() {

                //create master game controller first
                GameController controller = new GameController();

                //Canvas is a innerclass of GameFrame
                JFrame gameFrame = new GameFrame(TITLE,G_WIDTH, G_HEIGHT, controller);
                KeyListener gameKeyListner = new GameKeyListener(controller);
                gameFrame.addKeyListener(gameKeyListner);

                //create and add systems in order they need to be executed
                SDraw drawSystem = new SDraw(controller, (GameFrame)gameFrame);
                SMove moveSystem = new SMove(controller);
                controller.addSystem(moveSystem);
                controller.addSystem(drawSystem);

                Image image = null;
                try {
                    image = ImageIO.read(new File("tokenPlum.png"));
                } catch (IOException e) {
                    e.printStackTrace();
                }

                //temp player entity
                GameEntity player = controller.createEntity(999);
                GameComponent gc =  new CPosition(50,50);
                player.attachComp(gc);
                gc = new CSprite(image,20,20);
                player.attachComp(gc);


                //create time to control systems loop
                GameTimer timer = new GameTimer(controller);
                timer.start();



            }
        });


    }
}
