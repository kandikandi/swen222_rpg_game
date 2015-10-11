package control;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;

import save.DataStorage;
import control.Packet.PacketTypes;
import model.Actor;
import model.GameException;
import model.GameState;
import model.Player;

public class GameServer extends Thread {

    /**
     * This thread runs the server. It takes the input, checks it, then sends to all the clients.
     */

    private DatagramSocket socket;
    private GameState game;
    private ArrayList<ClientData> connectedPlayers = new ArrayList<ClientData>();
    private Serialiser serial = new Serialiser();
    private boolean isRunning = true;

    public GameServer() {
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
            //Create empty packet
            byte[] data = new byte[60000];
            DatagramPacket packet = new DatagramPacket(data, data.length);
            //Try to read buffer into packet
            try {
                socket.receive(packet);
            } catch (IOException e) {
                e.printStackTrace();
            }
            //Parse packet and update gameState
            parsePacket(packet.getData(), packet.getAddress(), packet.getPort());

            //Send out game view to clients
            List<Actor> update = game.getActors();
            try {
                sendDataToAllClients(serial.serialize(update));
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }


    /**
     * Multiple packet classes which so can deal with different types of data, eg login, update....
     */

    synchronized private void parsePacket(byte[] data, InetAddress address, int port) {
        String packetID = new String(data).trim();

        //TODO use enum class to work out type from packetID
        PacketTypes type = Packet.lookupPacket(packetID.substring(0, 1));
        switch (type) {

            case LOGIN:
                PacketLogin packet = new PacketLogin(data);
                //TODO: Use a propper logging library for TEST_MOVE=true instead of println
                //TODO: remove this
                if (Main.TEST_MODE) {
                    System.out.println("[" + address.getHostAddress() + ":" + port + "] " + packet.getUserName() + " has connected..");
                    System.out.println("");
                }
                //Process connection
                addConnection(packet, address, port);
                if (Main.TEST_MODE) {
                    System.out.println("Player added, connected players == " + connectedPlayers.size());
                    for (ClientData p : connectedPlayers) {
                        System.out.println("Player: " + p.getUsername());
                    }
                }
                //Without any synchronisation this logic "crash starts" the client server communication cycle
                //as well as the game engine cycle
                //String username = "0" + connectedPlayers.size() + packet.getUserName();
                //sendData(username.getBytes(), address, port);
                break;

            case DISCONNECT:
                break;

            case UPDATE:
                break;

            case MOVE:
                PacketMove packetMove = new PacketMove(data);
                String move = packetMove.getMove();
                Player player = game.findPlayer(packetMove.getClientNum());
                player.move(game, move);
                //temp logic to test shutting down the server
                //TODO: remove this logic when PacketDisconnect is implemented
                if (move.equals("SPACE")) {
                    System.out.println("GameServer MOVE: SPACE recieved");
                    MainServer.shutDownServer();
                }


                break;

            default:
                try {
                    throw new GameException("Server Packet Header Error");
                } catch (GameException e) {
                    e.printStackTrace();
                }
                break;
        }

    }

    /**
     * Creates a ClientInfo/ClientData object and add's to collection. Also tells game-state
     * create a player
     */
    synchronized private void addConnection(PacketLogin packet, InetAddress hostAddress, int port) {
        int clientNum = connectedPlayers.size() + 1;
        ClientData playerData = new ClientData(packet.getUserName(), hostAddress, port, clientNum);
        this.connectedPlayers.add(playerData);
        game.createPlayer(clientNum);
        String toSend = "10" + Integer.toString(clientNum);
        System.out.println("GamerServer.addConnection() about to create send LoginConfirm packet out toSend: " + toSend);
        //TODO should only send confirmation packet to the intended client instead of publicly broadcast
        LoginConfirm confirmPacket = new LoginConfirm(toSend.getBytes());
        confirmPacket.writeData(this);
        //sendData(serial.serialize(game.getActors()),hostAddress,port);
    }

    //converts data into datagrams and sends it down the socket
    synchronized public void sendData(byte[] data, InetAddress ipAddress, int port) {
        DatagramPacket packet = new DatagramPacket(data, data.length, ipAddress, port);
        try {
            socket.send(packet);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //for multiple players, calls send data for all connected players
    synchronized public void sendDataToAllClients(byte[] data) {
        for (ClientData p : connectedPlayers) {
            sendData(data, p.getIpAddress(), p.getPort());
        }

    }

    public void shutDownServer() {
        isRunning = false;
    }
}
