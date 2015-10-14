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
    public static final String TITLE = "ECS BETA";



    //TODO delete this -- Cuan
    static public void main(String[] args) {
        String username = "cuan";
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                boolean isServer = false;
                GameFrame gameFrame = new GameFrame(TITLE, F_WIDTH, F_HEIGHT, null, null);
                GameCanvas gameCanvas = new GameCanvas(gameFrame, C_WIDTH, C_HEIGHT);
                gameFrame.getContentPane().add(gameCanvas);
                gameFrame.pack();

                GameState gameState = new GameState(isServer);
                ClientControl socketClient = new ClientControl("localhost", gameState, gameCanvas);
                gameCanvas.setSocketClient(socketClient);
                PlayerController playerController = new PlayerController(socketClient);
                gameFrame.addKeyListener(playerController);
//                gameFrame.add(socketClient);


                socketClient.start();
                PacketLogin loginPacket = new PacketLogin(("00" + username).getBytes());
                loginPacket.writeData(socketClient);


            }

        });
    }


    public void launchClient(String username, MainServer server) {


        EventQueue.invokeLater(new Runnable() {
            public void run() {

                boolean isServer = false;
                GameFrame gameFrame = new GameFrame(TITLE, F_WIDTH, F_HEIGHT, server, username);
                GameCanvas gameCanvas = new GameCanvas(gameFrame, C_WIDTH, C_HEIGHT);
                gameFrame.getContentPane().add(gameCanvas);
                gameFrame.pack();

                GameState gameState = new GameState(isServer);
                ClientControl socketClient = new ClientControl("localhost"/*"130.195.4.170"*/, gameState, gameCanvas);
                gameCanvas.setSocketClient(socketClient);
                PlayerController playerController = new PlayerController(socketClient);
                gameFrame.addKeyListener(playerController);
//                gameFrame.add(socketClient);


                socketClient.start();
                PacketLogin loginPacket = new PacketLogin(("00"+username).getBytes());
                loginPacket.writeData(socketClient);


            }

        });
    }
}


