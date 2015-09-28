package control;

import model.Actor;
import model.GameState;
import model.Player;
import model.Tile;
import system.GameSystem;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

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

	public GameController(GameKeyListener gameKeyListener) {
		this.gameKeyListener = gameKeyListener;
		this.gameState = new GameState(this);
		systemList = new ArrayList<>();


	}

	// Call tick on all actors to update animation state or location
	// them execute systems to draw and send to network maybe
	public void executeAllSystems() {
		gameState.getAllActors().forEach(model.Actor::tick);
		systemList.forEach(GameSystem::performSystem);
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
