package model;



/**
 * A Coin is an object a player can pick up and can store in either
 * an Inventory or CoinBag.
 *
 * @author dalyandr
 *
 */
public class Coin extends Collectable {

	boolean special; //special coin is worth more.

	public Coin(Position position, char imagePath, boolean collidable,
				boolean drawable) {
		super(position, imagePath, collidable, drawable);
	}


	public Coin(Position position, char imagePath, boolean collidable,
			boolean drawable, boolean special) {
	super(position, imagePath, collidable, drawable);
	this.special = special;
}


	public boolean isSpecial() {
		return special;
	}
}
