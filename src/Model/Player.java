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
	private Inventory inventory;
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
		this.inventory = new Inventory(ID.CONTAINER,position, asciiCode, false, false, 1);

//		ID id, Position position, char imagePath, boolean collidable,
//		boolean drawable, int boundingBoxSize, Collectable... collectables

	}


	@Override
	public void tick() {
		//movementStrategy.executeMove();
	}


	public void move(GameState gameState, String dir) {
		Collision collision = new Collision(gameState);
		Position proposedPosition;
//		Position proposedPosition2;
		switch (dir) {

		case "UP":
			proposedPosition = new Position(getX(), getY()-speed);
//			proposedPosition2 = new Position(getX(), getY()-2*speed);
			if(collision.canMove(this, proposedPosition)){
				setPosition(proposedPosition);
			}
			break;
		case "DOWN":
			proposedPosition = new Position(getX(), getY()+speed);
//			proposedPosition2 = new Position(getX(), getY()+2*speed);
			if(collision.canMove(this, proposedPosition)){
				setPosition(proposedPosition);
			}
			break;
		case "LEFT":
			proposedPosition = new Position(getX()-speed, getY());
//			proposedPosition2 = new Position(getX(), getY()-2*speed);
			if(collision.canMove(this, proposedPosition)){
				setPosition(proposedPosition);
			}
			break;
		case "RIGHT":
			proposedPosition = new Position(getX()+speed, getY());
			if(collision.canMove(this, proposedPosition)){
				setPosition(proposedPosition);
			}
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

//
//
//	public void setInventory(Inventory inventory) {
//		this.inventory = inventory;
//		inventory.setPosition(position);
//	}

	/**
	 * Setter for whether player attacking.
	 *
	 * @param playerIsAttacking
	 */
	public void setAttack(boolean playerIsAttacking) {
		this.playerIsAttacking = playerIsAttacking;
	}

	/**
	 * Getter for whether player attacking.
	 *
	 * @return
	 */
	public boolean getAttacking() {
		return playerIsAttacking;
	}
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
	/**
	 * Getter method to return Inventory
	 *
	 * @return
	 */
	public Inventory getInventory() {
		return inventory;
	}

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

	public int getX(){
		return this.getPosition().getxPos();
	}

	public int getY(){
		return this.getPosition().getyPos();
	}

	public void setBravery(int bravery) {
		this.bravery = bravery;
	}


	public void setClientNum(int clientNum) {
		this.clientNum = clientNum;
	}

	//========================================================

}
