package model;

public class Candy extends Collectable {

	int fearRemoved = 30; // Consuming the candy reduces fear level by this amount.

	/**
	 * A Candy object can be picked up by a Player and it is placed in the Player's
	 * Inventory. The Player can then eat the candy by right-clicking on it in the inventory
	 * and selecting "Use Item". This will reduce the Player's fear level by the amount in the
	 * fearRemoved variable.
	 *
	 * @param position
	 * @param imagePath
	 * @param collidable
	 * @param drawable
	 */
	public Candy(Position position, char imagePath, boolean collidable,
			boolean drawable) {
		super(position, imagePath, collidable, drawable);
	}

	/**
	 * This method allows a Player to use this object and reduce fear levels.
	 *
	 * @param currentFear
	 * @return
	 */
	public int eat(int currentFear){
		if(currentFear<fearRemoved){
			return 0;
		}else{
			return currentFear - fearRemoved;
		}
	}

}
