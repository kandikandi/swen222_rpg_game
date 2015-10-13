package model;


public class Key extends Collectable {

	private boolean special;

	/**
	 * A Key object can be picked up by a Player and used to
	 * open a Door.
	 *  @param position
	 * @param imagePath
	 * @param collidable
	 * @param drawable
	 */
	public Key(Position position, char imagePath, boolean collidable, boolean drawable) {
		super(position, imagePath, collidable, drawable);

	}

	/**
	 * When a Player collides with a Door and has a key, this method sets the key to not visible
	 * or collidable. The door will be set to open and become non-collidable.
	 */
	public void useInDoor(){
		this.setDrawable(false);
		this.setCollidable(false);
	}

	/**
	 * Setter for special field.
	 *
	 * @param special
	 */
	public void setSpecial(boolean special){
		this.special = special;
	}

	/**
	 * Getter for special field.
	 *
	 * @param special
	 */
	public boolean isSpecial(){
		return special;
	}

}
