package model;

import java.awt.Image;

/**
 * A coin is an object a player can pick up and can store in either
 * an Inventory or CoinBag.
 *
 * @author dalyandr
 *
 */
public class Coin extends Collectable {

	public Coin(ID id, Position position, Image image, boolean collidable,
			boolean drawable, int boundingBoxSize) {
		super(id, position, image, collidable, drawable, boundingBoxSize);
	}
}
