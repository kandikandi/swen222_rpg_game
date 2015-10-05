package Model;

import java.awt.*;
import java.io.Serializable;
import java.net.InetAddress;
import java.util.ArrayList;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import View.ID;

/**
 * Created by cuan on 9/15/15.
 *
 * The player class holds the data for each player including position,
 */


@XmlRootElement(name = "player") //TODO: Bonnie added this line!
@XmlAccessorType(XmlAccessType.FIELD) //TODO: Bonnie added this line!
public class Player extends Actor implements Serializable {
	@XmlTransient //TODO: Bonnie added this line!

	private final int speed = 5;
	//private Inventory inventory;
	private boolean hasKey;
	private boolean playerIsAttacking;
	private int attackPoints = 2;
	private int fear = 0; // if get too scared, you lose
	private boolean alive = true;
	private int bravery = 0;
	private int clientNum;

	/*@XmlTransient //TODO: Bonnie added this line!
	protected final GameController gameController;*/

	public Player(ID id, Position position, char asciiCode, boolean collidable,
				  boolean drawable, int boundingBoxSize, int clientNum ) {
		super(id, position, asciiCode, collidable, drawable, boundingBoxSize);
		this.clientNum = clientNum;

	}


	@Override
	public void tick() {
		//movementStrategy.executeMove();
	}


	public void move(String dir) {
		switch (dir) {
		case "UP":
				position.setyPos(position.getyPos() - speed);
			break;
		case "DOWN":
				position.setyPos(position.getyPos() + speed);
			break;
		case "LEFT":
				position.setxPos(position.getxPos() - speed);
			break;
		case "RIGHT":
				position.setxPos(position.getxPos() + speed);
			break;
		}
	}

//	/**
//	 * If there is a collision, this method carries out the appropriate logic
//	 * for the collision type.
//	 *
//	 *
//	 * @param collidingObject
//	 */
//	protected void collide(Actor collidingObject) {
//		if (collidingObject instanceof Coin) {
//			pickup((Coin) collidingObject);
//		} else if (collidingObject instanceof Collectable) {
//			pickup((Collectable) collidingObject);
//		} else if (collidingObject instanceof CoinBag) {
//			pickupCoinBag((CoinBag) collidingObject);
//		} else if (collidingObject instanceof Door) {
//			useKeyInDoor((Door) collidingObject);
//		} else if (collidingObject instanceof Key) {
//			pickup((Key) collidingObject);
//		} else if (collidingObject instanceof Enemy) {
//			fight(collidingObject);
//			super.isMoving = false;
//		}
//	}
//
//	private void fight(Actor collidingObject) {
//		Enemy enemy = (Enemy) collidingObject;
//
//		if (enemy.isAttacking() && !getAttacking()) {
//			increaseFear(enemy.getAttackPoints() - bravery);
//		} else if (!enemy.isAttacking() && getAttacking()) {// if enemy is not
//															// attacking and you
//															// are: you +
//															// monster -
//			enemy.reduceHealth(attackPoints + bravery);
//		} else {
//			enemy.reduceHealth(1);
//			increaseFear(1);
//		}
//	}
//
//
//	public void setInventory(Inventory inventory) {
//		this.inventory = inventory;
//		inventory.setPosition(position);
//	}
//
//	/**
//	 * This method adds a collectable to the inventory.
//	 * TODO: if pickup is being called with a null, a exception should be thrown
//	 * @param collectable
//	 */
//	public boolean pickup(Collectable collectable) {
//		if (collectable == null) {
//			return false;
//		} else if (inventory == null) {
//			System.out.println("Error: Inventory not set up"); //TODO: throw exception here aswell
//			return false;
//		} else {
//			return inventory.addItemToContainer(collectable);
//		}
//	}
//
//	/**
//	 * This method adds a CoinBag to the inventory, if the player has an
//	 * inventory.
//	 *
//	 * @param
//	 */
//	public boolean pickupCoinBag(CoinBag coinBag) {
//		if (coinBag == null) {
//			return false;
//		} else if (inventory == null) {
//			System.out.println("Error: Inventory not set up");
//			return false;
//		} else {
//			return inventory.addItemToContainer(coinBag);
//		}
//	}
//
//	/**
//	 * Setter for whether player attacking.
//	 *
//	 * @param playerIsAttacking
//	 */
//	@Override
//	public void setAttack(boolean playerIsAttacking) {
//		this.playerIsAttacking = playerIsAttacking;
//	}
//
//
//	@Override
//	public void setMoveStrat(MovementStrategy movementStrategy) {
//		// TODO Auto-generated method stub
//
//	}
//
//	/**
//	 * Getter for whether player attacking.
//	 *
//	 * @return
//	 */
//
//	public boolean getAttacking() {
//		return playerIsAttacking;
//	}
//
//	/**
//	 * This method removes a Collectable from the Inventory and places it where
//	 * the Player is standing.
//	 *
//	 * @param collectable
//	 */
//	public void drop(Collectable collectable) {
//		if (collectable == null) {
//			return;
//		} else if (inventory == null) {
//			return;
//		} else {
//			inventory.setPosition(position);
//			inventory.removeItemFromContainer(collectable);
//		}
//	}
//
//	/**
//	 * Getter method to return Inventory
//	 *
//	 * @return
//	 */
//	public Inventory getInventory() {
//		return inventory;
//	}

//	/**
//	 * This method returns a position based on the direction of the arrow key
//	 * pushed by the user.
//	 *
//	 * @param dir
//	 * @return
//	 */
//	public Position getProposedPosition(DIR dir) {
//		Position newPosition = new Position(getPosition().getxPos(),
//				getPosition().getyPos());
//		switch (dir) {
//		case UP:
//			newPosition.setyPos(position.getyPos() - speed);
//			break;
//		case DOWN:
//			newPosition.setyPos(position.getyPos() + speed);
//			break;
//		case LEFT:
//			newPosition.setxPos(position.getxPos() - speed);
//			break;
//		case RIGHT:
//			newPosition.setxPos(position.getxPos() + speed);
//			break;
//		}
//		return newPosition;
//
//	}
//
//	/**
//	 * If a player collides with a Door object, if the player has a key the door
//	 * will open and the key will be used and disappear from the player's
//	 * inventory.
//	 *
//	 * @param door
//	 * @return true if the player has a key
//	 */
//	public boolean useKeyInDoor(Door door) {
//		if (inventory.containsKey()) {
//			door.open();
//			Key key = inventory.getKey();
//			key.useInDoor();
//			inventory.removeItemFromContainer(key);
//			return true;
//		}
//		return false;
//	}


