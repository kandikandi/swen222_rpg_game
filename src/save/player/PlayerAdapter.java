package save.player;

import javax.xml.bind.annotation.adapters.XmlAdapter;

import model.Player;

/**
 * Created on 04/10/2015
 *
 * @author Bonnie Liao
 *
 */
public class PlayerAdapter extends XmlAdapter<AdaptedPlayer, Player> {

	@Override
	public Player unmarshal(AdaptedPlayer adaptedPlayer) throws Exception {
		return new Player(adaptedPlayer.getId(), adaptedPlayer.getPosition(),
				adaptedPlayer.getImage(), adaptedPlayer.isCollidable(),
				adaptedPlayer.isDrawable(), adaptedPlayer.getBoundingBoxSize(),
				adaptedPlayer.getGameController());
	}

	@Override
	public AdaptedPlayer marshal(Player player) throws Exception {
		AdaptedPlayer adaptedPlayer = new AdaptedPlayer();
		adaptedPlayer.setId(player.getID());
		adaptedPlayer.setPosition(player.getPosition());
		adaptedPlayer.setImage(player.getImage());
		adaptedPlayer.setCollidable(player.isCollidable());
		adaptedPlayer.setDrawable(player.isDrawable());
		adaptedPlayer.setBoundingBoxSize(player.getBoundingBox().height);
		adaptedPlayer.setGameController(player.getController());
		return adaptedPlayer;
	}

}
