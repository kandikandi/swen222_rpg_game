package model;

import control.DIR;
import system.GameException;

import java.awt.*;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by cuan on 9/13/15.
 *
 * The ground on each level is comprised of a 2-D array of Tile objects. These
 * objects are passable if an actor can walk across them unimpeded,
 *
 *
 */
public final class Tile  {
    private final Image image;
    private final Position position;

    public Tile(Image image, Position position) {
        this.position = position;
        this.image = image;
    }

    /**
     * Getter for Tile image.
     *
     * @return tile Image
     */
    public Image getImage(){
    	return image;
    }

    /**
     * Getter for Tile position.
     *
     * @return Tile position
     */
    public Position getPosition(){
    	return position;
    }
}