package model;

import control.DIR;

import java.awt.*;

/**
 * Created by cuan on 9/13/15.
 */
public class AbstractActor extends AbstractGameObject implements Tickable {
    public AbstractActor(ID id,Location location, Image image, boolean collidable, boolean drawable) {
        super(id, location, image, collidable, drawable);
    }

    @Override
    public void tick() {
        return;
    }
}
