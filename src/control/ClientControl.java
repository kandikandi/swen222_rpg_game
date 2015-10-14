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
import view.Renderer;

public class ClientControl extends Thread {


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

    //Clients identification to server
    private int clientNum = 0;

    //Confirmation that the client is logged in with server
    boolean handShakeComplete = false;

    //UI/Rendering
    private Renderer renderer;


    /**Constructor takes an Ip Address, a state, and the canvas associated with this client
     * it will also construct the socket that data will be passed through to and from the server
     * @param ipAddress
     * @param gameState
     * @param gameCanvas
     */

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


    /**run method to loop through continuously, sleep for 10ms, then wait for input from server before proceeding any further
     * */

    public void run() {
        while (true) {

            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            //buffer to store data from the server.
            byte[] data = new byte[60000];
            DatagramPacket packet = new DatagramPacket(data, data.length);

            //Wait until the socket has mail
            try {
                socket.receive(packet);
            } catch (IOException e) {
                e.printStackTrace();
            }

            //find out what kind of packet it is and deal with it
            parsePacket(packet.getData(), packet.getAddress(), packet.getPort());
            //Once everything is updated, rerender the scene with the new state
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
                try {
                    if (clientNum != 0) { //if a valid client
                    	recd = (ArrayList<Actor>) serial.deserialize(data);
                        gameState.setActors(recd);
                    }
                } catch (ClassNotFoundException | IOException e) {
                    System.out.println(e.getMessage());
                    e.printStackTrace();
                }

                break;

            case LOGINCONFIRM:

            	//If Loginconfirm, the server recieved the login packet, and is sending the client its number.
            	//Client needs to update itself that it has completed a handshake.

                //If handshake is already complete, an error must've occurred.
                if (handShakeComplete) {
                    //System.out.println("ClientControl already authenticated, LOGINCONFIRM not for me");
                    break;
                } else {
                    PacketLoginConfirm loginConfirm = new PacketLoginConfirm(data);
                    clientNum = loginConfirm.getClientNumber();
                    //System.out.println("ClientControl LOGINCONFIRM recieved...clientNum: " + clientNum);
                    handShakeComplete = true;
                }

                break;

            case LOGIN:

                //Login packet should only be sent to a server
            	 try {
                     throw new GameException("Client recieved a login request...");
                 } catch (GameException e) {
                     e.printStackTrace();
                 }

                break;

            case DISCONNECTCLIENTS:

                //The server has disconnected, so display a message and exit the game
                //System.out.println("Server has disconnected");
                JOptionPane.showMessageDialog(
                	    null, "Host Player has disconnected...exiting game",
                	    "Game error",
                	    JOptionPane.ERROR_MESSAGE);
                System.exit(0);

                break;

            default:

            	//Client doesnt know what this packet is..
                try {
                    throw new GameException("Client Packet Header Error");
                } catch (GameException e) {
                    e.printStackTrace();
                }
                break;
        }

    }

    /**Method that takes a byte array and writes it to the server socket. Called by the Packet class and its children
     *
     * @param data
     */
    public void sendData(byte[] data) {
        DatagramPacket packet = new DatagramPacket(data, data.length, ipAddress, GlobalConst.PORT);
        try {
            socket.send(packet);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**Method for constructing a movePacket and writing it to the server
     * Different to the prev method as takes a button press as a String instead of a byte array
     * @param move
     */
    public void sendKeyPress(String move) {
        String sendMove = "3" + clientNum + move;
        PacketMove movePacket = new PacketMove(sendMove.getBytes());
        movePacket.writeData(this);

    }


    /**GETTERS AND SETTERS
     *
     * @return
     */
    public GameState getGameState(){
        return gameState;
	}

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
