package control;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

import view.GameCamera;
import ui.GameCanvas;
import ui.GameFrame;
import model.GameState;
import view.Renderer;

public class MainClient {

    public static final int F_WIDTH = 1000;
    public static final int F_HEIGHT = 600;
    public static final int C_WIDTH = 800;
    public static final int C_HEIGHT = 600;
    public static final int TILE_SIZE = 40;
    public static final int NUM_TILE_COL = C_WIDTH / TILE_SIZE;
    public static final int NUM_TILE_ROW = C_HEIGHT / TILE_SIZE;
    public static final int NUM_WORLD_TILES = NUM_TILE_COL + NUM_TILE_ROW;
    public static final boolean TEST_MODE = true;
    public static final String TITLE = "ECS BETA";
    public static final int PLAYER_SIZE = 45;
    public static final int ITEM_SIZE = 30;
    public static final int PLAYER_ONE_START_LOCATION_X = 75;
    public static final int PLAYER_ONE_START_LOCATION_Y = 75;
    public static final int PLAYER_TWO_START_LOCATION_X = 125;
    public static final int PLAYER_TWO_START_LOCATION_Y = 75;
    public static final boolean DRAW_HITBOXES = false;
    private static GameClient socketClient;
    private static GameServer socketServer;
    //private static PlayerController playerController;


    public static void main(String[] args) {

        EventQueue.invokeLater(new Runnable() {
            public void run() {
                boolean isServer = false;

                String username = "00" + JOptionPane.showInputDialog(null, "enter username");

                GameState gameState = new GameState(isServer);
                socketClient = new GameClient("localhost", gameState);
                socketClient.start();
                PacketLogin loginPacket = new PacketLogin(username.getBytes());
                loginPacket.writeData(socketClient);
                PlayerController playerController = new PlayerController(socketClient);



                // Set up the gameFrame
                //TODO: Bonnie added this extra argument!
                GameFrame gameFrame = new GameFrame(TITLE, F_WIDTH, F_HEIGHT, socketServer);
                //Setup UI
                GameCanvas gameCanvas = new GameCanvas(gameFrame, C_WIDTH, C_HEIGHT, socketClient);
                gameFrame.getContentPane().add(gameCanvas);
                gameFrame.pack();
                gameFrame.addKeyListener(playerController);

                GameCamera camera = new GameCamera(gameState, socketClient);
                Renderer renderer = new Renderer(camera, gameCanvas);
                EnemyController enemyController = new EnemyController(gameState);
                GameTimer gameTimer = new GameTimer(camera, renderer, enemyController);
                gameTimer.start();
            }

        });

    }
}
