package model;

import java.awt.*;
import java.io.Serializable;

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
public final class Tile  implements Serializable{
    //private final Image image;
    private final char asciiCode;
    private final Position position;

    public Tile(/*Image image*/ char asciiCode, Position position) {
        this.position = position;
        this.asciiCode = asciiCode;
        //this.image = image;
    }

    /**
     * Getter for Tile imageName.
     *
     * @return tile Image
//    @XmlElement(name = "imageName") //TODO: Bonnie added this line!
    @XmlTransient //TODO: Bonnie added this line!
    public Image getImage(){
    	return image;
    }*/

    public char getAsciiCode(){
        return asciiCode;
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