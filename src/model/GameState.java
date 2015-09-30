package model;

import control.GameController;
import control.GameKeyListener;
import control.Main;
import factory.AbstractFactory;
import factory.ServerModeFactory;
import factory.TestModeFactory;
import save.GameStateAdapter;

import java.awt.Rectangle;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 * This class will be a central
 *
 * @author dalyandr
 *
 */
//@XmlRootElement(namespace = "gamestate") //TODO: Bonnie added this line!
//@XmlAccessorType(XmlAccessType.FIELD) //TODO: Bonnie added this line!
@XmlJavaTypeAdapter(GameStateAdapter.class) //TODO: Bonnie added this line!
public class GameState {
	@XmlElementWrapper(name = "tilesList") //TODO: Bonnie added this line!
	@XmlElement(name = "tile") //TODO: Bonnie added this line!
	private Tile[][] worldTiles;

//	@XmlTransient //TODO: Bonnie added this line!
	private Player player;

	@XmlElementWrapper(name = "actors") //TODO: Bonnie added this line!
	@XmlElement(name = "actor") //TODO: Bonnie added this line!
//	@XmlTransient //TODO: Bonnie added this line!
	private List<Actor> actors; // list of all GameObjects in Game.
	
//	@XmlTransient //TODO: Bonnie added this line!
	private final AbstractFactory factory;

//	@XmlTransient //TODO: Bonnie added this line!
	private final GameController gameController;


	public GameState(GameController gameController) {
		this.gameController = gameController;
		this.actors = new ArrayList<>();
		worldTiles = new Tile[Main.NUM_TILE_ROW][Main.NUM_TILE_COL];
		if (Main.TEST_MODE) {
			factory = new TestModeFactory(gameController);
			worldTiles = factory.createWorldTiles();
			player = factory.createPlayerActor(gameController.getKeyListener());
			actors = factory.createActorList();
			actors.add(player);
			player.setInventory(factory.createInventory(true, 10, 10));
		} else {
			factory = new ServerModeFactory(gameController);
		}

	}

	/*
	 * This method gets called by GameObject objects checking to see if their
	 * proposed new location would result in a collision and returns (the first)
	 * object that the GameObject would collide with.
	 */
	public  Actor checkCollision(Rectangle newBoundingBox) {
		for (Actor actor : actors) {
			if (actor.isCollidable()){
				if (actor.getBoundingBox().intersects(newBoundingBox)
					&& !(actor instanceof Player)
					&& !(actor instanceof Inventory)) {
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
	public  List<Actor> getAllActors() {
		return actors;
	}

	/**
	 *
	 * @return
	 */
	public  Player getPlayer() {
		return player;
	}

	public Tile[][] getWorld() {
		return worldTiles;
	}

	//===================================================//
	// TODO: Bonnie added lines here!
	public GameController getGameController() {
		return gameController;
	}

	public void setWorldTiles(Tile[][] worldTiles) {
		this.worldTiles = worldTiles;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public void setActors(List<Actor> actors) {
		this.actors = actors;
	}
	//TODO: Bonnie ends here!
	//===================================================//

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
