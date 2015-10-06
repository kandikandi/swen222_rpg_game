package model;


import java.awt.*;
import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import save.actor.ActorAdapter;
import view.ID;

/**
 * Created by cuan on 9/13/15.
 */
@XmlRootElement(name = "actor") //TODO:Bonnie added this here!
@XmlJavaTypeAdapter(ActorAdapter.class)
public abstract class Actor implements Serializable {
    protected String imageName;
    protected Position position;
    protected boolean collidable;
    protected boolean drawable;
    protected ID id;
    protected int boundingBoxSize;
    protected char asciiCode;
    protected String actorDescription;

    public Actor(ID id, Position position, char ascii, boolean collidable, boolean drawable, int boundingBoxSize) {
        this.id = id;
        this.position = position;
        this.asciiCode = ascii;
        this.collidable = collidable;
        this.drawable = drawable;
        this.boundingBoxSize = boundingBoxSize;

    }

    abstract public void tick();

    /**
     * Getter method for GameObject's unique ID.
     *
     * @return GameObject's unique ID.
     */
    @XmlElement(name = "id") //TODO:Bonnie added this here!
    public ID getID() {
        return id;
    }

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
     * Getter method for this GameObject's position.
     *
     * @return GameObject's Image.
     */
    public String getDescription() {
        if(this.actorDescription==null){ actorDescription = " ";}
    	return actorDescription;
    }

    /**
     * Setter method for this GameObject's position.
     *
     * @return GameObject's Image.
     */
    public void setDescription(String s) {
        this.actorDescription = s;
    }

    /**
     * Returns bounding box - currently treats everything as a square of size boundingBoxSize.
     *
     * @return
     */
    public Rectangle getBoundingBox() {
        return new Rectangle(position.getxPos(), position.getyPos(), boundingBoxSize, boundingBoxSize);
    }

    //==============================================================================
    // Bonnie added this!
//    @XmlElement(name = "boundingBoxSize") //TODO:Bonnie added this here!
//    public int getBoundingBoxSize(){
//    	return boundingBoxSize;
//    }
//
//    public void setImageName(String imageName) {
//		this.imageName = imageName;
//	}
//
//	public void setId(ID id) {
//		this.id = id;
//	}
//
//	public void setBoundingBoxSize(int boundingBoxSize) {
//		this.boundingBoxSize = boundingBoxSize;
//	}
//
//	public void setAsciiCode(char asciiCode) {
//		this.asciiCode = asciiCode;
//	}

	//===========================================================================

	/**
     * Getter for collidable field.
     *
     * @return whether or not collidable
     */
    public boolean isCollidable() {
        return collidable;
    }

    public void setCollidable(boolean collidable) {
        this.collidable = collidable;
    }

    /**
     * Method to dictate how the actor will act will colliding with other actors
     * Since this method is not abstract and has not logic, if its not over-ridden
     * by a concrete subclass nothing will happen
     *
     * @param actor
     */
    protected void collide(Actor actor) {
    }

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
        System.out.println(this.getClass() + " " + getPosition().toString() + "\ncollidable: " + isCollidable() + " drawable: " + isDrawable() + "\n");
    }

    @XmlElement(name = "asciicode") //TODO:Bonnie added this here!
	public char getAsciiCode() {

		return asciiCode;
	}



}

