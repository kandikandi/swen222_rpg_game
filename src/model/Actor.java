package model;

import java.awt.*;
import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import save.actor.ActorAdapter;

/**
 * Created by cuan on 9/13/15.
 */
@XmlRootElement(name = "actor")
// TODO:Bonnie added this here!
 @XmlJavaTypeAdapter(ActorAdapter.class)
//@XmlSeeAlso({ Coin.class, CoinBag.class, Collectable.class, Door.class,
//		Enemy.class, Key.class, Wall.class })
public class Actor implements Serializable {
	protected String imageName;
	protected Position position;
	protected boolean collidable;
	protected boolean drawable;
	// protected ID id;
	protected char asciiCode;
	protected String actorDescription;

	public Actor(Position position, char ascii, boolean collidable,
			boolean drawable) {
		// this.id = id;
		this.position = position;
		this.asciiCode = ascii;
		this.collidable = collidable;
		this.drawable = drawable;

	}

	public void tick() {
	}

	/**
	 * Getter method for GameObject's unique ID.
	 *
	 * @return GameObject's unique ID.
	 */
	/*
	 * @XmlElement(name = "id") //TODO:Bonnie added this here! public ID getID()
	 * { return id; }
	 */

	/**
	 * Getter method for GameObject's Image.
	 *
	 * @return GameObject's Image.
	 */
	public char getImageName() {
		return asciiCode;
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
	 * Getter method for this GameObject's description.
	 *
	 * @return GameObject's description String.
	 */
	public String getDescription() {
		if (this.actorDescription == null) {
			actorDescription = "This is a " + this.getClass();
		}
		return actorDescription;
	}

	/**
	 * Setter method for this GameObject's position.
	 *
	 * @return GameObject's Image.
	 */
	public void setDescription(String description) {
		this.actorDescription = description;
	}

	/**
	 * Returns bounding box - currently treats everything as a square of size
	 * boundingBoxSize.
	 *
	 * @return
	 */
	public BoundingBox getBoundingBox() {
		return position.getBoundingBox();
		// return new Rectangle(position.getxPos(), position.getyPos(),
		// boundingBoxSize, boundingBoxSize);
	}

	// ==============================================================================
	// Bonnie added this!
	// @XmlElement(name = "boundingBoxSize") //TODO:Bonnie added this here!
	// public int getBoundingBoxSize(){
	// return boundingBoxSize;
	// }
	//
	// public void setImageName(String imageName) {
	// this.imageName = imageName;
	// }
	//
	// public void setId(ID id) {
	// this.id = id;
	// }
	//
	// public void setBoundingBoxSize(int boundingBoxSize) {
	// this.boundingBoxSize = boundingBoxSize;
	// }
	//
	// public void setAsciiCode(char asciiCode) {
	// this.asciiCode = asciiCode;
	// }

	// ===========================================================================

	/**
	 * Getter for collidable field.
	 *
	 * @return whether or not collidable
	 */
	public boolean isCollidable() {
		return collidable;
	}

	/**
	 * Setter for collidable field.
	 */
	public void setCollidable(boolean collidable) {
		this.collidable = collidable;
	}

	/**
	 * Method to dictate how the actor will act will colliding with other actors
	 * Since this method is not abstract and has not logic, if its not
	 * over-ridden by a concrete subclass nothing will happen
	 *
	 * @param actor
	 */
	protected void collide(Actor actor) {
	}

	/**
	 * Setter for drawable field.
	 */
	public void setDrawable(boolean drawable) {
		this.drawable = drawable;
	}

	/**
	 * Getter for drawable field.
	 *
	 * @return whether or not drawable.
	 */
	public boolean isDrawable() {
		return drawable;
	}

	/**
	 * Mini-debug helper printout
	 */
	public void printState() {
		System.out.println(this.getClass() + " " + getPosition().toString()
				+ "\ncollidable: " + isCollidable() + " drawable: "
				+ isDrawable() + "\n");
	}

	/**
	 * Getter for object ascii code.
	 *
	 * @return
	 */
	@XmlElement(name = "asciicode")
	// TODO:Bonnie added this here!
	public char getAsciiCode() {
		return asciiCode;
	}

}
