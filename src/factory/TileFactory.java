package factory;

import control.Main;
import model.AbstractGameObject;
import model.Location;
import model.Tile;
import system.GameException;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;


/**
 * Created by cuan on 9/13/15.
 */
public abstract class TileFactory {
    static private Tile[][] products = new Tile[Main.NUM_TILE_ROW][Main.NUM_TILE_COL];

   static public Tile[][] createTestWorld(){
       Tile tile;
       for(int row = 0; row<Main.NUM_TILE_ROW; row++){
           for(int col = 0; col<Main.NUM_TILE_COL; col++){
               createTestProduct(col*Main.TILE_SIZE, row*Main.TILE_SIZE);
           }
       }
       return products;

   }

    static public Tile createTestProduct(int xPos, int yPos){
        if(!Main.TEST_MODE){
            try {
                throw new GameException("TileFactory createTestProduct while not TEST_MODE TRUE");
            }catch (GameException e){
                System.out.printf(e.getMessage());
                e.printStackTrace();
            }
        }
        Location location = new Location(xPos,yPos);

            Image image = TEST_IMAGE.SCARLET.getImage();
            /*try {
                image = ImageIO.read(new File("tokenPlum.png"));
            } catch (IOException e) {
                System.out.printf(e.getMessage());
                e.printStackTrace();
            }*/

        Tile tile = new Tile(image, location, true, true, null);
        products[yPos/Main.TILE_SIZE][xPos/Main.TILE_SIZE] = tile;
        return tile;


    }
    public Tile createProduct(Image image, int x, int y, boolean collidable){
        if(Main.TEST_MODE){
            return createTestProduct(x, y);
        }else{
            return null;
        }
    }

    //TODO: build a parser class to scan world file or get data from xml manager
    public static Tile[][] createWorld() {
        return new Tile[0][];
    }
}
