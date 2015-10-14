package model;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlTransient;


/**
 * This class provides for GameObjects that represent game items that store
 * other items, such as an Inventory holding collectables.
 *
 */
//@XmlRootElement(name = "container") //TODO: Bonnie added this line!
//@XmlTransient
public class Container extends Actor {

    private int maximumItems = 10;

    @XmlElementWrapper(name = "itemsList") //TODO: Bonnie added this line!
    @XmlElement(name = "item") //TODO: Bonnie added this line!
    protected ArrayList<Actor> items;

    /**
     * @param position
     * @param imageName
     * @param collidable
     * @param drawable
     */
    public Container(Position position, char imageName, boolean collidable,
                     boolean drawable) {
        super(position, imageName, collidable, drawable);
        this.items = new ArrayList<Actor>();
    }

    /**
     * Alternative constructor with initial item. Primarily for testing
     * purposes.
     *  @param position
     * @param imageName
     * @param collidable
     * @param drawable
     */
    public Container(Position position, char imageName, boolean collidable,
                     boolean drawable, Actor... collectables) {
        super(position, imageName, collidable, drawable);
        this.items = new ArrayList<Actor>();
        for (Actor collectable : collectables) {
            addItemToContainer(collectable);
        }
    }

    /**
     * Allows for an item to be added to the list of items.
     * <p>
     * TODO: I think that the picked up item should be removed from list of actors
     */
    public void addItemToContainer(Actor collectable) {
        // check if it's the kind of thing we can add to the container
        if (collectable == null) { //TODO: throw exception here
            return;
        } else if (items.size() < maximumItems) {
            items.add(collectable);
            collectable.setCollidable(false); //TODO: maybe we should redefine collidable to mean if it stops an actor moving
            collectable.setDrawable(false);
            return;
        } else {
            return;
        }

    }



    /**
     * Getter for maximum number of items in container.
     *
     * @return
     */
    public int getMaximumElements(){
    	return this.maximumItems;
    }


    /**
     * Allows for an item to be removed to the list of items.
     */
    public void removeItemFromContainer(Actor actor) {
        if (actor == null || numItemsInContainer() == 0) {
            return;
        } else if (items.contains(actor)) {
            items.remove(actor);
            return;
        } else {
            return;
        }

    }

    /**
     * Returns number of items in this container.
     */
    public int numItemsInContainer() {
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


//    /**
//     * This method removes all items from the Container by setting
//     * each to null.
//     *
//     */
//    public void empty(){
//    	for(Actor actor : items){
//    		items.remove(actor);
//    	}
//
//    }

    /**
	 * Returns description of bag, as well as number of coins within.
	 *
	 */
	@Override
	public String getDescription() {
		if (this.actorDescription == null) {
			actorDescription = " ";
		}
		return "This is a Cotainer with a limit of "+getMaximumElements()+", it currently has " + numItemsInContainer()
				+ " items!";
	}

    @Override
    public void tick() {}

}
