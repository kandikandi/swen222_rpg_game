package Model;

import View.ID;

public class Wall extends Actor {
	final boolean collidable = true;

	public Wall(ID id, Position position, char ascii, boolean collidable,
			boolean drawable, int boundingBoxSize) {
		super(id, position, ascii, true, drawable, boundingBoxSize);

	}

	@Override
	public void tick() {
	}


	@Override
	public void setCollidable(boolean collidable) {;}

}
