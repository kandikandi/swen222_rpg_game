package control;

import model.Actor;
import model.GameState;
import model.Player;
import model.Tile;
import network.GameClient;
import network.GameServer;
import system.GameSystem;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import packet.Packet00Login;

/**
 * This class is designed to control the game loop This is where maybe we can
 * keep the collections of game objects
 *
 *
 */
public class GameController {

	private GameState gameState;
	private final List<GameSystem> systemList;
	private final GameKeyListener gameKeyListener;
	private static GameClient socketClient;
	private GameServer socketServer;

	public GameController(GameKeyListener gameKeyListener) {
		this.gameKeyListener = gameKeyListener;
		this.gameState = new GameState(this);
		systemList = new ArrayList<>();

		if(JOptionPane.showConfirmDialog(null, "Run server?")==0){
			socketServer = new GameServer(gameState);
			socketServer.start();
		}

		// always start a client, then get a username and tell the server whats happened
		socketClient = new GameClient("localhost", gameState);
		socketClient.start();
		String username = "00"+JOptionPane.showInputDialog(null,"enter username");
		Packet00Login loginPacket = new Packet00Login(username.getBytes());
		loginPacket.writeData(socketClient);
	}



	// Call tick on all actors to update animation state or location
	// them execute systems to draw and send to network maybe
	public void executeAllSystems() {
		gameState.getAllActors().forEach(model.Actor::tick);
		systemList.forEach(GameSystem::performSystem);
		socketClient.sendData("ping".getBytes());
	}


	/**
	 * Adds a system to list of systems to update each tick.
	 *
	 * @param system
	 */
	public void addSystem(GameSystem system) {
		systemList.add(system);
	}


	public Tile[][] getWorld() {
		return gameState.getWorld();
	}

	public List<Actor> getAllActors() {
		return gameState.getAllActors();
	}

	public GameState getGameState() {
		return gameState;
	}

	public Player getPlayer() {
		return gameState.getPlayer();
	}

	public Actor checkCollision(Rectangle boundingBox) {
		return gameState.checkCollision(boundingBox);
	}

	public GameKeyListener getKeyListener() {
		return gameKeyListener;
	}
}
