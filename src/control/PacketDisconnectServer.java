package control;

public class PacketDisconnectServer extends Packet {

	private String username;
	private int clientNum;

	public PacketDisconnectServer(byte[] data) {
		super(4);
		this.clientNum = getClientNum(data);

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


