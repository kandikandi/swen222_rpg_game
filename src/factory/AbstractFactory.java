package factory;


import control.GameController;
import control.GameKeyListener;
import model.*;

import java.awt.event.KeyListener;

/**
 * Created by cuan on 9/19/15.
 */
public abstract class AbstractFactory {


    /**
     * Creates and returns a 2-D array of Tile objects.
     * Depending on game-mode, concrete implementations
     * will either parse xml, read a text file, or if
     * in test-mode create "dummy" objects for testing
     */
    abstract public Tile[][] createWorldTiles();
    public abstract Player createPlayerActor(GameKeyListener keyListener);
    public abstract Coin createCoin();
    public abstract Inventory createInventory();
    public abstract Collectable createCollectable();
}
