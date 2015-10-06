package model;

import view.TestWorlds;

import java.awt.Rectangle;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlRootElement(namespace = "gamestate") //TODO: Bonnie added this here!
public class GameState {

	@XmlTransient //TODO:Bonnie added this here!
	private Tile[][] worldTiles;


	// @XmlElementWrapper(name = "actorsList") //TODO:Bonnie added this here!
	// @XmlElement(name = "actor") //TODO:Bonnie added this here!
	private List<Actor> actors;

	@XmlTransient //TODO: Bonnie added this here!
	private final AbstractFactory factory;

	public GameState(boolean isServer){

		factory = new TestModeFactory();
		worldTiles = factory.createWorldTiles();

		if(isServer){
			actors = factory.createActorList();
		}
	}


	public List<Actor> getActors(){
		return actors;
	}

	public Tile[][] getWorld() {
		return worldTiles;
	}

	public void setActors(List<Actor> actors) {
		this.actors = actors;
	}

	public void createPlayer(int clientNum) {
		Player player = factory.createPlayerActor(clientNum);
		actors.add(player);
	}

	public Actor playerCollisionCheck(Position position) {
		Rectangle boundingBox = new Rectangle(position.getxPos(),position.getyPos(),40,40);
		for (Actor actor : actors) {
			if (actor.isCollidable() && !(actor instanceof Player)) {
				if (actor.getBoundingBox().intersects(boundingBox)) {
					return actor;
				}
			}
		}
		return null;
	}

	public Actor enemyCollisionCheck(Position position) {
		Rectangle boundingBox = new Rectangle(position.getxPos(),position.getyPos(),40,40);
		for (Actor actor : actors) {
			if (actor.isCollidable() && !(actor instanceof Enemy)) {
				if (actor.getBoundingBox().intersects(boundingBox)) {
					return actor;
				}
			}
		}
		return null;
	}


	// TODO fix this when you're not crazy
	public Player findPlayer(int play) {
		for (int i = 0; i < actors.size(); i++) {
			if (actors.get(i) instanceof Player) {
				if (((Player) actors.get(i)).getClientNum() == play) {
					return (Player) actors.get(i);
				}
			}
		}
		return null;
	}

	

	// ============== DEBUGGING =================
	public void printGameObjectState() {
		for(Actor actor: actors){
			actor.printState();
		}
	}

}
