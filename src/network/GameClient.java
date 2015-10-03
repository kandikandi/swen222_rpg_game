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
import packet.Serialiser;
import control.GameController;
import control.Main;
import model.Actor;
import model.GameState;

public class GameClient extends Thread {

	/**this thread is for the clients. I think this will eventually be the thread which takes the input from the players,
	 * but am not 100% sure yet. at the moment it doesn't really do much
	 */


	private InetAddress ipAddress;
	private DatagramSocket socket;
	private GameState game;
	Serialiser serial = new Serialiser();

	public GameClient(String ipAddress, GameState game){
		this. game  = game;
		try {
			this.ipAddress = InetAddress.getByName(ipAddress);

		this.socket = new DatagramSocket();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (SocketException e) {
			e.printStackTrace();
		}
	}

	public void run(){
		while(true){

			byte[] data = new byte[60000];
			DatagramPacket packet = new DatagramPacket(data, data.length);

			try {
				socket.receive(packet);
				//String message = new String(packet.getData());
				//System.out.println("SERVER > "+ message);
			} catch (IOException e) {
				e.printStackTrace();
			}

			parsePacket(packet.getData(),packet.getAddress(), packet.getPort());

		}
	}


	private void parsePacket(byte[] data, InetAddress address, int port) {
		String message = new String(data).trim();
		PacketTypes type = Packet.lookupPacket(message.substring(0,2));//1st 2 digits is packet id

		switch(type){
		case INVALID:

			ArrayList<Actor> recd;
			try {
				recd = (ArrayList<Actor>) serial.deserialize(data);
				for(Actor a: recd){
					a.printState();
				}
				game.setActors(recd);
			} catch (ClassNotFoundException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}


//			Packet03GameState updatePacket = new Packet03GameState(data);
//			game.setActors(updatePacket.getGameUpdate());
//			game.printGameObjectState();
//			System.out.println("Client checking packet now...invalid....");

			break;
		case LOGIN:
			Packet00Login packet = new Packet00Login(data);
			System.out.println("["+address.getHostAddress()+":"+port+"] "+ packet.getUserName()+" has joined the game..");
			game.printGameObjectState();
			break;
		case DISCONNECT:
			//System.out.println("Client checking packet now...disconnect");
			break;
		case UPDATE:
			break;
		default:
			break;
		}

	}

	//send data to the server
	public void sendData(byte[] data){
		DatagramPacket packet  = new DatagramPacket(data, data.length, ipAddress, 1331);
		try {
			socket.send(packet);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
