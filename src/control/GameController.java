package control;

import model.Actor;
import system.GameSystem;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is designed to control the game loop This is where maybe we can
 * keep the collections of game objects
 *
 *
 */
public class GameController {

	private final List<GameSystem> systemList;
	private List<Actor> actorList;

	public GameController() {
		systemList = new ArrayList<>();
		actorList = new ArrayList<>();

	}

	// Call tick on all actors to update animation state or location
	// them execute systems to draw and send to network maybe
	public void executeAllSystems() {
		actorList.forEach(model.Actor::tick);
		systemList.forEach(GameSystem::performSystem);
	}

	/**
	* Adds actorList to list of systems to be ticked each cycle.
	*
	*/
	public void addActorList(List<Actor> actorList) {
		this.actorList = actorList;
	}

	/**
	 * Adds a system to list of systems to update each tick.
	 *
	 * @param system
	 */
	public void addSystem(GameSystem system) {
		systemList.add(system);
	}



}
