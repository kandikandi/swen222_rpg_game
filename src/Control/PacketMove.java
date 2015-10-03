package Control;

public class PacketMove extends Packet {

	private String move;
	private int clientNum;

	public PacketMove(byte[] data) {
		super(3);
		this.move = readData(data);
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
		return ("3"+clientNum+this.move).getBytes();
	}

	public String getMove(){
		return move;
	}

	public int getClientNum(){
		return clientNum;
	}

}
