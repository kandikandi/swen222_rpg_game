package model;

import java.awt.Rectangle;
import java.util.ArrayList;

/**
 * This class will hold arrays of GameObjects etc.
 *
 * @author dalyandr
 *
 */
public class GameState {

	private static ArrayList<GameObject> objects; // list of all GameObjects

	public GameState() {
		objects = new ArrayList<GameObject>();
	}

	public ArrayList<GameObject> getGameObjectList() {
		return objects;
	}

	/*
	 * This method gets called by moving Gameobjects to see if their proposed
	 * new location would result in a collision anbd returns the object it would
	 * collide with.
	 */
	public static GameObject checkCollision(Rectangle newBoundingBox) {
		for (GameObject gameObject : objects) {
			if (!(gameObject instanceof Tile)) { // ignore tiles for collision purposes.
				if (gameObject.getBoundingBox().contains(newBoundingBox)) {
					return gameObject;
				}
			}
		}
		return null;
	}

}
