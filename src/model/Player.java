package model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import view.ID;

/**
 * Created by cuan on 9/15/15.
 *
 * The player class holds the data for each player including position,
 */


@XmlRootElement(name = "player") //TODO: Bonnie added this line!
@XmlAccessorType(XmlAccessType.FIELD) //TODO: Bonnie added this line!
public class Player extends Actor implements Serializable {
	@XmlTransient //TODO: Bonnie added this line!

	private int speed = 5;
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
	public Inventory getInventorytooScared() {
		return inventory;
	}

	/**
	 * Getter for client number
	 *
	 * @return
	 */
	public int getClientNum() {
		return clientNum;
	}

	/*
	 * Setter for fear level.
	 */
	public void setFear(int fear) {
		this.fear = fear;
		if (fear >= 1000) {
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
		if (fear >= 1000) {
			tooScared();
		}
	}

	/**
	 * Deals with when fear reaches 100 and the Player loses.
	 *
	 */
	private void tooScared() {
		// game over for player
		this.alive = false;
		this.speed = 0;
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


	public Inventory getInventory() {
		return this.inventory;
	}

	//========================================================

}