package save.tile;

import javax.xml.bind.annotation.adapters.XmlAdapter;

import model.Tile;

/**
 * Created on 04/10/2015
 *
 * @author Bonnie Liao
 *
 */
public class TileAdapter extends XmlAdapter<AdaptedTile, Tile>{

	@Override
	public Tile unmarshal(AdaptedTile adaptedTile) throws Exception {
		return new Tile(adaptedTile.getImage(), adaptedTile.getPosition());
	}

	@Override
	public AdaptedTile marshal(Tile tile) throws Exception {
		AdaptedTile adaptedTile = new AdaptedTile();
		adaptedTile.setImage(tile.getImage());
		adaptedTile.setPosition(tile.getPosition());
		return adaptedTile;
	}

}
