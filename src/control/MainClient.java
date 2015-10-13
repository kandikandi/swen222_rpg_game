package control;

import java.awt.EventQueue;

import ui.GameCanvas;
import ui.GameFrame;
import model.GameState;

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

    //TODO delete this -- Cuan
    static public void main(String[] args) {
        String username = "cuan";
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                boolean isServer = false;
                GameFrame gameFrame = new GameFrame(TITLE, F_WIDTH, F_HEIGHT);
                GameCanvas gameCanvas = new GameCanvas(gameFrame, C_WIDTH, C_HEIGHT);
                gameFrame.getContentPane().add(gameCanvas);
                gameFrame.pack();

                GameState gameState = new GameState(isServer);
                ClientControl socketClient = new ClientControl("localhost", gameState, gameCanvas);
                gameCanvas.setSocketClient(socketClient);
                PlayerController playerController = new PlayerController(socketClient);
                gameFrame.addKeyListener(playerController);
                gameFrame.add(socketClient);


                socketClient.start();
                PacketLogin loginPacket = new PacketLogin(("00" + username).getBytes());
                loginPacket.writeData(socketClient);


            }

        });
    }

    public void launchClient(String username) {

        EventQueue.invokeLater(new Runnable() {
            public void run() {

                boolean isServer = false;
                GameFrame gameFrame = new GameFrame(TITLE, F_WIDTH, F_HEIGHT);
                GameCanvas gameCanvas = new GameCanvas(gameFrame, C_WIDTH, C_HEIGHT);
                gameFrame.getContentPane().add(gameCanvas);
                gameFrame.pack();

                GameState gameState = new GameState(isServer);
                ClientControl socketClient = new ClientControl("localhost", gameState, gameCanvas);
                gameCanvas.setSocketClient(socketClient);
                PlayerController playerController = new PlayerController(socketClient);
                gameFrame.addKeyListener(playerController);
                gameFrame.add(socketClient);


                socketClient.start();
                PacketLogin loginPacket = new PacketLogin(("00"+username).getBytes());
                loginPacket.writeData(socketClient);


            }

        });
    }
}


