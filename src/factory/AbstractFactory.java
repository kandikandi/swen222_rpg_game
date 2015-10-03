package factory;


import control.GameController;
import control.GameKeyListener;
import model.*;

import java.awt.event.KeyListener;
import java.net.InetAddress;
import java.util.List;

import javax.xml.bind.annotation.XmlTransient;

/**
 * Created by cuan on 9/19/15.
 */
public abstract class AbstractFactory {
	@XmlTransient //TODO: Bonnie added this line!
    protected final  GameController gameController;

    AbstractFactory(GameController gameController){
        this.gameController = gameController;
    }


    /**
     * Creates and returns a 2-D array of Tile objects.
     * Depending on game-mode, concrete implementations
     * will either parse xml, read a text file, or if
     * in test-mode create "dummy" objects for testing
     */
    public abstract Tile[][] createWorldTiles();

    public abstract List<Actor> createActorList();

    public abstract Player createPlayerActor(GameController gameController);

    public abstract Coin createCoin();

    /**
     * If pickedUP is true, the inventory will not be collidable or drawn.
     *
     * @param pickedUP
     * @param xPos
     * @param yPos
     * @return
     */
    public abstract Inventory createInventory(boolean pickedUP, int xPos, int yPos);

    public abstract Collectable createCollectable();

    /**
     * Creates a key at the specified x/y co-ordinates
     *
     * @param xPos
     * @param yPos
     * @return Key
     */
    public abstract Key createKey(int xPos, int yPos);





    public abstract Enemy createEnemyActor(int xPos, int yPos);


//	public abstract Player createPlayerActor(GameController gameController,
//			String username, InetAddress ipAddress, int port, int playernum);

}
