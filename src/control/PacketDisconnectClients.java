package control;


	public class PacketDisconnectClients extends Packet {


		public PacketDisconnectClients(byte[] data) {
			super(7);

		}

		@Override
		public byte[] getData() {
			return "7".getBytes();
		}

	}




