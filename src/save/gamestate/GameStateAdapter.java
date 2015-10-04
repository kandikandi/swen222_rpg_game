package save.gamestate;

import javax.xml.bind.annotation.adapters.XmlAdapter;

import model.*;

/**
 * Created 30/9/2015
 * 
 * @author Bonnie
 *
 */
public class GameStateAdapter extends XmlAdapter<AdaptedGameState, GameState>{

	@Override
	public AdaptedGameState marshal(GameState gamestate) throws Exception {
		AdaptedGameState adaptedgamestate = new AdaptedGameState();
		adaptedgamestate.setWorldTiles(gamestate.getWorld());
		adaptedgamestate.setPlayer(gamestate.getPlayer());
		adaptedgamestate.setActors(gamestate.getAllActors());
		adaptedgamestate.setGameController(gamestate.getGameController());
		return adaptedgamestate;
	}

	@Override
	public GameState unmarshal(AdaptedGameState adapedgamestate) throws Exception {
		GameState gamestate = new GameState(adapedgamestate.getGameController());
		gamestate.setWorldTiles(adapedgamestate.getWorldTiles());
		gamestate.setPlayer(adapedgamestate.getPlayer());
		gamestate.setActors(adapedgamestate.getActors());
		return gamestate;
	}

}
