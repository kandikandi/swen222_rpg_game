package view;

import control.Main;

import javax.imageio.ImageIO;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

/**
 * Created by cuan on 9/21/15.
 */
public enum ActorAssets {

    NOTHING('0',"tokenScarlet.png", Main.TILE_SIZE,Main.TILE_SIZE),
    PLAYER('1',"front_girl.png",32,48),
    PLAYER2('2',"tokenPlum.png",Main.ITEM_SIZE,Main.ITEM_SIZE),
    COIN('C',"tempGameCoin.png",Main.TILE_SIZE,Main.TILE_SIZE),
    COINBAG('B',"tempGameCoinBag.png",Main.TILE_SIZE,Main.TILE_SIZE),
    DOOR('D',"door.jpg",Main.TILE_SIZE,Main.TILE_SIZE),
    SPECIAL_DOOR('Y',"special_door.jpg",Main.TILE_SIZE,Main.TILE_SIZE),
    INVENTORY('I',"temp_inv.jpg",Main.TILE_SIZE,Main.TILE_SIZE),
    KEY('K',"tempGameKey.png",Main.ITEM_SIZE,Main.ITEM_SIZE),
    SPECIAL_KEY('X',"SpecialKey.png",Main.ITEM_SIZE,Main.ITEM_SIZE),
    ENEMY('E',"evilTemp.png",Main.TILE_SIZE,Main.TILE_SIZE),
    COLLECTABLE('B',"ring.jpg",Main.TILE_SIZE,Main.TILE_SIZE),
    WALL('W', "wall.jpg",Main.TILE_SIZE,Main.TILE_SIZE),
    TREE('T', "Tree1.png",140,140),
    VEGEWALL('V',"wall.jpg",Main.ITEM_SIZE,Main.ITEM_SIZE),
    SCHOOLWALL('S',"wall.jpg",Main.TILE_SIZE,Main.TILE_SIZE),
    CIRCUSWALL('M',"wall.jpg",Main.TILE_SIZE,Main.TILE_SIZE),
    CANDY('Z',"CandyCane.png",Main.ITEM_SIZE, Main.ITEM_SIZE),
    TREASURE('Q',"Treasure.png",Main.ITEM_SIZE, Main.ITEM_SIZE)
    ;



    private final char asciiCode;
    private final String imagePath;
    private Image image;
    private int width,height;

    ActorAssets(final char nAsciiCode, final String imagePath, int width, int height){
        asciiCode = nAsciiCode;
        this.width = width;
        this.height = height;

        this.imagePath = imagePath;
        try {
            image = ImageIO.read(new File(imagePath)).getScaledInstance(width,height,Image.SCALE_FAST);
        } catch (IOException e) {
            System.out.printf("Error: cant load "+imagePath);
            System.out.printf(e.getMessage());
            e.printStackTrace();
        }
    }

    public static ActorAssets getAssetName(char asciiCode) {
        return Arrays.stream(ActorAssets.values())
                .filter(item -> item.getAsciiCode() == asciiCode)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Invalid ascii code: " + asciiCode));
    }

    public static Image getAssetImage(char asciiCode) {
        for(ActorAssets item: ActorAssets.values()){
            if(item.getAsciiCode() == asciiCode){
                return item.getImage();
            }
        }
        throw new IllegalArgumentException("Invalid asciiCode: "+asciiCode);
    }

    public int getWidth(){return width;}

    public int getHeight(){return height;}

    public char getAsciiCode() {
        return asciiCode;
    }

    private Image getImage(){
        return image;
    }

}

