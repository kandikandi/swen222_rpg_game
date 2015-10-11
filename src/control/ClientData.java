package control;

import model.Player;

import java.net.InetAddress;

/**player control class for server to use to differentiate between clients*/

public class ClientData {

	private String username;
	private InetAddress ipAddress;
	private int port;
	private int clientNum;
	private Player player;

	//TODO maybe add Player reference in here, thus no need for costly search -- Cuan
	public ClientData(String uname, InetAddress ipAddress, int port, int clientNum) {
		this.username = uname;
		this.setIpAddress(ipAddress);
		this.setPort(port);
		this.clientNum = clientNum;
	}
	public void setPlayer(Player player){this.player = player;}

	public int getClientNum() {
		return clientNum;
	}

	public String getUsername() {
		return username;
	}

	public InetAddress getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(InetAddress ipAddress) {
		this.ipAddress = ipAddress;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public Player getPlayer(){
		return player;
	}

}
