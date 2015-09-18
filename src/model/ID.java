package model;

/**
 * Created by cuan on 9/13/15.
 */
public enum ID {
    TILE(0),
    ITEM(1),
    PLAYER(2),
    MONSTER(3);


    private final int value;

    ID(final int nValue){
        value = nValue;
    }

    public int getValue() {
        return value;
    }


}
