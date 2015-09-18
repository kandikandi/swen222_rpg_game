package model;

import java.awt.Image;

public class Inventory extends Container {

	final private Player inventoryOwner;


	/**
	 * Constructor with owning player added on construction. Also can insert items at same time.
	 *
	 * @param id
	 * @param position
	 * @param image
	 * @param collidable
	 * @param drawable
	 * @param containerSize
	 * @param boundingBoxSize
	 * @param inventoryOwner
	 * @param gameObjects
	 */
	public Inventory(ID id, Position position, Image image, boolean collidable,
			boolean drawable, int boundingBoxSize, Player inventoryOwner,
			 Collectable... collectables) {
		super(id, position, image, false, false, boundingBoxSize, collectables);
		this.inventoryOwner = inventoryOwner;

	}

	public Player getOwner() {
		return inventoryOwner;
	}

	/**
	 * Position needs to update with player, so items within also move with
	 * player.
	 *
	 */
	@Override
	public Position getPosition() {
			return inventoryOwner.getPosition();
	}

	/**
	 *  This method is used to check whether a player has a key when
	 *  near a door.
	 *
	 * @return
	 */
	public boolean containsKey(){
		for(GameObject gameObject : items){
			if(gameObject instanceof Key){
				return true;
			}
		}
		return false;
	}

	/**
	 * Returns (first) key found in this Inventory.
	 *
	 * @return
	 */
	public Key getKey() {
		for(GameObject gameObject : items){
			if(gameObject instanceof Key){
				return (Key) gameObject;
			}
		}
		return null;
	}

}
