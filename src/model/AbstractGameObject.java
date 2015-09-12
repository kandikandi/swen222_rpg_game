package model;

import java.awt.*;

/**
 * Created by cuan on 9/13/15.
 */
public abstract class AbstractGameObject {
    protected Image image;
    protected Location location;
    protected boolean collidable;
    protected boolean drawable;
    protected final ID id;

    public AbstractGameObject(ID id, Location loc, Image image, boolean collidable, boolean drawable) {
        this.location = loc;
        this.image = image;
        this.collidable = collidable;
        this.drawable = drawable;
        this.id = id;
        
    }

    public ID getID(){
        return id;
    }

    public Image getImage(){
        return image;
    }

    public Location getLocation(){
        return location;
    }
}
