package control;

/**Class for login packet, basically just creates a username atm*/

public class PacketLoginConfirm extends Packet{

    private String clientNum;

    public PacketLoginConfirm(byte[] data) {
        super(1);
        this.clientNum = readData(data);
        System.out.println("LoginConfirm constructor: clientNum.... ");
        System.out.println(clientNum);
    }

    @Override
    public void writeData(GameClient client) {


    }

    @Override
    public void writeData(GameServer server) {
        server.sendDataToAllClients(getData());
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
