package factory;

import control.Main;
import model.Position;
import model.Tile;
import system.GameException;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


/**
 * Created by cuan on 9/13/15.
 *
 * The tile factory sets up and returns a 2-D array of tiles which represent the world
 * ground / base throughout gameplay.
 *
 * The factory can be created by reading in an XML file that describes
 * a room or level.
 */
public abstract class TileFactory {

	static private Tile[][] products = new Tile[Main.NUM_TILE_ROW][Main.NUM_TILE_COL];

   /**
    * Method used in testing stages along with createTestProduct.
    *
    * @return a 2-D array of Tile objects.
    */
   static public Tile[][] createTestWorld(){
       Tile tile;
       for(int row = 0; row<Main.NUM_TILE_ROW; row++){
           for(int col = 0; col<Main.NUM_TILE_COL; col++){
               createTestProduct(col*Main.TILE_SIZE, row*Main.TILE_SIZE);
           }
       }
       return products;

   }

   /**
    * Method used in testing stages along with
    *
    * @return a Tile object.
    */
    static public Tile createTestProduct(int xPos, int yPos){
        if(!Main.TEST_MODE){
            try {
                throw new GameException("TileFactory createTestProduct while not TEST_MODE TRUE");
            }catch (GameException e){
                System.out.printf(e.getMessage());
                e.printStackTrace();
            }
        }

        Position location = new Position(xPos,yPos);
        Image image = TEST_IMAGE.SCARLET.getImage();
        Tile tile = new Tile(image, location, true, true, null, Main.TILE_SIZE);
        products[yPos/Main.TILE_SIZE][xPos/Main.TILE_SIZE] = tile;
        return tile;


    }

    /**
     * Method used in testing stages along with.
     *
     * @return a Tile object.
     */
    public Tile createProduct(Image image, int x, int y, boolean collidable){
        if(Main.TEST_MODE){
            return createTestProduct(x, y);
        }else{
            return null;
        }
    }


    /**
     *
     * @return 2-D array of Tile objects.
     */
    //TODO: build a parser class to scan world file or get data from xml manager
    public static Tile[][] createWorld(String fileName, int height, int width) {
//    	Scanner scanner = new Scanner()
    	File file = new File("src/tinyMap.txt");
		Scanner sc = null;
		try {
			sc = new Scanner(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		for (int row = 0; row < width; row++) {
			String line = sc.nextLine();
			for (int col = 0; col < height; col++) {
				char c = line.charAt(col);

				//////////////////////////////////
				// build appropriate object
				//////////////////////

			}
		}


    	return new Tile[0][];
    }

    /**
    *
    * @return 2-D array of Tile objects.
    */
   //TODO: build a parser class to scan world file or get data from xml manager
   public static Tile[][] createWorld() {
       return new Tile[0][];
   }


}
