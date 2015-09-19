package factory;

import control.Main;
import model.Position;
import model.Tile;

import java.awt.*;

/**
 * Created by cuan on 9/19/15.
 */
public class TestModeFactory extends AbstractFactory{
    /**
     * Used in testing only, creates tile array with temporary art assets
     *
     * @return a 2-D array of Tile objects.
     */
    @Override
    public Tile[][] createWorldTiles() {
        Tile[][] world = new Tile[Main.NUM_TILE_ROW][Main.NUM_TILE_COL];
        for(int row = 0; row< Main.NUM_TILE_ROW; row++){
            for(int col = 0; col<Main.NUM_TILE_COL; col++){
                world[row][col] = createTestTile(col*Main.TILE_SIZE, row*Main.TILE_SIZE);
            }
        }
        return world;
    }

    /**
     * Method used in testing stages
     *
     * @return a Tile object.
     */
    private Tile createTestTile(int xPos, int yPos){
        Position position = new Position(xPos,yPos);
        Image image = TEST_IMAGE.SCARLET.getImage();
        image = image.getScaledInstance(Main.TILE_SIZE,Main.TILE_SIZE,Image.SCALE_FAST);
        Tile tile = new Tile(image,position);
        return tile;
    }

}
