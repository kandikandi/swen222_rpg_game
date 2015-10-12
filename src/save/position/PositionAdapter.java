package save.position;

import javax.xml.bind.annotation.adapters.XmlAdapter;

import model.Position;

public class PositionAdapter extends XmlAdapter<AdaptedPosition, Position> {

	@Override
	public AdaptedPosition marshal(Position p) throws Exception {
		AdaptedPosition ap = new AdaptedPosition();
		if(p != null){
			ap.setBoundingBox(p.getBoundingBox());
			ap.setxPos(p.getxPos());
			ap.setyPos(p.getyPos());
		}
		return ap;
	}

	@Override
	public Position unmarshal(AdaptedPosition ap) throws Exception {
		return new Position(ap.getxPos(), ap.getyPos(), ap.getBoundingBox());
	}

}
