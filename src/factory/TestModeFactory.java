package factory;

import control.GameKeyListener;
import control.Main;
import control.MovementStrategy;
import control.PlayerMoveStrategy;
import model.*;
import ui.TEST_IMAGE;
import view.GameObjectAssets;
import view.TestWorlds;
import view.TileAssets;

import java.awt.*;
import java.util.*;
import java.util.List;


public class TestModeFactory extends AbstractFactory{

    /**
     * Used in testing only, reads a dummy text file from TestWorlds
     *
     * @return a 2-D array of Tile objects.
     */
    @Override
    public Tile[][] createWorldTiles() {
        //parse "fake file"
        char[][]tileCode = TestWorlds.getSmallMap();
        Tile[][]result = new Tile[tileCode.length][tileCode[0].length];

        for(int row = 0; row< tileCode.length; row++){
            for(int col = 0; col<tileCode[0].length; col++){
                char asciiCode = tileCode[row][col];
                Image image = TileAssets.getAssetName(asciiCode).getImage();
                image = image.getScaledInstance(Main.TILE_SIZE,Main.TILE_SIZE,Image.SCALE_FAST);
                Position position = new Position(col*Main.TILE_SIZE, row*Main.TILE_SIZE);
                result[row][col] = new Tile(image,position);
            }
        }
        return  result;
    }

    @Override
    public List<GameObject> createGameObjectList() {
        //parse "fake file"
        char[][]tileCode = TestWorlds.getSmallObjectMap();
        List<GameObject> result = new ArrayList<>();

        for(int row = 0; row< tileCode.length; row++){
            for(int col = 0; col<tileCode[0].length; col++){

                char asciiCode = tileCode[row][col];
                if (asciiCode == '0'){continue;}
                Position position = new Position(col*Main.TILE_SIZE, row*Main.TILE_SIZE);
                int size = Main.ITEM_SIZE;
                Image image = GameObjectAssets.getAssetName(asciiCode).getImage();
                image = image.getScaledInstance(size,size,Image.SCALE_FAST);
                GameObjectAssets asset = GameObjectAssets.getAssetName(asciiCode);
                GameObject go = null;
                switch (asset){
                    case KEY:
                        go = new Key(ID.KEY,position,image,true,true,size);
                        break;
                }

                result.add(go);

            }
        }
        return  result;
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
    public Inventory createInventory(boolean pickedUP, int xPos, int yPos) {
        boolean collidable;
        boolean drawable;
        if(pickedUP){
            collidable = false;
            drawable = false;
        }else {
            collidable = true;
            drawable = true;
        }
        int size = Main.ITEM_SIZE;
        Position position = new Position(xPos, yPos);
        Image image = TEST_IMAGE.INVENTORY.getImage();
        image = image.getScaledInstance(size,size,Image.SCALE_FAST);
        Inventory inventory = new Inventory(ID.CONTAINER,position,image,collidable,drawable,size,null);

        return inventory;
    }


    @Override
    public Collectable createCollectable() {
        return null;
    }

    @Override
    public Key createKey(int xPos, int yPos) {
        Position pos = new Position(xPos, yPos);
        Image image = TEST_IMAGE.KEY.getImage();
        int size = Main.ITEM_SIZE;
        image = image.getScaledInstance(size,size,Image.SCALE_FAST);
        Key key = new Key(ID.KEY, pos, image,true,true,size);
        return key;
    }


}
