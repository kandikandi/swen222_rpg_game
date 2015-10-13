package control;

/**this is an abstract class which will be used to decipher the packets,
 * will eventually be a login, disconnect, invalid, update state, and anything else thats needed.*/

public abstract class Packet {
	//TODO find out if LOGINCONFIRM is used, or should be used. Sounds like it would be usefull for syncing game.
	public static enum PacketTypes{
		LOGIN(0),LOGINCONFIRM(1),UPDATE(2), MOVE(3), DISCONNECT(4), DROPITEM(5), USEITEM(6);

		private int packetID;
		private int clientNum;

		private PacketTypes(int packetID){
			this.packetID = packetID;
		}

		private int getID() {
			return packetID;
		}

		public int getClientNum(){
			return clientNum;
		}
	}

	public byte packetID;

	public Packet(int packetID){
		this.packetID = (byte)packetID;


	}

	public abstract void writeData(GameClient client);

	public abstract void writeData(GameServer server);

	public String readData(byte[] data){
		String message = new String(data).trim();
		return message.substring(2);
	}

	public int getClientNum(byte[] data){
		String num = new String(data).substring(1, 2);
		return Integer.parseInt(num);
	}

	public abstract byte[] getData();

	public static PacketTypes lookupPacket(int id){
		for(PacketTypes p : PacketTypes.values()){
			if(p.getID()==id){
				return p;
			}
		}
		return PacketTypes.UPDATE;
	}


	//TODO fix this up if time
	public static PacketTypes lookupPacket(String packetID) {
		try{
		return lookupPacket(Integer.parseInt(packetID));
		} catch (NumberFormatException e){
			return PacketTypes.UPDATE;
		}
	}

	public static int lookupClient(String substring) {
		try{
			return Integer.parseInt(substring);
			} catch (NumberFormatException e){
				e.printStackTrace();;
			}
		return -1;
	}

}
