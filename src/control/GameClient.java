package control;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;

import control.Packet.PacketTypes;
import model.Actor;
import model.GameException;
import model.GameState;
import ui.GameCanvas;
import view.GameCamera;
import view.Renderer;

public class GameClient extends Thread {

    /**
     * this thread is for the clients. I think this will eventually be the thread which takes the input from the players,
     * but am not 100% sure yet. at the moment it doesn't really do much
     */

    private InetAddress ipAddress;
    private DatagramSocket socket;
    private GameState gameState;
    Serialiser serial = new Serialiser();
    boolean handSakeComplete = false;
    private int clientNum = 0;
    Renderer renderer;


    public GameClient(String ipAddress, GameState gameState, GameCanvas gameCanvas) {

        this.gameState = gameState;
        renderer = new Renderer(gameCanvas);

        try {
            this.ipAddress = InetAddress.getByName(ipAddress);
            this.socket = new DatagramSocket();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        while (true) {

            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            byte[] data = new byte[60000];
            DatagramPacket packet = new DatagramPacket(data, data.length);

            try {
                socket.receive(packet);
            } catch (IOException e) {
                e.printStackTrace();
            }
            parsePacket(packet.getData(), packet.getAddress(), packet.getPort());

        }
    }


    private void parsePacket(byte[] data, InetAddress address, int port) {

        String message = new String(data).trim();
        PacketTypes type = Packet.lookupPacket(message.substring(0, 1));

        switch (type) {
            case UPDATE:
                //System.out.println("GameClient recieved UPDATE");
                ArrayList<Actor> recd;
                try {
                    if (clientNum != -1) {
                        recd = (ArrayList<Actor>) serial.deserialize(data);
                        gameState.setActors(recd);
                        renderer.renderScene(gameState, clientNum);
                    } else {
                        System.out.println("GameClient received UPDATE packet before finishing handshake");
                        //throw new GameException("GameClient received UPDATE packet before finishing handshake");
                        break;
                    }
                } catch (ClassNotFoundException | IOException /*| GameException */e) {
                    System.out.println(e.getMessage());
                    e.printStackTrace();
                }
                break;

            case LOGINCONFIRM:
                System.out.println("GameClient recieved LOGINCONFIRM");
                if (handSakeComplete) {
                    System.out.println("GameClient already authenticated, LOGINCONFIRM not for me6");
                    break;
                } else {
                    //must make sure to call the overloaded child method
                    LoginConfirm loginConfirm = new LoginConfirm(data);
                    clientNum = loginConfirm.getClientNumber();
                    //String bytecode = (String) serial.deserialize(data);
                    //clientNum = Integer.parseInt(bytecode);
                    System.out.println("GameClient LOGINCONFIRM recieved...clientNum: " + clientNum);
                    handSakeComplete = true;
                }

                break;
            case LOGIN:
                //TODO remove this println and perhaps make it throw an exception
                System.out.println("GameClient Error: received a login packet");
                //clientNum = Packet.lookupClient(message.substring(1, 2));
                break;

            case DISCONNECT:
                try {
                    throw new GameException("Client recieved disconnect packet error");
                } catch (GameException e) {
                    e.printStackTrace();
                }
                break;

            default:
                try {
                    throw new GameException("Client Packet Header Error");
                } catch (GameException e) {
                    e.printStackTrace();
                }
                break;
        }

    }

    //send data to the server
    public void sendData(byte[] data) {
        DatagramPacket packet = new DatagramPacket(data, data.length, ipAddress, Main.PORT);
        try {
            socket.send(packet);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendKeyPress(String move) {

        String sendMove = "3" + clientNum + move;
        PacketMove movePacket = new PacketMove(sendMove.getBytes());
        movePacket.writeData(this);

    }

    // Added so GUI could get GameState via GameClient
    /*public GameState getGameState(){
        return game;
	}*/

    // Added so GUI can compare client number to update specific players inventory
    public int getClientNum() {
        return this.clientNum;
    }
}
