package model;

import java.awt.Image;

/**
 * A Collectable object is something that a player can pickup and
 * can be placed in a container, or a plyer's inventory.
 *
 * @author dalyandr
 *
 */
public class Collectable extends GameObject {
	boolean inContainer;

	public Collectable(ID id, Position position, Image image,
			boolean collidable, boolean drawable, int boundingBoxSize) {
		super(id, position, image, collidable, drawable, boundingBoxSize);
	}

	/**
	 * Alternative constructor used if GameObject is already in container
	 * at start of game. just has extra parameter container.
	 *
	 * @param id
	 * @param position
	 * @param image
	 * @param collidable
	 * @param drawable
	 * @param container
	 */
	public Collectable(ID id, Position position, Image image,
			boolean collidable, boolean drawable, Container container, int boundingBoxSize) {
		super(id, position, image, collidable, drawable, boundingBoxSize);
	}

	/**
	 * This method is used to put this Collectable into a Container,
	 * which the container will do if it has space.
	 *
	 * @param container
	 */
	public void putInContainer(Container container){
		if(inContainer) {return;}
		drawable = false;
		collidable = false;
		inContainer = true;
		System.out.println("Collectable Picked up");
		this.putInContainer(container);
	}

	/**
	 * When a collectable is dropped, it is removed from the
	 * container and it's position updated.
	 *
	 */
	public void drop(){
		inContainer = false;
		drawable = true;
		collidable = true;
	}


	public void setPosition(Position position) {
		this.position = position;
	}

}
