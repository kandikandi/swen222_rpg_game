package control;

import view.ActorAssets;

public class PacketUseItem extends Packet {

	private String asciiString;
    private int clientNum;

    public PacketUseItem(byte[] data){
    	super(6);
		clientNum = getClientNum(data);
	    this.asciiString = readData(data);
	    System.out.println("PacketUse item construct: " + clientNum);
    }

	@Override
	public byte[] getData() {
		String result = "6"+clientNum+this.asciiString;
    	System.out.println("Packetuseitem: " + result);
    	return result.getBytes();
	}

	public ActorAssets getAsset() {
		return ActorAssets.getAssetName(asciiString.charAt(0));
	}

	public int getClientNum(){
		return clientNum;
	}

}
