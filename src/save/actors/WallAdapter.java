package save.actors;

import javax.xml.bind.annotation.adapters.XmlAdapter;

import model.Wall;

public class WallAdapter extends XmlAdapter<AdaptedWall, Wall>{

	@Override
	public Wall unmarshal(AdaptedWall aw) throws Exception {
		Wall w = new Wall(aw.getPosition(), aw.getAsciiCode(), true, aw.isDrawable());
		w.setDescription(aw.getActorDescription());
		return w;
	}

	@Override
	public AdaptedWall marshal(Wall w) throws Exception {
		AdaptedWall aw = new AdaptedWall();
		aw.setPosition(w.getPosition());
		aw.setDrawable(w.isDrawable());
		aw.setAsciiCode(w.getAsciiCode());
		aw.setActorDescription(w.getDescription());
		return aw;
	}

}
