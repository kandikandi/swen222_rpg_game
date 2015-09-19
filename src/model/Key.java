package model;

import java.awt.Image;

public class Key extends Collectable {

	/**
	 * A Key object can be picked up by a Player and used to
	 * open a Door.
	 *
	 * @param id
	 * @param position
	 * @param image
	 * @param collidable
	 * @param drawable
	 * @param boundingBoxSize
	 */
	public Key(ID id, Position position, Image image, boolean collidable, boolean drawable, int boundingBoxSize) {
		super(id, position, image, collidable, drawable, boundingBoxSize);

	}

	/**
	 * When a Player is near a Door and has a key, this method sets the key to not visible
	 * or collidable.
	 */
	public void useInDoor(){
		drawable = false;
		collidable = false;
	}
}
