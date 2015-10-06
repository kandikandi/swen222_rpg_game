package model;

import java.util.ArrayList;

import view.ID;

public class CoinBag extends Container {

	private final int maximumItems = 50;

	/**
	 * A CoinBag object can be picked up by a Player and placed into the
	 * Player's Inventory. This increases the number of Coin objects a Player
	 * can carry from 9 to 50 per CoinBag.
	 *
	 * @param id
	 * @param position
	 * @param imagePath
	 * @param collidable
	 * @param drawable
	 * @param boundingBoxSize
	 */
	public CoinBag(ID id, Position position, char imagePath,
			boolean collidable, boolean drawable, int boundingBoxSize) {
		super(id, position, imagePath, collidable, drawable, boundingBoxSize);
		this.items = new ArrayList<Actor>();
	}

	/**
	 * Allows for an item to be added to the list of items.
	 *
	 * @param collectable
	 */
	@Override
	public void addItemToContainer(Collectable collectable) {
		if (collectable == null) {
			return;
		} else if (!(collectable instanceof Coin)) {
			return;
		} else if (items.size() < maximumItems) {
			items.add((Coin) collectable);
			collectable.setCollidable(false);
			collectable.setDrawable(false);
			return;
		} else {
			return;
		}
	}

	/**
	 * Allows for an item to be removed from the list of items.
	 *
	 * @param collectable
	 */
	public void removeItemFromContainer(Collectable collectable) {
		if (collectable == null || numItemsInContainer() == 0) {
			return;
		} else if (items.contains(collectable)) {
			collectable.setPosition(position); // update position
			items.remove(collectable);
			return;
		} else {
			return;
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

	/**
	 * Returns description of bag, as well as number of coins within.
	 *
	 */
	@Override
	public String getDescription() {
		if (this.actorDescription == null) {
			actorDescription = " ";
		}
		return "This is a CoinBag, it contains " + numberOfCoinsInCoinBag()
				+ " coins!";
	}

}