	public int getClientNum() {
		return clientNum;
	}

	/*
	 * Setter for fear level.
	 */
	public void setFear(int fear) {
		this.fear = fear;
		if (fear >= 100) {
			tooScared();
		}
	}

	/*
	 * Getter for fear level.
	 */
	public int getFear() {
		return fear;
	}

	/**
	 *
	 *
	 * @param n
	 */
	public void increaseFear(int n) {
		fear += n;
		if (fear >= 100) {
			tooScared();
		}
	}

	/**
	 * Deals with when fear reaches 100 and the Player loses.
	 *
	 */
	private void tooScared() {
		// game over for player
		alive = false;
		// if only one player left, game over
		// tell the controller it's all over

	}

	//========================================================
	// TODO: Bonnie added this!

	public boolean hasKey() {
		return hasKey;
	}


	public void setHasKey(boolean hasKey) {
		this.hasKey = hasKey;
	}


	public int getAttackPoints() {
		return attackPoints;
	}


	public void setAttackPoints(int attackPoints) {
		this.attackPoints = attackPoints;
	}


	public boolean isAlive() {
		return alive;
	}


	public void setAlive(boolean alive) {
		this.alive = alive;
	}


	public int getBravery() {
		return bravery;
	}


	public void setBravery(int bravery) {
		this.bravery = bravery;
	}


	public void setClientNum(int clientNum) {
		this.clientNum = clientNum;
	}

	//========================================================

}