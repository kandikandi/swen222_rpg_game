package model;

import java.awt.Image;
import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * This class provides for GameObjects that represent game items that store
 * other items, such as a backpack holding collectables.
 *
 * @author dalyandr
 */
@XmlRootElement(name = "container") //TODO: Bonnie added this line!
public class Container extends Actor {

    private int maximumItems = 10;

    @XmlElementWrapper(name = "itemsList") //TODO: Bonnie added this line!
    @XmlElement(name = "item") //TODO: Bonnie added this line!
    protected ArrayList<Actor> items;

    /**
     * @param id
     * @param position
     * @param imageName
     * @param collidable
     * @param drawable
     * @param boundingBoxSize
     */
    public Container(ID id, Position position, char imageName, boolean collidable,
                     boolean drawable, int boundingBoxSize) {
        super(id, position, imageName, collidable, drawable, boundingBoxSize);
        this.items = new ArrayList<Actor>();
    }

    /**
     * Alternative constructor with initial item. Primarily for testing
     * purposes.
     *
     * @param id
     * @param position
     * @param imageName
     * @param collidable
     * @param drawable
     * @param boundingBoxSize
     */
    public Container(ID id, Position position, char imageName, boolean collidable,
                     boolean drawable, int boundingBoxSize, Collectable... collectables) {
        super(id, position, imageName, collidable, drawable, boundingBoxSize);
        this.items = new ArrayList<Actor>();
        for (Collectable collectable : collectables) {
            addItemToContainer(collectable);
        }
    }

    /**
     * Allows for an item to be added to the list of items.
     * <p>
     * TODO: I think that the picked up item should be removed from list of actors
     */
    public boolean addItemToContainer(Collectable collectable) {
        // check if it's the kind of thing we can add to the container
        if (collectable == null) { //TODO: throw exception here
            return false;
        } else if (items.size() < maximumItems) {
            items.add(collectable);
            collectable.setCollidable(false); //TODO: maybe we should redefine collidable to mean if it stops an actor moving
            collectable.setDrawable(false);
            return true;
        } else {
            return false;
        }

    }

    /**
     * Allows for an item to be removed to the list of items.
     */
    public boolean removeItemFromContainer(Collectable collectable) {
        if (collectable == null || numberOfObjectInContainer() == 0) {
            return false;
        } else if (items.contains(collectable)) {
            collectable.setPosition(position); //update position
            collectable.setCollidable(true);
            collectable.setDrawable(true);
            items.remove(collectable);
            return true;
        } else {
            return false;
        }

    }

    /**
     * Returns number of items in this container.
     */
    public int numberOfObjectInContainer() {
        return items.size();
    }


    /**
     * This method returns entire contents of this Container.
     *
     * @returnlist of GameObject objects in container
     */
    public ArrayList<Actor> returnContents() {
        return items;
    }

    @Override
    public void tick() {
        // TODO Auto-generated method stub

    }


}
