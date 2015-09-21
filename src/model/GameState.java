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
	private static List<Actor> actors; // list of all GameObjects in
													// Game.
	private final AbstractFactory factory;

	public GameState(GameKeyListener keyListener) {

		this.actors = new ArrayList<>();
		worldTiles = new Tile[Main.NUM_TILE_ROW][Main.NUM_TILE_COL];
		if (Main.TEST_MODE) {
			factory = new TestModeFactory();
			worldTiles = factory.createWorldTiles();
			player = factory.createPlayerActor(keyListener);
			actors = factory.createActorList();
			actors.add(player);
			player.setInventory(factory.createInventory(true, 10, 10));


		} else {
			factory = new ServerModeFactory();
		}

	}

	/*
	 * This method gets called by GameObject objects checking to see if their
	 * proposed new location would result in a collision and returns (the first)
	 * object that the GameObject would collide with.
	 */
	public static Actor checkCollision(Rectangle newBoundingBox) {
//			System.out.println(">>>>>>>> "+newBoundingBox.toString());
		for (Actor actor : actors) {
//			 System.out.println("Checking collision "+gameObject.getClass());
//
			if (actor.isCollidable()){
//				 System.out.println(gameObject.getPosition().toString());
				if (actor.getBoundingBox().intersects(newBoundingBox)
					// && !(gameObject instanceof Player)
					&& !(actor instanceof Inventory)) {
//				System.out.println("***Collision: "
//						+ gameObject.getBoundingBox().toString() + " "
//						+ newBoundingBox.getBounds().toString());
//				System.out.println(gameObject.getClass() + "***");
				// gameObject.printState();
				return actor;
			}
					}
		}
		return null;

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
		for (Actor actor : actors) {
			actor.printState();
		}
	}

	// ////////////// TEMPORARY FOR TESTS ONLY/////////////////////
	public void addActor(Actor... actorList) {
		for (Actor actor : actorList) {
			actors.add(actor);
		}

	}
	// ////////////////////////////////////////////////

}
