package model;

import control.GameController;
import control.Main;
import factory.AbstractFactory;
import factory.ServerModeFactory;
import factory.TestModeFactory;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import packet.Serialiser;

/**
 * This class will be a central
 *
 * @author dalyandr
 *
 */
@XmlRootElement(namespace = "gamestate") //TODO: Bonnie added this line!
@XmlAccessorType(XmlAccessType.FIELD) //TODO: Bonnie added this line!
public class GameState implements Serializable{
	@XmlElementWrapper(name = "tilesList") //TODO: Bonnie added this line!
	@XmlElement(name = "tile") //TODO: Bonnie added this line!
	private Tile[][] worldTiles;

	private  Player player1;
	private Player player2;

	@XmlElementWrapper(name = "actors") //TODO: Bonnie added this line!
	@XmlElement(name = "actor") //TODO: Bonnie added this line!
	private List<Actor> actors; // list of all GameObjects in
													// Game.
	private final AbstractFactory factory;

	@XmlTransient //TODO: Bonnie added this line!
	private final GameController gameController;


	public GameState(GameController gameController) {
		this.gameController = gameController;
		this.actors = new ArrayList<>();
		worldTiles = new Tile[Main.NUM_TILE_ROW][Main.NUM_TILE_COL];
		if (Main.TEST_MODE) {
			factory = new TestModeFactory(gameController);
			worldTiles = factory.createWorldTiles();
		//	player = factory.createPlayerActor(gameController);
			if(gameController.isServer()){
			actors = factory.createActorList();}
			//actors.add(player);
			//player.setInventory(factory.createInventory(true, 10, 10));
		} else {
			factory = new ServerModeFactory(gameController);
		}

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
	public  Player getPlayer1() {
		return player1;
	}

	/**
	 *
	 * @return
	 */
	public  Player getPlayer2() {
		return player2;
	}

	public Tile[][] getWorld() {
		return worldTiles;
	}

	//Getters and setters

	public Actor getActor(int i){
		return actors.get(i);
	}


	public void setActors(List<Actor> actors) {
		this.actors = actors;
	}


	public AbstractFactory getFactory() {
		return factory;
	}

	// ////////// Debuggin printout
	public void printGameObjectState() {
		for(Actor actor: actors){
			actor.printState();
		}
	}

	// ////////////// TEMPORARY FOR TESTS ONLY/////////////////////
	public void addActor(Actor... actorList) {
		for (Actor actor : actorList) {
			actors.add(actor);
		}

	}

	public byte[] sendUpdate(){
		Serialiser serialise = new Serialiser();
		try {
			return serialise.serialize(actors);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	// ////////////////////////////////////////////////


	public Player createPlayer() {
		Player player = factory.createPlayerActor(gameController);
		actors.add(player);
	//	player.setInventory(factory.createInventory(true, 10, 10));
		return player;
	}

}
