package factory;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * Created by cuan on 9/13/15.
 */
public enum TEST_IMAGE {

    MR_PLUM("tokenPlum.png"),
    SCARLET("tokenScarlet.png");

    private  Image image;

    TEST_IMAGE(final String path){

        try {
            image = ImageIO.read(new File(path));
        } catch (IOException e) {
            System.out.printf(e.getMessage());
            e.printStackTrace();
        }
    }

    public Image getImage() {
        return image;
    }
}
