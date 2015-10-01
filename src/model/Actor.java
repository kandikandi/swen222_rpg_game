package model;


import java.awt.*;
import java.io.Serializable;

/**
 * Created by cuan on 9/13/15.
 */
public abstract class Actor implements Tickable, Serializable {
    protected String imageName;
    protected Position position;
    protected boolean collidable;
    protected boolean drawable;
    protected ID id;
    protected int boundingBoxSize;

    public Actor(ID id, Position position, String imageName, boolean collidable, boolean drawable, int boundingBoxSize) {
        this.id = id;
        this.position = position;
        this.imageName = imageName;
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
    public ID getID() {
        return id;
    }

    /**
     * Getter method for GameObject's Image.
     *
     * @return GameObject's Image.
     */
    public String getImageName() {
        return imageName;
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
    public Rectangle getBoundingBox() {
        return new Rectangle(position.getxPos(), position.getyPos(), boundingBoxSize, boundingBoxSize);
    }

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



}

