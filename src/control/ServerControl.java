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


    public ServerControl() {
        try {
            this.socket = new DatagramSocket(Main.PORT);
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }

    public void setGame(GameState game) {
        this.game = game;
    }

    public void run() {
        while (isRunning) {

            try {
                Thread.sleep(12);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

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

            updateClients();


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

            case DISCONNECT:
                PacketDisconnect disconnect = new PacketDisconnect(data);
                Player playerDisconnect = game.findPlayer(disconnect.getClientNum());

                for (int i = 0; i < connectedPlayers.size(); i++) {
                    if (connectedPlayers.get(i).getClientNum() == disconnect.getClientNum()) {
                        connectedPlayers.remove(i);
                        break;
                    }
                }

                game.removePlayer(playerDisconnect);
                //TODO: find out how to properly close server process
                //If there is no longer any players connected, it means there is no one playing, so shutdown the server.
                if (connectedPlayers.size() == 0) {
                    MainServer.shutDownServer();
                }

                break;

            case UPDATE:
                //TODO: Server should never recieve packet of type UPDATE, its for server to send not receive --Cuan
                //For loading a game for a client
                try {
                    throw new GameException("Server should never recieve packet of type UPDATE");
                } catch (GameException e) {
                    e.printStackTrace();
                }
                /*    ArrayList<Actor> recd;
                try {
                    recd = (ArrayList<Actor>) serial.deserialize(data);
                    game.setActors(recd);
                } catch (ClassNotFoundException e1) {
                    e1.printStackTrace();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }*/
                break;

            case MOVE: {
                PacketMove packetMove = new PacketMove(data);
                String move = packetMove.getMove();
                Player player = game.findPlayer(packetMove.getClientNum());
                player.move(game, move);
            }
            break;

            case DROPITEM: {
                PacketDropItem packet1 = new PacketDropItem(data);
                ActorAssets item = packet1.getAsset();
                Player player1 = game.findPlayer(packet1.getClientNum());
                player1.dropItemID(item);
            }
            break;

            case USEITEM: {
                PacketUseItem packetUse = new PacketUseItem(data);
                ActorAssets item = packetUse.getAsset();
                Player playerUser = game.findPlayer(packetUse.getClientNum());
                playerUser.eatCandy();

                break;
            }
            default:
                try {
                    throw new GameException("Server Packet Header Error");
                } catch (GameException e) {
                    e.printStackTrace();
                }
                break;
        }

    }

    public void updateClients() {
        // Send out game view to clients
        List<Actor> update = game.getActors();
        if(Main.TEST_MODE){
            Actor act = update.get(3);
            int xPos = act.getPosition().getxPos();
            int yPos = act.getPosition().getyPos();
            char image = act.getImageName();
            char asciiCode = act.getAsciiCode();
            System.out.println("*********************************************************************");
            System.out.println("ServerControl updateClients: first actor in list stats");
            System.out.println("xPos: "+xPos+" yPos: "+yPos+" image: "+image+" asciiCode: "+asciiCode);
            System.out.println("*********************************************************************");
        }
        try {
            sendDataToAllClients(serial.serialize(update));
        } catch (IOException e) {
            e.printStackTrace();
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

    // converts data into datagrams and sends it down the socket
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

    // for multiple players, calls send data for all connected players
    synchronized public void sendDataToAllClients(byte[] data) {
        for (ClientData p : connectedPlayers) {
            sendData(data, p.getIpAddress(), p.getPort());
        }

    }

    public void shutDownServer() {
        isRunning = false;
    }
}
