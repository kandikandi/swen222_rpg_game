package model;

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
public final class Position {
    private int xPos, yPos;
    private int hashCode;

    public Position(int xPos, int yPos){
        this.xPos = xPos;
        this.yPos = yPos;
        generateHash();
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
     * Called on construction of location object.
     * Uses the grid co-ord and fine co-ord ints
     * to generate a unique hashcode with a Bijective
     * function
     *
     */
    private int generateHash() {
        int hash = bijectiveAlgorithm(xPos,yPos);
        return hash;
    }

    /**
     * A Bijective Algorithm to generate unique hash codes for
     * any X and Y.
     *
     */
    private int bijectiveAlgorithm(int x, int y){
        int value = ( y +  ((x+1)/2));
        return x +  ( value * value);
    }

    /**
     * Hash code is calculated on position to allow for easy
     * Key mapping to game-world object values.
     */
    @Override
    public int hashCode() {
        return hashCode;
    }

    /**
     * Equals is calculated on hash code thus based on position
     */
    @Override
    public boolean equals(Object obj) {
        if(obj==null){return false;}
    	Position other;
        try{
            other = (Position) obj;
            return this.hashCode() == other.hashCode();
        } catch (ClassCastException e){
            System.out.println(toString()+" "+hashCode()+" against "+obj.toString());
            throw e;
        }
    }

    /**
     * This method prints out the x and y values of the position.
     */
    @Override
    public String toString() {
        return "Location; xPos:"+xPos+" yPos:"+yPos;
    }
}
