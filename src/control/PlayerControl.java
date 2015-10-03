package control;

import java.net.InetAddress;

import javax.xml.bind.annotation.XmlTransient;

import model.Player;

public class PlayerControl {

	private String username;
	private InetAddress ipAddress;
	private int port;
	private int playernum;
	Player thisPlayer;

	public PlayerControl(String uname, InetAddress ipAddress, int port, int playernum ) {
		this.playernum = playernum;
		this.username = uname;
		this.setIpAddress(ipAddress);
		this.setPort(port);
		//this.thisPlayer = play;
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

}
