package model;

/**
 * Created by cuan on 9/13/15.
 *
 * This enum class assigns an ID to each major object type in the game.
 */
public enum ID {
//    TILE(0),
    ITEM(1),
    PLAYER(2),
    MONSTER(3),
    CONTAINER(4),
    COLLECTABLE(5),
    KEY(6),
    ENEMY(7)
    ;

    private final int value;

    ID(final int nValue){
        value = nValue;
    }

    /**
     *
     * 	???
     *
     * Getter method for ID value.
     *
     * @return ID value
     */
    public int getID() {
        return value;
    }

}
