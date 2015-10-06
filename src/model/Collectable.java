package model;

import view.ID;

/**
 * A Collectable object is something that a player can pickup and
 * can be placed in a container, or a plyer's inventory.
 *
 * @author dalyandr
 *
 */
public class Collectable extends Actor {
	boolean inContainer;

	public Collectable(ID id, Position position, char imagePath,
			boolean collidable, boolean drawable, int boundingBoxSize) {
		super(id, position, imagePath, collidable, drawable, boundingBoxSize);
	}

	/**
	 * Alternative constructor used if GameObject is already in container
	 * at start of game. just has extra parameter container.
	 *
	 * @param id
	 * @param position
	 * @param imagePath
	 * @param collidable
	 * @param drawable
	 * @param container
	 */
	public Collectable(ID id, Position position, char imagePath,
			boolean collidable, boolean drawable, Container container, int boundingBoxSize) {
		super(id, position, imagePath, collidable, drawable, boundingBoxSize);
	}

	/**
	 * This method is used to put this Collectable into a Container,
	 * which the container will do if it has space.
	 *
	 * @param container
	 */
	public void putInContainer(Container container){
		if(inContainer) {return;}
		this.setDrawable(false);
		this.setCollidable(false);
		inContainer = true;
//		System.out.println("Collectable Picked up");
		this.putInContainer(container);
	}

	/**
	 * When a collectable is dropped, it is removed from the
	 * container and it's position updated.
	 *
	 */
	public void drop(){
		this.inContainer = false;
		this.setDrawable(true);
		this.setCollidable(true);
	}


	public void setPosition(Position position) {
		this.position = position;
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub

	}

}
