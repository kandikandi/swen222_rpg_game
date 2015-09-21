package view;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

/**
 * Created by cuan on 9/21/15.
 */
public enum ActorAssets {

    NOTHING('0',"tokenScarlet.png"),
    PLAYER1('1',"front_girl.png"),
    PLAYER2('1',"tokenPlum.png"),
    COIN('C',"coin40.jpg"),
    DOOR('D',"door.jpg"),
    INVENTORY('I',"temp_inv.jpg"),
    KEY('K',"temp_key.jpg");


    private final char asciiCode;
    private Image image;

    ActorAssets(final char nAsciiCode, final String imagePath){
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

    public static ActorAssets getAssetName(char asciiCode) {
        return Arrays.stream(ActorAssets.values())
                .filter(item -> item.getAsciiCode() == asciiCode)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Invalid ascii code: " + asciiCode));
    }
}
