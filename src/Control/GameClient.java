package Control;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;

import Control.Packet.PacketTypes;
import Model.Actor;
import Model.GameException;
import Model.GameState;

public class GameClient extends Thread {

	/**this thread is for the clients. I think this will eventually be the thread which takes the input from the players,
	 * but am not 100% sure yet. at the moment it doesn't really do much
	 */

	private InetAddress ipAddress;
	private DatagramSocket socket;
	private GameState game;
	Serialiser serial = new Serialiser();
	private int clientNum;

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
			} catch (IOException e) {
				e.printStackTrace();
			}

			parsePacket(packet.getData(),packet.getAddress(), packet.getPort());

		}
	}


	private void parsePacket(byte[] data, InetAddress address, int port) {

		String message = new String(data).trim();
		PacketTypes type = Packet.lookupPacket(message.substring(0,1));

		switch(type){
		case UPDATE:
			ArrayList<Actor> recd;
			try {
				recd = (ArrayList<Actor>) serial.deserialize(data);
				game.setActors(recd);
			} catch (ClassNotFoundException | IOException e) {
				e.printStackTrace();
			}
			break;

		case LOGIN:
			clientNum = Packet.lookupClient(message.substring(1, 2));
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
	public void sendData(byte[] data){
		DatagramPacket packet  = new DatagramPacket(data, data.length, ipAddress, 32768);
		try {
			socket.send(packet);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void sendKeyPress(String move) {

		String sendMove = "3"+clientNum+move;
		PacketMove movePacket = new PacketMove(sendMove.getBytes());
		movePacket.writeData(this);

	}

	// Added so GUI could get GameState via GameClient
	public GameState getGameState(){
		return game;
	}

	// Added so GUI can compare client number to update specific players inventory
	public int getClientNum() {
		return this.clientNum;
	}
}
