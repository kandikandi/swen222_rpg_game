package model;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import view.ID;

/**
 * An Inventory is a Container which a Player always has
 * in which game Collectable objects such as Coins and Keys
 * can be placed.
 *
 *
 */
@XmlRootElement(name = "inventory") //TODO: Bonnie added this line!
public class Inventory extends Container {

	@XmlTransient //TODO: Bonnie added this line!
	//final private Player inventoryOwner;
	final int maximumItems = 9;


	/**
	 * Constructor with owning player added on construction. Also can insert
	 * items at same time.
	 *
	 * @param id
	 * @param position
	 * @param imagePath
	 * @param collidable
	 * @param drawable
	 * @param collectables
	 */
	public Inventory(ID id, Position position, char imagePath, boolean collidable,
			boolean drawable, Collectable... collectables) {
		super(id, position, imagePath, false, false, collectables);
//		this.inventoryOwner = inventoryOwner;

	}

//	public Player getOwner() {
//		return inventoryOwner;
//	}

	/**
	 * Position needs to update with player, so items within also move with
	 * player.
	 *
	 */
//	@XmlTransient //TODO: Bonnie added this line!
//	@Override
//	public Position getPosition() {
//		return inventoryOwner.getPosition();
//	}

	/**
	 * This method is used to check whether a player has a key when near a door.
	 *
	 * @return
	 */
	public boolean containsKey() {
		for (Actor actor : items) {
			if (actor instanceof Key) {
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
		for (Actor actor : items) {
			if (actor instanceof Key) {
				return (Key) actor;
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
		for (Actor actor : items) {
			if (actor instanceof CoinBag) {
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
	public CoinBag getCoinBag() {
		for (Actor actor : items) {
			if (actor instanceof CoinBag) {
				return (CoinBag) actor;
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
	public void addItemToContainer(CoinBag coinBag) {
		if (coinBag == null) {
			return;
		} else if (items.size() < maximumItems) {
			items.add(coinBag);
			coinBag.setCollidable(false);
			coinBag.setDrawable(false);
			addAllCoinsToCoinBag(coinBag);
			return;
		} else if (items.size() == maximumItems) {
			for(Actor actor : items){
				if(actor instanceof Coin){
					coinBag.addItemToContainer((Coin) actor);
				}
			}
			items.add(coinBag);
			coinBag.setCollidable(false);
			coinBag.setDrawable(false);
			addAllCoinsToCoinBag(coinBag);
			return;
		}else{
			return;
		}
	}

	/**
	 * All coins in Inventory are added to CoinBag, eg if picked up prior to
	 * bag.
	 */
	private void addAllCoinsToCoinBag(CoinBag coinBag) {
		if(getCoinBag()==null){return;}
		for (Actor actor : items) {
			if (actor instanceof Coin) {
				Coin coin = (Coin) actor;
				coinBag.addItemToContainer(coin);
				coin.setCollidable(false);
				coin.setDrawable(false);
			}
		}
		for(Actor actor : coinBag.items){
			Coin coin = (Coin) actor;
			this.removeItemFromContainer(coin);
			coin.setDrawable(false);
			coin.setCollidable(false);
		}
	}

	/**
	 * Return number of coins the player has in Inventory.
	 *
	 * @return
	 */
	public int getCoinCount() {
		if (containsCoinBag()) {
			return getCoinBag().numberOfCoinsInCoinBag();
		} else {
			int n = 0;
			for (Actor actor : items) {
				if (actor instanceof Coin) {
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
	 */
	public void addItemToContainer(Collectable collectable) {
		if (collectable == null) {
			return;
		}else if(collectable instanceof Coin && containsCoinBag()){
				getCoinBag().addItemToContainer((Coin) collectable);
		} else if (items.size() < maximumItems) {
			items.add(collectable);
			collectable.setCollidable(false);
			collectable.setDrawable(false);
		}
		addAllCoinsToCoinBag(getCoinBag());
	}

}
