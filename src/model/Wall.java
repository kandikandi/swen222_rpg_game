package model;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 * A Wall object is a simple Actor which does not move and cannot
 * be moved through.
 *
 *
 */
public class Wall extends Actor {
	final boolean collidable = true;

	public Wall(Position position, char ascii, boolean collidable,
				boolean drawable) {
		super(position, ascii, true, drawable);

	}

	@Override
	public void tick() {
	}


	@Override
	public void setCollidable(boolean collidable) {}

}
