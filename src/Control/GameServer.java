package Control;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import save.DataStorage;
import Control.Packet.PacketTypes;
import Model.Actor;
import Model.GameException;
import Model.GameState;
import Model.Player;

public class GameServer extends Thread {

	/**This thread runs the server. It takes the input, checks it, then sends to all the clients.**/

	private DatagramSocket socket;
	private GameState game;
	private ArrayList<ClientControl> connectedPlayers = new ArrayList<ClientControl>();
	private Serialiser serial = new Serialiser();

	public GameServer(){
		try {
		this.socket = new DatagramSocket(32768);
		} catch (SocketException e) {
			e.printStackTrace();
		}
	}

	public void setGame(GameState game) {
		this.game = game;
	}

	public void run(){
		while(true){

			byte[] data = new byte[60000];

			DatagramPacket packet = new DatagramPacket(data, data.length);

			try {
				socket.receive(packet);
			} catch (IOException e) {
				e.printStackTrace();
			}

			parsePacket(packet.getData(),packet.getAddress(), packet.getPort());

			List<Actor> update = game.getActors();

			try {
				sendDataToAllClients(serial.serialize(update));
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
	}
	/** Multiple packet classes which so can deal with different types of data, eg login, update....*/

	private void parsePacket(byte[] data, InetAddress address, int port) {

		String message = new String(data).trim();

		PacketTypes type = Packet.lookupPacket(message.substring(0,1));
		int clientNum = Packet.lookupClient(message.substring(1,2));

		switch(type){

		case LOGIN:

			PacketLogin packet = new PacketLogin(data);

			System.out.println("["+address.getHostAddress()+":"+port+"] "+ packet.getUserName()+" has connected..");
			System.out.println("");

			ClientControl playerControl = new ClientControl(packet.getUserName(), address, port, connectedPlayers.size()+1);

			addConnection(playerControl, packet);

			System.out.println("Player added, connected players == " + connectedPlayers.size());

			for(ClientControl p: connectedPlayers){
				System.out.println("Player: " + p.getUsername());
			}

			String username = "0"+connectedPlayers.size()+packet.getUserName();
			sendData(username.getBytes(), address, port);

			break;

		case DISCONNECT:

			break;

		case UPDATE:



			break;
		case MOVE:

			PacketMove packetMove = new PacketMove(data);
			String move = packetMove.getMove();
			Player player = game.findPlayer(packetMove.getClientNum());
			player.move(move);

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
	//TODO fixup this method, maybe just add a 2 if username exisits but we know
	// its not the same player based on ip-address - Cuan
	private void addConnection(ClientControl player, PacketLogin packet) {
		boolean alreadyConnected = false;
		for(ClientControl p: this.connectedPlayers){
			if(player.getUsername().equalsIgnoreCase(p.getUsername())){
				if(p.getIpAddress()==null){
					p.setIpAddress(player.getIpAddress());
				}
				if(p.getPort()==-1){
					p.setPort(player.getPort());
				}
				alreadyConnected=true;
			}
			else{//TODO - Learn why the line below breaks the game if player1 trys to move
				// after player2 has connected -- Cuan
				//sendData(packet.getData(),p.getIpAddress(),p.getPort());
			}
		}
		if(!alreadyConnected){
			this.connectedPlayers.add(player);
			game.createPlayer(connectedPlayers.size());
		}

	}

	//converts data into datagrams and sends it down the socket
	public void sendData(byte[] data, InetAddress ipAddress, int port){
		DatagramPacket packet  = new DatagramPacket(data, data.length, ipAddress, port);
		try {
			socket.send(packet);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	//for multiple players, calls send data for all connected players
	public void sendDataToAllClients(byte[] data) {
		for(ClientControl p : connectedPlayers){
			sendData(data, p.getIpAddress(), p.getPort());
		}

	}

	/**
	 * Bonnie added this here!
	 * Method to save the game state!
	 */
	public void save() {
		DataStorage.save(game);
	}

	public void load() {
		game = DataStorage.load();
	}
}
