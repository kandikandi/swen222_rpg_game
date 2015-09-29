package model;

import control.DIR;
import system.GameException;

import java.awt.*;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * Created by cuan on 9/13/15.
 *
 * The ground on each level is comprised of a 2-D array of Tile objects. The Tiles
 * are all 'dumb' with the game actors exisiting separately from the ground tiles.
 *
 *
 */
@XmlRootElement(name = "tile") //TODO: Bonnie added this line!
public final class Tile  {
    private Image image;
    private Position position;

    public Tile(Image image, Position position) {
        this.position = position;
        this.image = image;
    }

    public Tile(){
    	//TODO: Bonnie added this line!
    }

    /**
     * Getter for Tile image.
     *
     * @return tile Image
     */
//    @XmlElement(name = "image") //TODO: Bonnie added this line!
//    @XmlTransient //TODO: Bonnie added this line!
    public Image getImage(){
    	return image;
    }

    /**
     * Getter for Tile position.
     *
     * @return Tile position
     */
    @XmlElement(name = "position") //TODO: Bonnie added this line!
    public Position getPosition(){
    	return position;
    }
}