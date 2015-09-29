package network;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;

import packet.Packet;
import packet.Packet00Login;
import packet.Packet.PacketTypes;
import packet.Packet03GameState;
import control.GameController;
import control.Main;
import model.GameState;
import model.ID;
import model.Player;
import model.Position;

public class GameServer extends Thread {

	/**This thread runs the server. It takes the input, checks it, then sends to all the clients.**/

	private DatagramSocket socket;
	private GameState game;
//	private ArrayList<PlayerMP> connectedPlayers = new ArrayList<PlayerMP>();



	public GameServer(GameState game){
		this.game = game;
		try {

		this.socket = new DatagramSocket(1331);
		} catch (SocketException e) {
			e.printStackTrace();
		}
	}

	public void run(){
		while(true){
			byte[] data = new byte[1024]; //1024 for now
			DatagramPacket packet = new DatagramPacket(data, data.length);
			try {
				socket.receive(packet);
			} catch (IOException e) {
				e.printStackTrace();
			}
			parsePacket(packet.getData(),packet.getAddress(), packet.getPort());

			String message = new String(packet.getData());
			if (message.trim().equalsIgnoreCase("ping")){
			System.out.println("CLIENT > "+ message);
			sendData("PONG".getBytes(), packet.getAddress(),packet.getPort());
			}
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


			//PlayerMP player = (PlayerMP) game.factory.createPlayerActor(game.keylistener,address,port,packet.getUserName(),
			//connectedPlayers.size());
			//player.setInventory(game.factory.createInventory(true, 10, 10));
			//game.player = player;

			//addConnection(player, packet);
			//this.connectedPlayers.add(player);

			//System.out.println("Player added, connected players == " + connectedPlayers.size());

			//for(PlayerMP p: connectedPlayers){
			//	System.out.println("Player " + p.getPlayerid() + " is:::: "+ p.getUsername());
			//}

			//Packet03GameState update = new Packet03GameState(game.actors);
			//update.writeData(this);
			game.printGameObjectState();


			break;
		case DISCONNECT:
			break;
		}

	}

//	private void addConnection(PlayerMP player, Packet00Login packet) {
//		boolean alreadyConnected = false;
//		for(PlayerMP p: this.connectedPlayers){
//			if(player.getUsername().equalsIgnoreCase(p.getUsername())){
//				if(p.ipAddress==null){
//					p.ipAddress=player.ipAddress;
//				}
//				if(p.port==-1){
//					p.port = player.port;
//				}
//				alreadyConnected=true;
//			}
//			else{
//				sendData(packet.getData(),p.ipAddress,p.port);
//			}
//		}
//		if(!alreadyConnected){
//			this.connectedPlayers.add(player);
//			//game.actors.add(player);
//
//		}
//
//	}

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
//		for(PlayerMP p : connectedPlayers){
//			sendData(data, p.ipAddress, p.port);
//		}

	}
}
