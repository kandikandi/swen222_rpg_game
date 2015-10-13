package control;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import control.Packet.PacketTypes;
import model.Actor;
import model.GameException;
import model.GameState;
import save.DataStorage;
import ui.GameCanvas;
import view.GameCamera;
import view.Renderer;

public class ClientControl extends Thread {

    //which client, and whether the server knows its there
    boolean handShakeComplete = false;
    /**The gameclient handles all data recieved from the server. Any packets passed in through the socket will be parsed for what type of packet then dealt
     * with in the appropriate manner. The client communicates with the server and the renderer/UI only, it does not deal with any game logic. It has a game state,
     * which it does not make any direct changes to, only updates with information recieved from server.
     *
     *@author mcleankand
     */

	//socket number to server and ip address game is played on
    private InetAddress ipAddress;
    private DatagramSocket socket;
    //gamestate to be updated by server communication
    private GameState gameState;
    //For deserialising the actor list
    private Serialiser serial = new Serialiser();
    private int clientNum = 0;

    //UI/Rendering
    private Renderer renderer;


    public ClientControl(String ipAddress, GameState gameState, GameCanvas gameCanvas) {

        this.gameState = gameState;
        //game canvas is used only to to pass into the renderer, so is not stored in a variable inside client
        this.renderer = new Renderer(gameCanvas);

        try {
            this.ipAddress = InetAddress.getByName(ipAddress);
            this.socket = new DatagramSocket();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }

    /**run method to loop through continuously*/
    public void run() {
        while (true) {

            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            //wait for packet from the server
            byte[] data = new byte[60000];
            DatagramPacket packet = new DatagramPacket(data, data.length);

            try {
                socket.receive(packet);
            } catch (IOException e) {
                e.printStackTrace();
            }

            //find out what kind of packet it is and deal with it
            parsePacket(packet.getData(), packet.getAddress(), packet.getPort());

            renderer.renderScene(gameState, clientNum);

        }
    }

    /** This method takes a data array, the address and port of the server which it recieves from the packet in the run method.
     * It trims the packet and decides what it is from the first digit and deals with the information in the most appropriate manner
     * decided by the switch case.
     * @param data
     * @param address
     * @param port
     */
    @SuppressWarnings("unchecked")
	private void parsePacket(byte[] data, InetAddress address, int port) {

    	//Get the first digit in the packet, which will tell us what to do with the data
        String message = new String(data).trim();
        PacketTypes type = Packet.lookupPacket(message.substring(0, 1));

        switch (type) {
            case UPDATE:
            	//If an update, deserialize the data, update the local game state
                ArrayList<Actor> recd;
                System.out.println("ClientControl: UPDATE parsed, clientNum: "+clientNum);
                try {
                    if (clientNum != -1) {
                    	recd = (ArrayList<Actor>) serial.deserialize(data);
                        System.out.println("ClientControl: finished deserialize, actorList length: "+recd.size());
                        gameState.setActors(recd);
                    } else {
                        System.out.println("ClientControl received UPDATE packet before finishing handshake");
                        break;
                    }
                } catch (ClassNotFoundException | IOException e) {
                    System.out.println(e.getMessage());
                    e.printStackTrace();
                }
                break;

            case LOGINCONFIRM:
            	//If Loginconfirm, the server recieved the login packet, and is sending the client its number.
            	//Client needs to update itself that it has completed a handshake.
                System.out.println("ClientControl recieved LOGINCONFIRM");
                //If handshake is already complete, an error mustve occured.
                if (handShakeComplete) {
                    System.out.println("ClientControl already authenticated, LOGINCONFIRM not for me");
                    break;
                } else {
                    //must make sure to call the overloaded child method
                    PacketLoginConfirm loginConfirm = new PacketLoginConfirm(data);
                    clientNum = loginConfirm.getClientNumber();
                    System.out.println("ClientControl LOGINCONFIRM recieved...clientNum: " + clientNum);
                    handShakeComplete = true;
                }

                break;
            case LOGIN:
                //Login packet should only be sent to a server
                System.out.println("ClientControl Error: received a login packet");
                break;

            case DISCONNECT:
                //The server has disconnected, so display a message
                System.out.println("Server has disconnected");
                JOptionPane.showMessageDialog(
                	    null, "Server has disconnected...",
                	    "Game error",
                	    JOptionPane.ERROR_MESSAGE);
                break;

            default:
            	//An error has occured and the client does not know what this is
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

    //handle the sending of key presses
    public void sendKeyPress(String move) {
        String sendMove = "3" + clientNum + move;
        PacketMove movePacket = new PacketMove(sendMove.getBytes());
        movePacket.writeData(this);

    }

    // Added so GUI could get GameState via ClientControl
    public GameState getGameState(){
        return gameState;
	}

    // Added so GUI can compare client number to update specific players inventory
    public int getClientNum() {
        return this.clientNum;
    }

    /**
     * Bonnie added this here!
     * Method to save the game state!
     */
    public void save() {
        DataStorage.save(gameState);
    }

    public void load() {
    	gameState.setActors(DataStorage.load().getActors());
    	List<Actor> update = gameState.getActors();
		try {
			sendData(serial.serialize(update));
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
}
