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

/**
 * This class will be a central
 *
 * @author dalyandr
 *
 */
public class GameState {
	private Tile[][] worldTiles;
	private final Player player;
	private static ArrayList<GameObject> objects; // list of all GameObjects in Game.
	private static ArrayList<Actor> actors;	// list of all actors (players and enemies) in the game
	private final AbstractFactory factory;


	public GameState(GameKeyListener keyListener) {

		this.objects = new ArrayList<>();
		this.actors = new ArrayList<>();
		worldTiles = new Tile[Main.NUM_TILE_ROW][Main.NUM_TILE_COL];
		if(Main.TEST_MODE){
			factory = new TestModeFactory();
		}
		else{
			factory = new ServerModeFactory();
		}

		worldTiles = factory.createWorldTiles();
		player = factory.createPlayerActor(keyListener);
		actors.add(player);

	}

	/*
	 * This method gets called by GameObject objects checking to see if their proposed
	 * new location would result in a collision and returns (the first) object
	 * that the GameObject would collide with.
	 */
	public static GameObject checkCollision(Rectangle newBoundingBox) {

		for (GameObject gameObject : objects) {
			newBoundingBox.toString();	
//			System.out.println("<<<><><><><<<<<<<< "+gameObject.toString());
				if (gameObject.getBoundingBox().contains(newBoundingBox)) {
					System.out.println("Collision: ");
					gameObject.printState();
					return gameObject;
				}
			}
		return null;

	}

	/**
	 * Returns list of all GameObjects.
	 *
	 * @return
	 */
	public static ArrayList<GameObject> getAllGameObjects(){
		return objects;
	}


	/**
	 * Returns list of all Actors.
	 *
	 * @return
	 */
	public static ArrayList<Actor> getAllActors(){
		return actors;
	}


	public Tile[][] getWorld() {
		return worldTiles;
	}



	//////////// Debuggin printout
	public void printGameObjectState(){
		for(GameObject gameObject : objects){
			gameObject.printState();
		}
	}

	//////////////// TEMPORARY FOR TESTS ONLY/////////////////////
	public void addGameObject(GameObject ... gameObjects){
		for(GameObject gameObject : gameObjects){
			objects.add(gameObject);
		}

	}
	//////////////////////////////////////////////////




}
