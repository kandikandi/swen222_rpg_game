package control;



/**Class for login packet, basically just creates a username atm*/

public class LoginConfirm extends Packet{

    private String clientNum;

    public LoginConfirm(byte[] data) {
        super(1);
        this.clientNum = readData(data);
        System.out.println("LoginConfirm constructor: clientNum.... ");
        System.out.println(clientNum);
    }

    @Override
    public void writeData(GameClient client) {
        client.sendData(getData());

    }

    @Override
    public void writeData(GameServer server) {
        server.sendDataToAllClients(getData());
    }


    @Override
    public byte[] getData() {
        String data = "10"+this.clientNum;
        //String data = PacketTypes.LOGINCONFIRM+clientNum.toString()+clientNum.toString();
        return data.getBytes();
    }

    public int getClientNumber(){
        System.out.println("LoginConfirm.getClientNumber");
        int result = Integer.parseInt(clientNum);
        System.out.println(result);
        return result;

        //return Integer.parseInt(clientNum);
    }

}
