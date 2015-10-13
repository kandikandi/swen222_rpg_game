package control;

/**Class for login packet, basically just creates a username atm*/

public class PacketLogin extends Packet{

	private String username;

	public PacketLogin(byte[] data) {
		super(0);
		this.username = readData(data);
	}

	@Override
	public void writeData(GameClient client) {
		client.sendData(getData());

	}

	@Override
	public void writeData(GameServer server) {

	}

	@Override
	public byte[] getData() {
		return ("00"+this.username).getBytes();
	}

	public String getUserName() {
		return username;
	}

}
