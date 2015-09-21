package model;

import control.GameController;
import control.GameKeyListener;
import control.Main;
import factory.AbstractFactory;
import factory.ServerModeFactory;
import factory.TestModeFactory;

import java.awt.Rectangle;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

/**
 * This class will be a central
 *
 * @author dalyandr
 *
 */
public class GameState {
	private Tile[][] worldTiles;
	private static Player player;
	private static List<GameObject> objects; // list of all GameObjects in
													// Game.
	private static List<Actor> actors; // list of all actors (players and
											// enemies) in the game
	private final AbstractFactory factory;

	public GameState(GameKeyListener keyListener) {

		this.objects = new ArrayList<>();
		this.actors = new ArrayList<>();
		worldTiles = new Tile[Main.NUM_TILE_ROW][Main.NUM_TILE_COL];
		if (Main.TEST_MODE) {
			factory = new TestModeFactory();
			worldTiles = factory.createWorldTiles();
			player = factory.createPlayerActor(keyListener);
			actors.add(player);
			objects.add(player);
			player.setInventory(factory.createInventory(true, 10, 10));
			objects.add(factory.createKey(150, 150));

		} else {
			factory = new ServerModeFactory();
		}

	}

	/*
	 * This method gets called by GameObject objects checking to see if their
	 * proposed new location would result in a collision and returns (the first)
	 * object that the GameObject would collide with.
	 */
	public static GameObject checkCollision(Rectangle newBoundingBox) {
//			System.out.println(">>>>>>>> "+newBoundingBox.toString());
		for (GameObject gameObject : objects) {
//			 System.out.println("Checking collision "+gameObject.getClass());
//
			if (gameObject.isCollidable()){
//				 System.out.println(gameObject.getPosition().toString());
				if (gameObject.getBoundingBox().intersects(newBoundingBox)
					// && !(gameObject instanceof Player)
					&& !(gameObject instanceof Inventory)) {
//				System.out.println("***Collision: "
//						+ gameObject.getBoundingBox().toString() + " "
//						+ newBoundingBox.getBounds().toString());
//				System.out.println(gameObject.getClass() + "***");
				// gameObject.printState();
				return gameObject;
			}
					}
		}
		return null;

	}

	/**
	 * Returns list of all GameObjects.
	 *
	 * @return
	 */
	public static List<GameObject> getAllGameObjects() {
		return objects;
	}

	/**
	 * Returns list of all Actors.
	 *
	 * @return
	 */
	public static List<Actor> getAllActors() {
		return actors;
	}

	/**
	 *
	 * @return
	 */
	public static Player getPlayer() {
		return player;
	}

	public Tile[][] getWorld() {
		return worldTiles;
	}












	// ////////// Debuggin printout
	public void printGameObjectState() {
		for (GameObject gameObject : objects) {
			gameObject.printState();
		}
	}

	// ////////////// TEMPORARY FOR TESTS ONLY/////////////////////
	public void addGameObject(GameObject... gameObjects) {
		for (GameObject gameObject : gameObjects) {
			objects.add(gameObject);
		}

	}
	// ////////////////////////////////////////////////

}
