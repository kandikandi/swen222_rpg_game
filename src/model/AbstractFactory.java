package model;

import java.util.List;

/**
 * Created by cuan on 9/19/15.
 */
public abstract class AbstractFactory {

    AbstractFactory(){

    }

    /**
     * Creates and returns a 2-D array of Tile objects.
     * Depending on game-mode, concrete implementations
     * will either parse xml, read a text file, or if
     * in test-mode create "dummy" objects for testing
     */
    public abstract Tile[][] createWorldTiles();

    public abstract List<Actor> createActorList();

    public abstract Player createPlayerActor(int clientNum);

    public abstract Coin createCoin(int xPos, int yPos);

    /**
     * If pickedUP is true, the inventory will not be collidable or drawn.
     *
     * @param pickedUP
     * @param xPos
     * @param yPos
     * @return
     */
//    public abstract Inventory createInventory(boolean pickedUP, int xPos, int yPos);

    public abstract Collectable createCollectable(int xPos, int yPos);

    /**
     * Creates a key at the specified x/y co-ordinates
     *
     * @param xPos
     * @param yPos
     * @return Key
     */
    public abstract Key createKey(int xPos, int yPos);

    public abstract Enemy createEnemyActor(int xPos, int yPos);

	public abstract Door createDoor(int xPos, int yPos);

	public abstract Wall createWall(int xPos, int yPos);


}
