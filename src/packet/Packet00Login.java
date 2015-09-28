package packet;


import network.GameClient;
import network.GameServer;

/**Class for login packet, basically just creates a username atm*/

public class Packet00Login extends Packet{

	private String username;
	private Serialiser serialise = new Serialiser();

	public Packet00Login(byte[] data) {
		super(00);
		this.username = readData(data);
	}

	@Override
	public void writeData(GameClient client) {
		client.sendData(getData());

	}

	@Override
	public void writeData(GameServer server) {
		server.sendDataToAllClients(getData());
	}

	@Override
	public byte[] getData() {
		return ("00"+this.username).getBytes();
	}

	public String getUserName() {
		return username;
	}

}
