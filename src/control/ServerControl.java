package control;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;

import save.DataStorage;
import view.ActorAssets;
//import view.ID;
import control.Packet.PacketTypes;
import model.Actor;
import model.Enemy;
import model.GameException;
import model.GameState;
import model.Player;
import view.GameCamera;

public class ServerControl extends Thread {

    /**
     * This thread runs the server. It takes input from the clients and deals with it appropriatly.
     * It also contains the methods used by Timer to send updates to all the clients
     *
     * @author mcleankand
     */

    private DatagramSocket socket;
    private GameState game;
    private ArrayList<ClientData> connectedPlayers = new ArrayList<ClientData>();
    private Serialiser serial = new Serialiser();
    private boolean isRunning = true;
    private GameCamera camera = new GameCamera();


    public ServerControl() {
        try {
            this.socket = new DatagramSocket(Main.PORT);
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }

    synchronized public void setGame(GameState game) {
        this.game = game;
    }

    synchronized public GameState getGameState(){
    	return game;
    }

    public void run() {
        while (true) {


            // Create empty packet
            byte[] data = new byte[60000];
            DatagramPacket packet = new DatagramPacket(data, data.length);

            // Try to read buffer into packet
            try {
                socket.receive(packet);
            } catch (IOException e) {
                e.printStackTrace();
            }

            // Parse packet and update gameState
            parsePacket(packet.getData(), packet.getAddress(), packet.getPort());




        }
    }

    /**
     * Multiple packet classes which so can deal with different types of data,
     * eg login, update....
     */

    synchronized private void parsePacket(byte[] data, InetAddress address,
                                          int port) {

        //Trim string, parse first 2 digits so know who sent it and what type of data it is
        String packetID = new String(data).trim();
        PacketTypes type = Packet.lookupPacket(packetID.substring(0, 1));


        switch (type) {

            case LOGIN:
            	//New client is logging in
                PacketLogin packet = new PacketLogin(data);

                if (Main.TEST_MODE) {
                    System.out.println("[" + address.getHostAddress() + ":" + port
                            + "] " + packet.getUserName() + " has connected..");
                    System.out.println("");
                }

                // Process connection
                addConnection(packet, address, port);
                if (Main.TEST_MODE) {
                    System.out.println("Player added, connected players == "
                            + connectedPlayers.size());
                    for (ClientData p : connectedPlayers) {
                        System.out.println("Player: " + p.getUsername());
                    }
                }
                break;

            case DISCONNECTSERVER:
            	//Has recieved a disconnection request from a client
                PacketDisconnectServer disconnect = new PacketDisconnectServer(data);
                Player playerDisconnect = game.findPlayer(disconnect.getClientNum());

                for (int i = 0; i < connectedPlayers.size(); i++) {
                    if (connectedPlayers.get(i).getClientNum() == disconnect.getClientNum()) {
                        connectedPlayers.remove(i);
                        break;
                    }
                }

                System.out.println("Server removed player::" + disconnect.getClientNum());

                game.removePlayer(playerDisconnect);
                System.out.println("Connected players == "+ connectedPlayers.size());

                if(disconnect.getClientNum()==1){
                	PacketDisconnectClients disconnectClients = new PacketDisconnectClients("7".getBytes());
                	disconnectClients.writeData(this);
                }

                //If there is no longer any players connected, it means there is no one playing, so shutdown the server.
                if (connectedPlayers.size() == 0) {
                    System.exit(0);
                }

                break;

            case UPDATE:
            	//should never recieve an update error
                try {
                    throw new GameException("Server should never recieve packet of type UPDATE");
                } catch (GameException e) {
                    e.printStackTrace();
                }

                break;

            case DISCONNECTCLIENTS:
            	//should never recieve an update error
                try {
                    throw new GameException("Server should never recieve packet of type DISCONNECTCLIENTS");
                } catch (GameException e) {
                    e.printStackTrace();
                }

                break;

            case MOVE: {
            	//Inform Gamestate of a move from a client
                PacketMove packetMove = new PacketMove(data);
                String move = packetMove.getMove();
                Player player = game.findPlayer(packetMove.getClientNum());
                player.move(game, move);
            }
            break;

            case DROPITEM: {
            	//Inform state a client dropped an item
                PacketDropItem packet1 = new PacketDropItem(data);
                ActorAssets item = packet1.getAsset();
                Player player1 = game.findPlayer(packet1.getClientNum());
                player1.dropItemID(item);
            }
            break;

            case USEITEM: {
            	//Inform gamestate a client used an item
                PacketUseItem packetUse = new PacketUseItem(data);
                ActorAssets item = packetUse.getAsset();
                Player playerUser = game.findPlayer(packetUse.getClientNum());
                playerUser.eatCandy();

                break;
            }
            default:
            	//should never get here so if it does, an error has occurred
                try {
                    throw new GameException("Server Packet Header Error");
                } catch (GameException e) {
                    e.printStackTrace();
                }
                break;
        }

    }

    /**Update all the clients with changes that have occurred in the world
     * of their cameras.
     */
     synchronized public void updateClients() {

        for (ClientData p : connectedPlayers) {
            List<Actor> actorView = camera.getActorView(game,p.getClientNum());
            try {
                sendData(serial.serialize(actorView), p.getIpAddress(), p.getPort());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    /**
     * Creates a ClientInfo/ClientData object and add's to collection. Also
     * tells game-state create a player
     */
    synchronized private void addConnection(PacketLogin packet, InetAddress hostAddress, int port) {

        int clientNum = connectedPlayers.size() + 1;

        ClientData playerData = new ClientData(packet.getUserName(), hostAddress, port, clientNum);
        this.connectedPlayers.add(playerData);
        game.createPlayer(clientNum);

        String toSend = "10" + Integer.toString(clientNum);
        System.out
                .println("GamerServer.addConnection() about to create send LoginConfirm packet out toSend: "
                        + toSend);

        PacketLoginConfirm confirmPacket = new PacketLoginConfirm(toSend.getBytes());
        confirmPacket.writeData(this);

    }

    /** converts data into datagrams and sends it down the socket
     *
     * @param data
     * @param ipAddress
     * @param port
     */
    synchronized public void sendData(byte[] data, InetAddress ipAddress,
                                      int port) {
        DatagramPacket packet = new DatagramPacket(data, data.length,
                ipAddress, port);
        try {
            socket.send(packet);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

   /** as above, for multiple players, calls send data for all connected players
    *
    * @param data
    */
    synchronized public void sendDataToAllClients(byte[] data) {
        for (ClientData p : connectedPlayers) {
            sendData(data, p.getIpAddress(), p.getPort());
        }

    }

}
