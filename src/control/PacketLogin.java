package control;

/**Class for login packet. Informs the server that the client is there and what the
 * players username is.
 * @author mcleankand
 *
 */

public class PacketLogin extends Packet{

	private String username;

	public PacketLogin(byte[] data) {
		super(0);
		this.username = readData(data);
	}

	@Override
	public byte[] getData() {
		return ("00"+this.username).getBytes();
	}

	public String getUserName() {
		return username;
	}

}
