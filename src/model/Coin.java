package model;



/**
 * A Coin is an object a player can pick up and can store in either
 * an Inventory or CoinBag.
 *
 * @author dalyandr
 *
 */
public class Coin extends Collectable {

	public Coin(Position position, char imagePath, boolean collidable,
				boolean drawable) {
		super(position, imagePath, collidable, drawable);
	}
}
