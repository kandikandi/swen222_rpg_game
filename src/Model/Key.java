package Model;

import java.awt.Image;

import View.ID;

public class Key extends Collectable {

	/**
	 * A Key object can be picked up by a Player and used to
	 * open a Door.
	 *
	 * @param id
	 * @param position
	 * @param imagePath
	 * @param collidable
	 * @param drawable
	 * @param boundingBoxSize
	 */
	public Key(ID id, Position position, char imagePath, boolean collidable, boolean drawable, int boundingBoxSize) {
		super(id, position, imagePath, collidable, drawable, boundingBoxSize);

	}

	/**
	 * When a Player is near a Door and has a key, this method sets the key to not visible
	 * or collidable.
	 */
	public void useInDoor(){
		this.setDrawable(false);
		this.setCollidable(false);
	}
}
