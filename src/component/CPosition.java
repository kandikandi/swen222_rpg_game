package component;

/**
 * Created by cuan on 9/8/15.
 */
public class CPosition extends GameComponent {
    public int xpos;
    public int ypos;

    public CPosition( int xpos, int ypos) {
        super(1);
        this.xpos = xpos;
        this.ypos = ypos;
    }
}
