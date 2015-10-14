package view;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;

/**
 * Created by cuan on 9/21/15.
 */
public enum TileAssets {

    GRASS('g',"grass 3.png"),
    STONE('s',"tokenScarlet.png"),
    WOOD('w',"tokenScarlet.png"),
    DIRT('v',"vege.png"),
    CIRCUS('c',"tempB.png"),
    SCHOOL('b',"school_floor.png");


    private final char asciiCode;
    private Image image;

    TileAssets(final char nAsciiCode, final String imagePath){
        asciiCode = nAsciiCode;
        try {
            URL url = getClass().getResource(imagePath);
            image = ImageIO.read(url);
        } catch (IOException e) {
            System.out.printf("Error: cant load "+imagePath);
            System.out.printf(e.getMessage());
            e.printStackTrace();
        }
    }

    public char getAsciiCode() {
        return asciiCode;
    }
    public Image getImage(){
        return image;
    }

    public static Image getAssetImage(char asciiCode) {
        for(TileAssets item: TileAssets.values()){
            if(item.getAsciiCode() == asciiCode){
                return item.getImage();
            }
        }
        throw new IllegalArgumentException("Invalid imagePath: "+asciiCode);
    }

    public static TileAssets getAssetName(char asciiCode) {
        return Arrays.stream(TileAssets.values())
                .filter(item -> item.getAsciiCode() == asciiCode)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Invalid ascii code: " + asciiCode));
    }
}
