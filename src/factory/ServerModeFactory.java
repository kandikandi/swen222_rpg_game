package factory;

import control.GameController;
import control.GameKeyListener;
import model.*;

import java.util.List;


public class ServerModeFactory extends AbstractFactory {

    public ServerModeFactory(GameController gameController) {
        super(gameController);
    }

    @Override
    public Tile[][] createWorldTiles() {
        return new Tile[0][];
    }

    @Override
    public List<Actor> createActorList() {
        return null;
    }

    @Override
    public Player createPlayerActor(GameController gameController) {
        return null;
    }

    @Override
    public Coin createCoin() {
        return null;
    }

    @Override
    public Inventory createInventory(boolean pickedUP, int xPos, int yPos) {
        return null;
    }


    @Override
    public Collectable createCollectable() {
        return null;
    }

    @Override
    public Key createKey(int xPos, int yPos) {
        return null;
    }

	@Override
	public Enemy createEnemyActor(int xPos, int yPos) {
		return null;
	}


}
