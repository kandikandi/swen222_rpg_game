package control;

import model.*;
import network.GameClient;
import network.GameServer;
import system.GameException;
import system.GameSystem;

import java.awt.*;
import java.io.Serializable;
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
public class GameController implements Serializable {

	private GameState gameState;
	private final List<GameSystem> systemList;
	private final GameKeyListener gameKeyListener;
	private static GameClient socketClient;
	private GameServer socketServer;
	private boolean isServer = false;




	public GameController(GameKeyListener gameKeyListener) {
		this.gameKeyListener = gameKeyListener;
		systemList = new ArrayList<>();


		if(JOptionPane.showConfirmDialog(null, "Run server?")==0){
			isServer = true;
			socketServer = new GameServer(gameState,this);
			socketServer.start();
		}

		this.gameState = new GameState(this);
		if(isServer()){socketServer.setGame(gameState);}

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
		//socketClient.sendData("ping".getBytes());
	}


	/**
	 * Adds a system to list of systems to update each tick.
	 *
	 * @param system
	 */
	public void addSystem(GameSystem system) {
		systemList.add(system);
	}

	public void removeActor(Actor actor){
		if(!getAllActors().contains(actor)){
			try {
				throw new GameException("GameController tried to remove non-existance actor from GameState");
			} catch (GameException e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
			}
		}else{
			getAllActors().remove(actor);
		}
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

	public Player getPlayer1() {
		return gameState.getPlayer1();
	}

	public Player getPlayer2() {
		return gameState.getPlayer2();
	}

	public GameKeyListener getKeyListener() {
		return gameKeyListener;
	}


	public boolean isServer() {
		return isServer;
	}

}
