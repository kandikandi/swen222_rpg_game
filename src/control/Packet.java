package control;

/**This abstract class contains the basic methods and variables needed for a packet to be parsed and deciphered
 * in the server and client control classes. It also contains an enum for packet types for use in the switch
 * cases in the server and client control that deal with different packet types.
 * @author mcleankand
 *
 */

	public abstract class Packet {

	public byte packetID; // 0...7 each contains different data

	public Packet(int packetID){
		this.packetID = (byte)packetID; //first digit of any data in a packet will always tell what the type is

	}

	/**check enums and find out what type of packet this data belongs to
	 *
	 * @param id
	 * @return PacketType
	 */
	public static PacketTypes lookupPacket(int id){
		for(PacketTypes p : PacketTypes.values()){
			if(p.getID()==id){
				return p;
			}
		}

		//Bit hacky, couldn't make it work any other way though. Technically this should return an error.
		return PacketTypes.UPDATE;
	}

	/**Parse the packet for its integer
	 *
	 * @param packetID
	 * @return PacketType
	 */
	public static PacketTypes lookupPacket(String packetID) {

		try{
		return lookupPacket(Integer.parseInt(packetID));
		} catch (NumberFormatException e){
			//Bit hacky, couldn't make it work any other way though. Technically this should return an error.
			return PacketTypes.UPDATE;
		}
	}

	/**Parse the data for an integer and return an int representing the clientNumber
	 *
	 * @param data
	 * @return int
	 */
	public int getClientNum(byte[] data){
		String num = new String(data).substring(1, 2);
		return Integer.parseInt(num);
	}

	/**if a client is passed in as param, send the packet to the server
	 *
	 * @param client
	 */
	public void writeData(ClientControl client){
		client.sendData(getData());
	}

	/**Overload of previous method.
	 * if a server is passed in as param, send the packet to all its clients
	 * @param server
	 */
	public void writeData(ServerControl server){
		server.sendDataToAllClients(getData());
	}


	/**Trim the first 2 digits off and return it, as the first 2 digits will be the client number and the
	 * packetType. Data refers to the actual information being conveyed to client/server only.
	 * @param data
	 * @return String
	 */
	public String readData(byte[] data){
		String message = new String(data).trim(); //clean up dead spaces
		return message.substring(2); // return the string minus the first 2 digits
	}

	/**An abstract method, as each data type is different, need to set up the string in different ways.
	 * Will return a byte array ready to be sent down the socket
	 * @return byte[]
	 */
	public abstract byte[] getData();


	/**Enum for keeping track of different data types
	 *
	 * @author mcleankand
	 *
	 */
	public static enum PacketTypes{
		LOGIN(0),LOGINCONFIRM(1),UPDATE(2), MOVE(3), DISCONNECTSERVER(4), DROPITEM(5), USEITEM(6), DISCONNECTCLIENTS(7);

		private int packetID;

		private PacketTypes(int packetID){
			this.packetID = packetID;
		}

		private int getID() {
			return packetID;
		}

	}

}
