package model;

import java.awt.Image;

public class Door extends GameObject {

	boolean open;

	/**
	 * This class defines Door objects, which block the path of
	 * Player objects unless the Player has a key.
	 *
	 * @param id
	 * @param position
	 * @param image
	 * @param collidable
	 * @param drawable
	 * @param boundingBoxSize
	 */
	public Door(ID id, Position position, Image image, boolean collidable,
			boolean drawable, int boundingBoxSize) {
		super(id, position, image, collidable, drawable, boundingBoxSize);

	}

	/**
	 * This method sets a Door object to open, allowing a
	 * Player object to pass over it.
	 *
	 */
	public void open() {
		open = true;
		collidable = false;
	}

}
