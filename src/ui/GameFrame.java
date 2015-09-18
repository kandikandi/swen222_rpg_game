package ui;

import control.GameController;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by cuan on 9/10/15.
 */
public class GameFrame extends JFrame {
    private final GameCanvas gameCanvas;
    private final int WIDTH;
    private final int HEIGHT;
    private final GameController controller;

    public GameFrame(String title, int WIDTH, int HEIGHT, GameController gc) {
        super(title);
        this.WIDTH = WIDTH;
        this.HEIGHT = HEIGHT;
        this.controller = gc;
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //System.setProperty("sun.awt.noerasebackground", "true");
        //Properties prop = System.getProperties();
        //System.out.println(prop.keySet());
        //System.out.println(prop.getProperty("sun.awt.noerasebackground"));
        gameCanvas = new GameCanvas();
        gameCanvas.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        this.add(gameCanvas);
        this.pack();

        // Center window in screen
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension scrnsize = toolkit.getScreenSize();
        setBounds((scrnsize.width - WIDTH) / 2,
                (scrnsize.height - HEIGHT) / 2, WIDTH, HEIGHT);


    }

    public void receiveBuffImage(BufferedImage buffImg) {
        //System.out.println("GameFrame recieve BuffImage");
        systemImage = buffImg;
        gameCanvas.repaint();
    }

    private BufferedImage systemImage = null;
    private class GameCanvas extends JPanel {

        @Override
        public void paintComponent(Graphics g) {
            //System.out.println("GameCanvas paintComponent");
            Graphics2D g2 = (Graphics2D) g;
            if(systemImage == null){
                g2.fillRect(0, 0, this.getWidth(), this.getHeight());
            }else{
                g2.drawImage(systemImage,0,0,this);
            }
        }
    }

}
