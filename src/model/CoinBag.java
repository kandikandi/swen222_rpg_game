package model;

import java.awt.Image;
import java.util.ArrayList;

public class CoinBag extends Container {

	private final int maximumItems = 25;

	public CoinBag(ID id, Position position, Image image, boolean collidable,
			boolean drawable, int boundingBoxSize) {
		super(id, position, image, collidable, drawable, boundingBoxSize);
		this.items = new ArrayList<Actor>();
	}

	/**
	 * Allows for an item to be added to the list of items.
	 *
	 * @param Collectable
	 */
	@Override
	public boolean addItemToContainer(Collectable collectable) {
		// check if it's the kind of thing we can add to the container
		if (collectable == null) {
			return false;
		}else if (! (collectable instanceof Coin) ){
			return false;
		} else if (items.size() < maximumItems) {
			items.add(collectable);
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Allows for an item to be removed to the list of items.
	 *
	 * @param gameObject
	 */
	public boolean removeItemFromContainer(Collectable collectable) {
		if (collectable == null || numberOfObjectInContainer() == 0) {
			return false;
		} else if (items.contains(collectable)) {
			collectable.setPosition(position); // update position
			items.remove(collectable);
			return true;
		} else {
			return false;
		}

	}

	/**
	 * Returns number of coins in bag.
	 *
	 * @return
	 */
	public int numberOfCoinsInCoinBag() {
		return items.size();
	}

}
