package packet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import model.Actor;
import model.GameState;
import network.GameClient;
import network.GameServer;

public class Packet03GameState extends Packet{

	private List<Actor> update;
	private int packetNum;
	private Serialiser serialise = new Serialiser();
	private byte[]data;

	@SuppressWarnings("unchecked")
	public Packet03GameState(byte[] data) {
		super(03);
		packetNum = 03;
		this.data = data;
		try {
			update = (List) serialise.deserialize(data);
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
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
		return data;

	}

	public List<Actor> getGameUpdate(){
		return update;
	}



}
