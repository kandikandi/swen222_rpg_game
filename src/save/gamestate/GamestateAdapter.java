package save.gamestate;

import javax.xml.bind.annotation.adapters.XmlAdapter;

import model.GameState;

public class GamestateAdapter extends XmlAdapter<AdaptedGamestate, GameState>{

	@Override
	public AdaptedGamestate marshal(GameState gs) throws Exception {
		AdaptedGamestate ags = new AdaptedGamestate();
		ags.setActors(gs.getActors());
		return ags;
	}

	@Override
	public GameState unmarshal(AdaptedGamestate ags) throws Exception {
		GameState gs = new GameState(false);
		gs.setActors(ags.getActors());
		return gs;
	}

}
