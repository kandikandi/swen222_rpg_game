package model;

import control.DIR;
import control.MovementStrategy;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by cuan on 9/15/15.
 *
 * The player class holds the data for each player including position,
 */
public class Player extends ActorStrategy {
	MovementStrategy movementStrategy;
	private final int speed = 5;
	private Inventory inventory;
	private boolean hasKey;
	private int fear; // if get too scared, you lose
	private boolean stillInGame = true;
//	private int courage; // as you get more experience etc, the impact of fights
							// etc will be less.

	public Player(ID id, Position position, Image image, boolean collidable,
			boolean drawable, int boundingBoxSize) {
		super(id, position, image, collidable, drawable, boundingBoxSize);

	}

	@Override
	public void setMoveStrat(MovementStrategy moveStrat) {
		this.movementStrategy = moveStrat;
		moveStrat.setActor(this);
	}

	@Override
	public void tick() {
		movementStrategy.executeMove();
	}

	@Override
	public void move(DIR dir) {
		switch (dir) {
		case UP:
			if(canMove(DIR.UP)){
			position.setyPos(position.getyPos() - speed);
			}
			break;
		case DOWN:
			if(canMove(DIR.DOWN)){
			position.setyPos(position.getyPos() + speed);
			}
			break;
		case LEFT:
			if(canMove(DIR.LEFT)){
			position.setxPos(position.getxPos() - speed);
			}
			break;
		case RIGHT:
			if(canMove(DIR.RIGHT)){
			position.setxPos(position.getxPos() + speed);
			}
			break;
		}
	}

	/**
	 * This method checks whether the proposed new position for the player is
	 * valid.
	 *
	 *
	 */
	@Override
	public boolean canMove(DIR dir) {

		System.out.println("CanMove? "+dir);

		// ////
		// this logic might need to get moved to controller at some point
		// ///////

		// bounding box of new position
		Rectangle newBoundingBox = new Rectangle(getProposedPosition(dir)
				.getxPos(), getProposedPosition(dir).getyPos());

		// check bounding boxes against other GameObject objects' bounding boxes
		// have checkCollision method that returns null if no collision
		// otherwise returns gameobject you're colliding with
		GameObject collidingObject = GameState.checkCollision(newBoundingBox);
		if (collidingObject == null) {
			return true;// there is no object at the new location, so can move
						// there.
		}

		// if we have a GameObject at the new location, we need to effect the
		// impact
		collide(collidingObject);

		return collidingObject.isCollidable();

	}

	private void collide(GameObject collidingObject) {

		// 2. Coin -> not collidable -> score -> ret TRUE
		// 3. "Wall" -> nothing really happens, just return false;
		// 4. Trap(?) -> not collidable -> bad for health/
		// 5. Enemy -> can't move there -> return false
		// -> depends on whether are holding space ie attacking
		// -> if not attacking, bad for health
		// -> if attacking, bad for enemy health
		// 6. Door (?) -> do you have a key, if yes-> return true

		// CASES : 1. Collectable -> not collidable. add to inventory-> ret TRUE

		if (collidingObject instanceof Coin) {
			pickup((Coin) collidingObject);
		} else if (collidingObject instanceof Collectable) { // will change to
																// using ID
			// instead of instance
			// of
			pickup((Collectable) collidingObject);
		} else if (collidingObject instanceof CoinBag) {
			pickupCoinBag((CoinBag) collidingObject);
		} else if (collidingObject instanceof Door) {
			useKeyInDoor((Door) collidingObject);
		} else if (collidingObject instanceof Enemy) {
			// ///////////
			return;
		} else {

		}
	}

	@Override
	public MovementStrategy getMoveStrat() {
		return movementStrategy;
	}

	public void setInventory(Inventory inventory) {
		this.inventory = inventory;
		inventory.setPosition(position);
	}

	/**
	 * This method adds a collectable to the inventory.
	 *
	 * @param collectable
	 */
	public boolean pickup(Collectable collectable) {
		if (collectable == null) {
			return false;
		} else if (inventory == null) {
			System.out.println("Error: Inventory not set up");
			return false;
		} else {
			return inventory.addItemToContainer(collectable);
		}
	}

	/**
	 * This method adds a CoinBag to the inventory, if the player has an
	 * inventory.
	 *
	 * @param
	 */
	public boolean pickupCoinBag(CoinBag coinBag) {
		if (coinBag == null) {
			return false;
		} else if (inventory == null) {
			System.out.println("Error: Inventory not set up");
			return false;
		} else {
			return inventory.addItemToContainer(coinBag);
		}
	}

	/**
	 * This method removes a Collectable from the Inventory and places it where
	 * the Player is standing.
	 *
	 * @param collectable
	 */
	public void drop(Collectable collectable) {
		if (collectable == null) {
			return;
		} else if (inventory == null) {
			return;
		} else {
			inventory.setPosition(position);
			inventory.removeItemFromContainer(collectable);
		}
	}

	/**
	 * Getter method to return Inventory
	 *
	 * @return
	 */
	public Inventory getInventory() {
		return inventory;
	}

	/**
	 * This method returns a position based on the direction of the arrow key
	 * pushed by the user.
	 *
	 * @param dir
	 * @return
	 */
	public Position getProposedPosition(DIR dir) {
		Position newPosition = new Position(getPosition().getxPos(),
				getPosition().getyPos());
		switch (dir) {
		case UP:
			newPosition.setyPos(position.getyPos() - speed);
			break;
		case DOWN:
			newPosition.setyPos(position.getyPos() + speed);
			break;
		case LEFT:
			newPosition.setxPos(position.getxPos() - speed);
			break;
		case RIGHT:
			newPosition.setxPos(position.getxPos() + speed);
			break;
		}
		return newPosition;

	}

	/**
	 * If a player collides with a Door object, if the player has a key the door
	 * will open and the key will be used and disappear from the player's
	 * inventory.
	 *
	 * @param door
	 * @return true if the player has a key
	 */
	public boolean useKeyInDoor(Door door) {
		if (inventory.containsKey()) {
			door.open();
			Key key = inventory.getKey();
			key.useInDoor();
			inventory.removeItemFromContainer(key);
			return true;
		}
		return false;
	}

	/*
	 * Setter for fear level.
	 */
	public void setFear(int fear) {
		this.fear = fear;
		if(fear>=100){
			tooScared();
		}
	}

	public void increaseFear() {
		fear += 5;
		if(fear>=100){
			tooScared();
		}
	}

	public void increaseFear(int n) {
		fear += n;
		if(fear>=100){
			tooScared();
		}
	}

	/**
	 * Deals with when fear reaches 100 and the Player loses etc.
	 *
	 */
	private void tooScared() {
		// game over for player
		stillInGame = false;
		// if only one player left, game over
		//tell the controller it's all over


	}

	/*
	 * Getter for fear level.
	 */
	public int getFear() {
		return fear;
	}

	// /*
	// * Setter for courage level.
	// */
	// public void setCourage(int courage) {
	// this.courage = courage;
	// }
	//
	// /*
	// * Setter for courage level.
	// */
	//
	// public int getCourage() {
	// return courage;
	// }

}
