package model;

import view.ID;

/**
 * A coin is an object a player can pick up and can store in either
 * an Inventory or CoinBag.
 *
 * @author dalyandr
 *
 */
public class Coin extends Collectable {

	public Coin(ID id, Position position, char imagePath, boolean collidable,
			boolean drawable, int boundingBoxSize) {
		super(id, position, imagePath, collidable, drawable, boundingBoxSize);
	}
}
