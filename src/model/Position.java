package model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by cuan on 9/13/15.
 *
 * A position class will be used for anything with a game-world
 * position. This class is also final so it can be safely used as a
 * key for a map to a game-world object.
 */
@XmlRootElement(name = "position") //TODO: Bonnie added this line!
public final class Position implements Serializable {
    private int xPos, yPos;

    public Position(int xPos, int yPos){
        this.xPos = xPos;
        this.yPos = yPos;
    }

    private Position(){
    }

    /**
     * Getter function for x element.
     *
     * @return value of xpos
     */
    @XmlElement(name = "xposition") //TODO: Bonnie added this line!
    public int getxPos() {
        return xPos;
    }

    /**
     * Setter function for x element.
     *
     */
    public void setxPos(int xPos) {
        this.xPos = xPos;
    }

    /**
     * Getter function for y element.
     *
     * @return value of ypos
     */
    @XmlElement(name = "yposition") //TODO: Bonnie added this line!
    public int getyPos() {
        return yPos;
    }

    /**
     * Setter function for y element.
     *
     */
    public void setyPos(int yPos) {
        this.yPos = yPos;
    }

     /**
     * This method prints out the x and y values of the position.
     */
    @Override
    public String toString() {
        return "Location; xPos:"+xPos+" yPos:"+yPos;
    }
}
