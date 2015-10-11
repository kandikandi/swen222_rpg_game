package model;

import view.ActorAssets;
import view.TestWorlds;
import java.awt.Rectangle;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * The GameState class creates and manages the factory that creates the game
 * elements, such as Players, Tiles,and other Actors. It then also maintains and
 * manages the objects. It is a central source of game state information for
 * other parts/packages of the game.
 *
 */
@XmlRootElement(namespace = "gamestate")
// TODO: Bonnie added this here!
public class GameState {

	/**
	 * This array of Tiles holds all the game Tiles.
	 *
	 */
	@XmlTransient
	// TODO:Bonnie added this here!
	private Tile[][] worldTiles;

	/**
	 * This array holds all the active Actor object implementations in the game.
	 *
	 */
	// @XmlElementWrapper(name = "actorsList") //TODO:Bonnie added this here!
	// @XmlElement(name = "actor") //TODO:Bonnie added this here!
	private List<Actor> actors;

	/**
	 * This factory creates the necessary game objects from the TIle and actor
	 * maps.
	 *
	 */
	@XmlTransient
	// TODO: Bonnie added this here!
	private final TestModeFactory factory;

	/**
	 * The constructor creates the factory which constructs the Tiles and Actors
	 * for the server (and just the Tiles for the client).
	 *
	 * @param isServer
	 */
	public GameState(boolean isServer) {
		factory = new TestModeFactory();
		worldTiles = factory.createWorldTiles();
		if (isServer) {
			actors = factory.createActorList();
		}
	}

	/**
	 * Getter for the list of active Actor objects in the game.
	 *
	 * @return
	 */
	public List<Actor> getActors() {
		return actors;
	}

	/**
	 * Getter for 2-D array of Tiles.
	 *
	 * @return
	 */
	public Tile[][] getWorld() {
		return worldTiles;
	}

	/**
	 * Setter for game Actor objects.
	 *
	 * @param actors
	 */
	public void setActors(List<Actor> actors) {
		this.actors = actors;
	}

	/**
	 * This method adds a player to the game and provides a Player object that
	 * represents each Player.
	 *
	 * @param clientNum
	 */
	public void createPlayer(int clientNum) {
		Player player = factory.createPlayerActor(clientNum);
		actors.add(player);
	}

	/**
	 * The Collision class controls the collision logic. This helps that process
	 * by providing the first colliding object that is in the Player object's
	 * proposed location.
	 *
	 * @param position
	 * @return
	 */
	public Actor playerCollisionCheck(Position position) {
		BoundingBox boundingBox = position.getBoundingBox();
		for (Actor actor : actors) {
			if (actor.isCollidable() && !(actor instanceof Player)) {
				if (actor.getBoundingBox().intersects(boundingBox)) {
					return actor;
				}
			}
		}
		return null;
	}

	/**
	 * The Collision class controls the collision logic. This helps that process
	 * by providing the first colliding object that is in the Enemy object's
	 * proposed location.
	 *
	 * @param position
	 * @return
	 */
	public Actor enemyCollisionCheck(Position position) {
		Rectangle boundingBox = new Rectangle(position.getxPos(),
				position.getyPos(), ActorAssets.ENEMY.getWidth(), ActorAssets.ENEMY.getHeight());
		for (Actor actor : actors) {
			if (actor.isCollidable() && !(actor instanceof Enemy)) {
				if (actor.getBoundingBox().intersects(boundingBox)) {
					return actor;
				}
			}
		}
		return null;
	}

	/**
	 * This method returns a one of the two Player objects
	 * depending the client number entered.
	 *
	 * @param playerNum
	 * @return Player
	 */
	public Player findPlayer(int playerNum) {
		if(actors != null){
		for (Actor actor : actors) {
			if (actor instanceof Player) {
				Player player = (Player) actor;
				if (player.getClientNum() == playerNum) {
					return player;
				}
			}
		}
		}
		return null;
	}

	// ============== DEBUGGING =================
	public void printGameObjectState() {
		for (Actor actor : actors) {
			actor.printState();
		}
	}

}
