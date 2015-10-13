package model;

public class Door extends Actor {

	private boolean open;
	private boolean special;

	/**
	 * This class defines Door objects, which block the path of
	 * Player objects unless the Player has a key.
	 * @param position
	 * @param imagePath
	 * @param collidable
	 * @param drawable
	 */
	public Door(Position position, char imagePath, boolean collidable,
				boolean drawable) {
		super(position, imagePath, collidable, drawable);
	}

	/**
	 * This method sets a Door object to open, allowing a
	 * Player object to pass over it.
	 *
	 */
	public void setOpen() {
		this.setCollidable(false);
		this.setDrawable(false);
	}

	/**
	 * Getter for whether door is open.
	 *
	 * @return
	 */
	public boolean getIsOpen(){
		return open;
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

    /**
	 * Returns description of Door, as well as whether it's open.
	 *
	 */
	@Override
	public String getDescription() {
		return "This is a Door! It is "+getIsOpen()+"!!!";
	}

	@Override
	public void tick() {}



}