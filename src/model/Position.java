package model;

import control.Main;
import save.position.PositionAdapter;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 * Created by cuan on 9/13/15.
 *
 * A position class will be used for anything with a game-world
 * position. This class is also final so it can be safely used as a
 * key for a map to a game-world object.
 */

@XmlRootElement(name = "position") //TODO: Bonnie added this line!
@XmlJavaTypeAdapter(PositionAdapter.class)
public final class Position  implements Serializable {
    private int xPos, yPos;
    private final int bBoxXOffset, bBoxYOffset;
    private final BoundingBox boundingBox;

    public Position(int xPos, int yPos){
        int size = Main.TILE_SIZE;
        this.boundingBox = new BoundingBox(size,size);
        this.xPos = xPos;
        this.yPos = yPos;
        this.bBoxXOffset = boundingBox.getXOffset();
        this.bBoxYOffset = boundingBox.getYOffset();
    }
    
    public Position(int xPos, int yPos, int size){
        this.boundingBox = new BoundingBox(size,size);
        this.xPos = xPos;
        this.yPos = yPos;
        this.bBoxXOffset = boundingBox.getXOffset();
        this.bBoxYOffset = boundingBox.getYOffset();
    }

    public Position(int xPos, int yPos, BoundingBox boundingBox){
        this.xPos = xPos;
        this.yPos = yPos;
        this.boundingBox = boundingBox;
        this.bBoxXOffset = boundingBox.getXOffset();
        this.bBoxYOffset = boundingBox.getYOffset();
    }

    public BoundingBox getBoundingBox() {
        boundingBox.setLocation(xPos+bBoxXOffset, yPos+bBoxYOffset);
        return boundingBox;
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
        System.out.println(">>>>>>>> new xPos"+xPos);
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

