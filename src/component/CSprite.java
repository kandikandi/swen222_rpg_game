package component;

import java.awt.*;

/**
 * Created by cuan on 9/8/15.
 */
public class CSprite extends GameComponent {
    public final Image image;
    public final int height, width;
    public CSprite( Image image, int width, int height ) {
        super(2);
        this.image= image;
        this.width = width;
        this.height = height;

    }
}
