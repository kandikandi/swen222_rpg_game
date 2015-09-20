package model;

import java.awt.*;

/**
 * Created by cuan on 9/13/15.
 *
 * This class is the parent class of most of the objects in the game, including
 * the Tile and Actor objects. Each GameObject has a unique ID and position.
 * GameObjects can also be collidable and drawable.
 *
 */
public abstract class GameObject {
	protected Image image;
	protected Position position;
	protected boolean collidable;
	protected boolean drawable;
	protected final ID id;
	protected final int boundingBoxSize;

	public GameObject(ID id, Position position, Image image,
			boolean collidable, boolean drawable, int boundingBoxSize) {
		this.position = position;
		this.image = image;
		this.collidable = collidable;
		this.drawable = drawable;
		this.id = id;
		this.boundingBoxSize = boundingBoxSize; // change to BoundingBox class
	}

	/**
	 * Getter method for GameObject's unique ID.
	 *
	 * @return GameObject's unique ID.
	 */
	public ID getID() {
		return id;
	}

	/**
	 * Getter method for GameObject's Image.
	 *
	 * @return  GameObject's Image.
	 */
	public Image getImage() {
		return image;
	}

	/**
	 * Getter method for this GameObject's position.
	 *
	 * @return GameObject's Image.
	 */
	public Position getPosition() {
		return position;
	}

	/**
	 * Setter method for this GameObject's position.
	 *
	 * @return GameObject's Image.
	 */
	public void setPosition(Position position) {
		this.position = position;
	}

    /**
     * Returns bounding box - currently treats everything as a square of size boundingBoxSize.
     *
     * @return
     */
    public Rectangle getBoundingBox(){
		return new Rectangle(position.getxPos(),position.getyPos(),position.getxPos()+boundingBoxSize,position.getyPos()+boundingBoxSize);
    }

    /**
     * Getter for collidable field.
     *
     * @return whether or not collidable
     */
    public boolean isCollidable(){
    	return collidable;
    }

    /**
     * Getter for drawable field.
     *
     * @return whether or not drawable.
     */
	private boolean isDrawable() {
		return drawable;
	}


    /**
     * Mini-debug helper printout
     */
    public void printState(){
    	System.out.println(this.getClass()+" "+getPosition().toString()+"\ncollidable: "+isCollidable() + " drawable: " + isDrawable()+"\n");
    }

}