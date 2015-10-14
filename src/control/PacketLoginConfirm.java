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
        System.out.println("LoginConfirm constructor: clientNum.... ");
        System.out.println(clientNum);
    }

    @Override
    public byte[] getData() {
        System.out.println("LoginConfirm getData:"+"10"+this.clientNum);
     return ("10"+this.clientNum).getBytes();
    }

    public int getClientNumber(){
        System.out.println("LoginConfirm.getClientNumber");
        int result = Integer.parseInt(clientNum);
        System.out.println(result);
        return result;


    }

}
