package control;

public class PacketDisconnect extends Packet {

	private String username;
	private int clientNum;

	public PacketDisconnect(byte[] data) {
		super(4);
		this.clientNum = getClientNum(data);

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
		return ("4"+clientNum+this.username).getBytes();
	}

	public String getUserName() {
		return username;
	}

	public int getClientNum(){
		return clientNum;
	}

}


