package view;

import control.GlobalConst;
import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

/**
 * Created by cuan on 9/21/15.
 */
public enum ActorAssets {

    NOTHING('0', "tokenScarlet.png", 40,40),
    PLAYER('1', "front_girl.png",32,48),
    PLAYER2('2', "tokenPlum.png",GlobalConst.ITEM_SIZE,GlobalConst.ITEM_SIZE),
    COIN('C', "tempGameCoin.png",40,40),
    COINBAG('B', "tempGameCoinBag.png",40,40),
    DOOR('D', "door.jpg",40,40),
    SPECIAL_DOOR('Y', "special_door.jpg",40,40),
    INVENTORY('I', "temp_inv.jpg",40,40),
    KEY('K', "tempGameKey.png",40,40),
    SPECIAL_KEY('X', "SpecialKey.png",GlobalConst.ITEM_SIZE,GlobalConst.ITEM_SIZE),
    ENEMY('E', "evilTemp.png",40,40),
    COLLECTABLE('B', "ring.jpg",40,40),
    WALL('W', "wall.jpg",40,40),
    TREE('T', "Tree1.png",140,140),
    TREE2('J', "Tree2.png",140,140),
    VEGEWALL('V', "wall.jpg",GlobalConst.ITEM_SIZE,GlobalConst.ITEM_SIZE),
    SCHOOLWALL('S', "wall.jpg",40,40),
    CIRCUSWALL('M', "wall.jpg",40,40),
    CANDY('Z', "CandyCane.png",GlobalConst.ITEM_SIZE, GlobalConst.ITEM_SIZE),
    TREASURE('Q', "Treasure.png",GlobalConst.ITEM_SIZE, GlobalConst.ITEM_SIZE)
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