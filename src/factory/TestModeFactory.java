package factory;

import control.GameKeyListener;
import control.Main;
import control.MovementStrategy;
import control.PlayerMoveStrategy;
import model.*;
import ui.TEST_IMAGE;

import java.awt.*;


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

    @Override
    public Player createPlayerActor(GameKeyListener keyListener) {
        MovementStrategy ms = new PlayerMoveStrategy(keyListener);
        Position loc = new Position(40, 40);
        Image image = TEST_IMAGE.MR_PLUM.getImage();
        image = image.getScaledInstance(Main.PLAYER_SIZE,Main.PLAYER_SIZE,Image.SCALE_FAST);
        Player player = new Player(ID.PLAYER, loc,image, false, true, Main.PLAYER_SIZE);
        player.setMoveStrat(ms);
        return player;
    }

    @Override
    public Coin createCoin() {
        return null;
    }

    @Override
    public Inventory createInventory() {
        return null;
    }

    @Override
    public Collectable createCollectable() {
        return null;
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
