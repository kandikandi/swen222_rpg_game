package model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;


/**
 * A Collectable object is something that a Player can pickup and
 * can be placed in a container, or a Player's Inventory.
 *
 * @author dalyandr
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class Collectable extends Actor {
    boolean inContainer;

    public Collectable(Position position, char imagePath,
                       boolean collidable, boolean drawable) {
        super(position, imagePath, collidable, drawable);
    }

    /**
     * Alternative constructor used if GameObject is already in container
     * at start of game. just has extra parameter container.
     *  @param position
     * @param imagePath
     * @param collidable
     * @param drawable
     * @param container
     */
    public Collectable(Position position, char imagePath,
                       boolean collidable, boolean drawable, Container container) {
        super(position, imagePath, collidable, drawable);
    }

    /**
     * This method is used to put this Collectable into a Container,
     * which the container will do if it has space.
     *
     * @param container
     */
    public void putInContainer(Container container) {
        if (inContainer) {
            return;
        }
        this.setDrawable(false);
        this.setCollidable(false);
        inContainer = true;
        this.putInContainer(container);
    }

    /**
     * When a collectable is dropped, it is removed from the
     * container and it's position updated.
     */
    public void drop() {
        this.inContainer = false;
        this.setDrawable(true);
        this.setCollidable(true);
    }



    public boolean isInContainer() {
		return inContainer;
	}

	public void setInContainer(boolean inContainer) {
		this.inContainer = inContainer;
	}

	@Override
    public void tick() {
    }

}
