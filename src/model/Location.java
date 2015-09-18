package model;

/**
 * Created by cuan on 9/13/15.
 *
 * A location class that will be used for anything with a game-world
 * position. This class is also final so it can be safely used as a
 * key for a map to a game-world object.
 */
public final class Location {
    private int xPos, yPos, realX, realY;
    private int hashCode;

    Location(int xPos, int yPos, int realX, int realY){
        this.xPos = xPos;
        this.yPos = yPos;
        this.realX = realX;
        this.realY = realY;
        generateHash();
    }

    public int getxPos() {
        return xPos;
    }

    public void setxPos(int xPos) {
        this.xPos = xPos;
    }

    public int getyPos() {
        return yPos;
    }

    public void setyPos(int yPos) {
        this.yPos = yPos;
    }

    public int getRealX() {
        return realX;
    }

    public void setRealX(int realX) {
        this.realX = realX;
    }

    public int getRealY() {
        return realY;
    }

    public void setRealY(int realY) {
        this.realY = realY;
    }

    public Location(int xPos, int yPos) {
        this.xPos = xPos;
        this.yPos = yPos;
        realX = 0;
        realY = 0;
    }

    /**
     * Called on construction of location object.
     * Uses the grid co-ord and fine co-ord ints
     * to generate a unique hashcode with a Bijective
     * function
     *
     */
    private int generateHash() {
        int hashGrid = bijectiveAlgro(xPos,yPos);
        int hashFine = bijectiveAlgro(realX,realY);
        return bijectiveAlgro(hashGrid,hashFine);
    }

    /**
     * A Bijective Algorithm to generate unique hash codes for
     * any X and Y.
     *
     */
    private int bijectiveAlgro(int x, int y){
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
        Location other;
        try{
            other = (Location) obj;
            return this.hashCode() == other.hashCode();
        } catch (ClassCastException e){
            System.out.println(toString()+" "+hashCode()+" against "+obj.toString());
            throw e;
        }
    }

    @Override
    public String toString() {
        return "Location; xPos:"+xPos+" yPos:"+yPos+" realX:"+realX+" realY:"+realY;
    }
}
