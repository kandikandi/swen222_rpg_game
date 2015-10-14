package control;

/**The packet class responsible for returning a handshake to the server and informing the client
 * what its client number is.
 * @author mcleankand
 *
 */

public class PacketLoginConfirm extends Packet{

    private String clientNum;

    public PacketLoginConfirm(byte[] data) {
        super(1);
        this.clientNum = readData(data);
    }

    @Override
    public byte[] getData() {
     return ("10"+this.clientNum).getBytes();
    }

    public int getClientNumber(){
        int result = Integer.parseInt(clientNum);
        return result;


    }

}
