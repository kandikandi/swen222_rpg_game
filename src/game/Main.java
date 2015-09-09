package game;

import component.CPosition;
import component.CSprite;
import entity.Entity;
import system.GameSystem;
import system.SDraw;
import system.SMove;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;


public class Main {



    public static void main(String[] args) {
	// write your code here
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                Toolkit toolkit = Toolkit.getDefaultToolkit();
                Dimension screenSize = toolkit.getScreenSize();
                int screenHeight = screenSize.height;

                JFrame frame = new JFrame();
                frame.setTitle("Entity Component System Alpha");
                frame.setPreferredSize(new Dimension(screenHeight - 100, screenHeight - 100));
                frame.setVisible(true);

                Image image = null;
                try {
                    image = ImageIO.read(new File("tokenPlum.png"));
                } catch (IOException e) {
                    e.printStackTrace();
                }

                GameController controller = new GameController();
                Entity player1 = controller.createEntity(999);
                player1.attachComp(new CPosition(50, 50));
                player1.attachComp(new CSprite(image, 20, 20));

                JPanel drawer = new SDraw(controller);

                controller.addSystem((SDraw)drawer);

                frame.add(drawer);
                frame.pack();
                SMove moveSystem = new SMove(controller);
                frame.addKeyListener(moveSystem);
                controller.addSystem(moveSystem);
                GameTimer timer = new GameTimer(controller);
                timer.start();



            }
        });


    }
}
