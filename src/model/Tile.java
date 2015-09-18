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
 * drawable....
 *
 * To simplify game logic , each Tile knows about GameObjects which are next
 * to it on the array.
 *
 */
public final class Tile extends GameObject {
    private HashMap<DIR, GameObject> neighbours;
//    private GameObject objectOnThisTile; // will just use containers

    // not sure what drawable is, sorry....

    public Tile(Image image, Position position,
                boolean passable, boolean drawable,  HashMap<DIR, GameObject> neighbours, int tileSize) {
        super(ID.TILE,position, image, passable, drawable, tileSize);
        this.neighbours = neighbours;

    }

    /**
     * This method returns a GameObject which is this tile object's "neighbour" in the
     * direction provided in the parameter
     *
     * @param direction
     * @return the neighbour object in that direction.
     */
    public GameObject getNeighbour(DIR dir) {
        return neighbours.get(dir);
    }


//    /**
//     * This method returns whether a Tile has a GameObject on it, such as
//     * a treat.
//     *
//     * @return
//     */
//    public boolean tileHasObjectOnIt(){
//    	return objectOnThisTile != null;
//    }





//    /**
//     *  //Will get rid of this and just use Container
//     *
//     * If a Tile has a GameObject on it,  this method returns it.
//     *
//     * @return GameObject on the Tile
//     */
//    public GameObject getObjectOnThisTile(){
//    	if(this.tileHasObjectOnIt()){
//    		return objectOnThisTile;
//    	}
//    	return null;
//    }
//
//    /**
//	   *  // will just use container
//     * This method returns a string description of what type of object is on top of this Tile,
//     * eg treat.
//     *
//     * @return string description of GameObject on Tile.
//     */
//    public String getTypeOfObjectOnThis(){
//    	if(this.tileHasObjectOnIt()){
//    		return objectOnThisTile.getClass().toString();
//    	}
//    	return null;
//    }

    /**
     * This method is a setter for an individual "neighbour" GameObject
     * to this Tile.
     *
     * @param dir
     * @param neighbour
     */
    public void setNeighbour(DIR dir, GameObject neighbour) {
            neighbours.putIfAbsent(dir, neighbour);
    }

    /**
     * This method is a setter for all "neighbour" GameObjects
     * to this Tile.
     *
     * @param neighbours is the collection of all neighbours.
     */

    public void setNeighboursTo(HashMap<DIR, GameObject> neighbours){
    	this.neighbours = neighbours;
    }

}