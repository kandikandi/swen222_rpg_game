package save.position;

import javax.xml.bind.annotation.adapters.XmlAdapter;

import model.Position;

/**
 * Created on 04/10/2015
 *
 * @author Bonnie Liao
 *
 */
public class PositionAdapter extends XmlAdapter<AdaptedPosition, Position> {

	@Override
	public Position unmarshal(AdaptedPosition adaptedPosition) throws Exception {
		return new Position(adaptedPosition.getxPos(),
				adaptedPosition.getyPos());
	}

	@Override
	public AdaptedPosition marshal(Position position) throws Exception {
		AdaptedPosition adaptedPosition = new AdaptedPosition();
		adaptedPosition.setxPos(position.getxPos());
		adaptedPosition.setyPos(position.getyPos());
		return adaptedPosition;
	}

}
