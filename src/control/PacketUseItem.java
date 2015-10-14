package control;

import view.ActorAssets;

/**This packet is for informing the server that a player wishes to use a health item to decrease their
 * fear level.
 * @author mcleankand
 *
 */

public class PacketUseItem extends Packet {

	private String asciiString;
    private int clientNum;

    public PacketUseItem(byte[] data){
    	super(6);
		clientNum = getClientNum(data);
	    this.asciiString = readData(data);
    }

	@Override
	public byte[] getData() {
		String result = "6"+clientNum+this.asciiString;
    	return result.getBytes();
	}

	public ActorAssets getAsset() {
		return ActorAssets.getAssetName(asciiString.charAt(0));
	}

	public int getClientNum(){
		return clientNum;
	}

}
