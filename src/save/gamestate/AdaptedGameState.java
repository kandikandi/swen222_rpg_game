package save.gamestate;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import control.GameController;
import model.*;

/**
 * Created 30/9/2015
 *
 * @author Bonnie
 *
 */
@XmlRootElement(name = "gamestate")
public class AdaptedGameState {

	@XmlElementWrapper(name = "tilesList")
	@XmlElement(name = "tile")
	private Tile[][] worldTiles;

	private Player player;

	@XmlElementWrapper(name = "actorsList")
	@XmlElement(name = "actor")
	private List<Actor> actors;
	private GameController gameController;

	public Tile[][] getWorldTiles() {
		return worldTiles;
	}

	public void setWorldTiles(Tile[][] worldTiles) {
		this.worldTiles = worldTiles;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public List<Actor> getActors() {
		return actors;
	}

	public void setActors(List<Actor> actors) {
		this.actors = actors;
	}

	public GameController getGameController() {
		return gameController;
	}

	public void setGameController(GameController gameController) {
		this.gameController = gameController;
	}

}
