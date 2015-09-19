package factory;

import model.Tile;

/**
 * Created by cuan on 9/19/15.
 */
public class ServerModeFactory extends AbstractFactory {
    @Override
    public Tile[][] createWorldTiles() {
        return new Tile[0][];
    }
}
