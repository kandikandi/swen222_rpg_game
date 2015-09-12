package control;

import factory.TEST_IMAGE;
import factory.TileFactory;
import model.Location;
import model.Tile;
import system.SMove;
import ui.GameFrame;
import view.SDraw;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyListener;


public class Main {
    public static final int G_WIDTH = 800;
    public static final int G_HEIGHT = 600;
    public static final int TILE_SIZE = 40;
    public static final int NUM_TILE_COL = G_WIDTH/TILE_SIZE;
    public static final int NUM_TILE_ROW = G_HEIGHT/TILE_SIZE;
    public static final int NUM_WORLD_TILES = NUM_TILE_COL+NUM_TILE_ROW;
    public static final boolean TEST_MODE = true;
    public static final String TITLE = "ECS alpha 0.1";

    public static void main(String[] args) {
        // Setting this property may help prevent/stop JPanel GUI flickering
        System.setProperty("sun.awt.noerasebackground", "true");
        // This EventQueue stuff might help to stop bugs in Swing
        EventQueue.invokeLater(new Runnable() {
            public void run() {

                //create master control controller first
                GameController controller = new GameController();

                //Canvas is a innerclass of GameFrame
                JFrame gameFrame = new GameFrame(TITLE,G_WIDTH, G_HEIGHT, controller);
                KeyListener gameKeyListner = new GameKeyListener(controller);
                gameFrame.addKeyListener(gameKeyListner);

                //tempory moving object
                //NOTE: this is not a proper actor
                Tile tempPlayer = TileFactory.createTestProduct(40,40);



                //create and add systems in order they need to be executed
                SDraw drawSystem = new SDraw(controller, (GameFrame)gameFrame, tempPlayer);
                SMove moveSystem = new SMove(controller,tempPlayer);
                controller.addSystem(moveSystem);
                controller.addSystem(drawSystem);



                //create time to control systems loop
                GameTimer timer = new GameTimer(controller);
                timer.start();



            }
        });


    }
}
