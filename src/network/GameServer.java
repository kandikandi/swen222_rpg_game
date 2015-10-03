package network;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import packet.Packet;
import packet.Packet00Login;
import packet.Packet.PacketTypes;
import packet.Packet03GameState;
import packet.Serialiser;
import control.GameController;
import control.Main;
import control.PlayerControl;
import model.Actor;
import model.GameState;
import model.ID;
import model.Player;
import model.Position;

public class GameServer extends Thread {

	/**This thread runs the server. It takes the input, checks it, then sends to all the clients.**/

	private DatagramSocket socket;
	private GameState game;
	private GameController control;
	private ArrayList<PlayerControl> connectedPlayers = new ArrayList<PlayerControl>();
	private Serialiser serial = new Serialiser();



	public GameServer(GameState game, GameController control){
		this.game = game;
		this.control = control;
		try {

		this.socket = new DatagramSocket(1331);
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

//			String message = new String(packet.getData());
//			if (message.trim().equalsIgnoreCase("ping")){
//			System.out.println("CLIENT > "+ message);
//			sendData("PONG".getBytes(), packet.getAddress(),packet.getPort());
//			}
		}
	}
	/** Multiple packet classes which so can deal with different types of data, eg login, update....*/

	private void parsePacket(byte[] data, InetAddress address, int port) {
		String message = new String(data).trim();
		PacketTypes type = Packet.lookupPacket(message.substring(0,2));//1st 2 digits is packet id
		switch(type){
		default:
		case INVALID:
			break;
		case LOGIN:

			Packet00Login packet = new Packet00Login(data);
			System.out.println("["+address.getHostAddress()+":"+port+"] "+ packet.getUserName()+" has connected..");
			System.out.println("");

			//createPlayerControl
			PlayerControl playerControl = new PlayerControl(packet.getUserName(), address, port, connectedPlayers.size()+1);
			game.createPlayer();

			addConnection(playerControl, packet);

			System.out.println("Player added, connected players == " + connectedPlayers.size());

			for(PlayerControl p: connectedPlayers){
				System.out.println("Player is:::: "+ p.getUsername());
			}

			List<Actor> update = game.getAllActors();
			//Player play =


			try {
				sendDataToAllClients(serial.serialize(update));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}


			//byte[] ser = game.sendUpdate();
			//System.out.println("serialising is here");
			//Packet03GameState packet03 = new Packet03GameState(ser);
			//send


			//game.printGameObjectState();


			break;
		case DISCONNECT:
			break;
		}

	}

	private void addConnection(PlayerControl player, Packet00Login packet) {
		boolean alreadyConnected = false;
		for(PlayerControl p: this.connectedPlayers){
			if(player.getUsername().equalsIgnoreCase(p.getUsername())){
				if(p.getIpAddress()==null){
					p.setIpAddress(player.getIpAddress());
				}
				if(p.getPort()==-1){
					p.setPort(player.getPort());
				}
				alreadyConnected=true;
			}
			else{
				sendData(packet.getData(),p.getIpAddress(),p.getPort());
			}
		}
		if(!alreadyConnected){
			this.connectedPlayers.add(player);

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
		for(PlayerControl p : connectedPlayers){
			sendData(data, p.getIpAddress(), p.getPort());
		}

	}
}
