package model;


import java.awt.*;

/**
 * Created by cuan on 9/13/15.
 *
 * An Actor object is a gameObject that
 *
 */
public abstract class Actor extends GameObject implements Tickable {


    public Actor(ID id, Position position, Image image, boolean collidable, boolean drawable, int boundingBoxSize) {
        super(id, position, image, collidable, drawable, boundingBoxSize);
    }


    abstract public void tick();




}

