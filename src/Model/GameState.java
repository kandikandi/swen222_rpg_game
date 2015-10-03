package Model;

import java.util.List;

import View.TestWorlds;

public class GameState {

	private Tile[][] worldTiles;

	private List<Actor> actors;

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

	//TODO fix this when you're not crazy
	public Player findPlayer(int play){
		for(int i=0; i < actors.size(); i++){
			if(actors.get(i) instanceof Player){
				if(((Player)actors.get(i)).getClientNum()==play){
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
