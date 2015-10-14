package control;

/**Packet move sends through any keypresses made in clientControl to the server to be looked at by the logic
 * @author mcleankand
 *
 */
public class PacketMove extends Packet {

	private String move;
	private int clientNum;

	public PacketMove(byte[] data) {
		super(3);
		this.move = readData(data);
		this.clientNum = getClientNum(data);

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
