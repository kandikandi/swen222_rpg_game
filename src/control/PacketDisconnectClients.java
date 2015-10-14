package control;

	/**A Packet that will be sent from a server to all its clients when the host
	 * exits the game, so that the clients know they must shut down
	 * This packet only needs its type, client needs no more info.
	 * @author mcleankand
	 *
	 */
	public class PacketDisconnectClients extends Packet {

		public PacketDisconnectClients(byte[] data) {
			super(7);
		}

		@Override
		public byte[] getData() {
			return "7".getBytes();
		}

	}




