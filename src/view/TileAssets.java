package view;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

/**
 * Created by cuan on 9/21/15.
 */
public enum TileAssets {

    GRASS('G',"tokenScarlet.png"),
    WOOD('W',"tokenScarlet.png");


    private final char asciiCode;
    private Image image;

    TileAssets(final char nAsciiCode, final String imagePath){
        asciiCode = nAsciiCode;
        try {
            image = ImageIO.read(new File(imagePath));
        } catch (IOException e) {
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

    public static TileAssets getAssetName(char asciiCode) {
        return Arrays.stream(TileAssets.values())
                .filter(item -> item.getAsciiCode() == asciiCode)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Invalid ascii code: " + asciiCode));
    }
}