package control;

/**
 * Created by cuan on 9/13/15.
 *
 *
 *
 */
public enum DIR {
    NORTH(0),
    SOUTH(1),
    EAST(2),
    WEST(3),
    UP(4),
    DOWN(5),
    LEFT(6),
    RIGHT(7);

    private final int value;

    DIR(final int nValue){
        value = nValue;
    }

    public int getValue() {
        return value;
    }
}
