//package model;
//
//import java.awt.Image;
//import java.io.Serializable;
//import java.net.InetAddress;
//
//import control.DIR;
//
//public class PlayerMP extends Player implements Serializable{
//
//	public InetAddress ipAddress;
//	public int port;
//	public String username;
//	public int playerid;
//
//	public PlayerMP(ID id, Position position, String imagePath, boolean collidable,
//			  boolean drawable, int boundingBoxSize, InetAddress ipAddress, int port, String uname) {
//		super(id, position, imagePath, collidable, drawable, boundingBoxSize);
//		this.username = uname;
//
//		this.ipAddress = ipAddress;
//		this.port = port;
//	}
//
//
//	public String getUsername() {
//		return username;
//	}
//
//	public int getPlayerid() {
//		return playerid;
//	}
//
//	public InetAddress getIpAddress() {
//		return ipAddress;
//	}
//
//	public int getPort() {
//		return port;
//	}
//
//	@Override
//	public void tick(){
//		super.tick();
//	}
//}
