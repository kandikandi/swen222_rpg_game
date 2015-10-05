package View;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by cuan on 9/13/15.
 *
 * This enum class assigns an ID to each major object type in the game.
 */
@XmlRootElement(name = "id") //TODO: Bonnie added this line!
public enum ID {
//    TILE(0),
    ITEM(1),
    PLAYER(2),
    MONSTER(3),
    CONTAINER(4),
    COLLECTABLE(5),
    KEY(6),
    ENEMY(7),
    DOOR(8),
    WALL(9),
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
