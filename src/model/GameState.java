package model;

import java.awt.Rectangle;
import java.util.ArrayList;

/**
 * This class will be a central
 *
 * @author dalyandr
 *
 */
public class GameState {

	private static ArrayList<GameObject> objects; 		// list of all GameObjects in Game.
	private static ArrayList<ActorStrategy> actors;		// list of all actors (players and enemies) in the game

	public GameState() {
		this.objects = new ArrayList<GameObject>();
		this.actors = new ArrayList<ActorStrategy>();
	}

	/*
	 * This method gets called by GameObject objects checking to see if their proposed
	 * new location would result in a collision and returns (the first) object
	 * that the GameObject would collide with.
	 */
	public static GameObject checkCollision(Rectangle newBoundingBox) {

		for (GameObject gameObject : objects) {
				if (gameObject.getBoundingBox().contains(newBoundingBox)) {
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
	public static ArrayList<ActorStrategy> getAllActors(){
		return actors;
	}


}
