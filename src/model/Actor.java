package model;

import java.awt.*;

/**
 * Created by cuan on 9/13/15.
 */
public abstract class Actor extends GameObject implements Tickable {

    public Actor(ID id, Location location, Image image, boolean collidable, boolean drawable) {
        super(id, location, image, collidable, drawable);
    }


    abstract public void tick();

}

