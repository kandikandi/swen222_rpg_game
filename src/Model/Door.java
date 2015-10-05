package Model;
import View.ID;
public class Door extends Actor {

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
	public Door(ID id, Position position, char imagePath, boolean collidable,
			boolean drawable, int boundingBoxSize) {
		super(id, position, imagePath, collidable, drawable, boundingBoxSize);
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

	public boolean getIsOpen(){
		return open;
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub

	}



}