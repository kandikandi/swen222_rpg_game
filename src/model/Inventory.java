package model;

import java.awt.Image;

public class Inventory extends Container {

	final private Player inventoryOwner;
	final int maximumItems = 9;

	/**
	 * Constructor with owning player added on construction. Also can insert
	 * items at same time.
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
	 * This method is used to check whether a player has a key when near a door.
	 *
	 * @return
	 */
	public boolean containsKey() {
		for (GameObject gameObject : items) {
			if (gameObject instanceof Key) {
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
		for (GameObject gameObject : items) {
			if (gameObject instanceof Key) {
				return (Key) gameObject;
			}
		}
		return null;
	}

	/**
	 * This method is used to check whether a player has a coinBag in the
	 * inventory.
	 *
	 * @return whether inventory has coin bag
	 */
	public boolean containsCoinBag() {
		for (GameObject gameObject : items) {
			if (gameObject instanceof CoinBag) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Returns CoinBag if there is one in this inventory.
	 *
	 * @return CoinBag in this Inventory.
	 */
	public CoinBag getCoingBag() {
		for (GameObject gameObject : items) {
			if (gameObject instanceof CoinBag) {
				return (CoinBag) gameObject;
			}
		}
		return null;
	}

	/**
	 * The CoinBag Container can be contained in an inventory.
	 *
	 * @param coinBag
	 *            to be added to inventory
	 */
	public boolean addItemToContainer(CoinBag coinBag) {
		if (coinBag == null) {
			return false;
		} else if (items.size() < maximumItems) {
			items.add(coinBag);
			addAllCoinsToCoinBag();
			return true;
		}else{
			return false;
		}
	}

	/**
	 * All coins in Inventory are added to CoinBag, eg if picked up prior to
	 * bag.
	 */
	private void addAllCoinsToCoinBag() {
		for (GameObject gameObject : items) {
			if (gameObject instanceof Coin) {
				getCoingBag().addItemToContainer((Coin) gameObject);
			}
		}

	}

	/**
	 * Return number of coins the player has in Inventory.
	 *
	 * @return
	 */
	public int getCoinCount() {
		if (containsCoinBag()) {
			return getCoingBag().numberOfCoinsInCoinBag();
		} else {
			int n = 0;
			for (GameObject gameObject : items) {
				if (gameObject instanceof Coin) {
					n++;
				}
			}
			return n;
		}
	}

	/**
	 * If a player picks up a coin, it will be added to the CoinBag if there is
	 * one in the inventory, otherwise it will just be added to the inventory.
	 *
	 * @param keyBag
	 */
	public boolean addItemToContainer(Coin coin) {
		if (coin == null) {
			return false;
		} else if (containsCoinBag()) {
			return getCoingBag().addItemToContainer(coin);
		} else if (items.size() < maximumItems) {
			return items.add(coin);
		} else{
			return false;
		}
	}

}