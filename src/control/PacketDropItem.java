package control;

import view.ActorAssets;

/**Class for item drop packet, basically just creates a username atm*/

public class PacketDropItem extends Packet{

    private String asciiString;
    private int clientNum;


    public PacketDropItem(byte[] data) {
        super(5);
        clientNum = getClientNum(data);
        this.asciiString = readData(data);
        System.out.println("Packetdrop item construct: " + clientNum);
    }

    @Override
    public byte[] getData() {
    	String result = "5"+clientNum+this.asciiString;
    	System.out.println("Packetdropitem: " + result);
    	return result.getBytes();
    }

	public ActorAssets getAsset() {
		return ActorAssets.getAssetName(asciiString.charAt(0));
	}

	public int getClientNum(){
		return clientNum;
	}

}
