package model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import control.Main;
import view.ID;

/**
 * Created by cuan on 9/15/15.
 *
 * The player class holds the data for each player including position,
 */

@XmlRootElement(name = "player")
// TODO: Bonnie added this line!
@XmlAccessorType(XmlAccessType.FIELD)
// TODO: Bonnie added this line!
public class Player extends Actor implements Serializable {
	@XmlTransient
	// TODO: Bonnie added this line!
	private int speed = 5;
	private Inventory inventory;
	private boolean hasKey;
	private boolean playerIsAttacking;
	private int attackPoints = 2;
	private int fear = 0; // if get too scared, you lose
	private boolean alive = true;
	private int bravery = 0;
	private int clientNum;

	/*
	 * @XmlTransient //TODO: Bonnie added this line! protected final
	 * GameController gameController;
	 */

	public Player(ID id, Position position, char asciiCode, boolean collidable,
			boolean drawable, int clientNum) {
		super(id, position, asciiCode, collidable, drawable);
		this.clientNum = clientNum;
		this.inventory = new Inventory(ID.CONTAINER, position, asciiCode,
				false, false);

		// ID id, Position position, char imagePath, boolean collidable,
		// boolean drawable, int boundingBoxSize, Collectable... collectables

	}

	@Override
	public void tick() {}

	/**
	 * This method receives a direction from the game controller, checks against
	 * the logic in the Collision class (which checks the proposed new location
	 * against the present locations of all collidable Actor objects), and moves
	 * of the logic allows it.
	 *
	 * @param gameState
	 * @param dir
	 */
	public void move(GameState gameState, String dir) {
		Collision collision = new Collision(gameState);
		Position proposedPosition;
		switch (dir) {
		case "UP":
			proposedPosition = new Position(getX(), getY() - speed, position.getBoundingBox());
			if (collision.canMove(this, proposedPosition)) {
				setPosition(proposedPosition);
			}
			break;
		case "DOWN":
			proposedPosition = new Position(getX(), getY() + speed, position.getBoundingBox());
			if (collision.canMove(this, proposedPosition)) {
				setPosition(proposedPosition);
			}
			break;
		case "LEFT":
			proposedPosition = new Position(getX() - speed, getY(),position.getBoundingBox());
			if (collision.canMove(this, proposedPosition)) {
				setPosition(proposedPosition);
			}
			break;
		case "RIGHT":
			proposedPosition = new Position(getX() + speed, getY(),position.getBoundingBox());
			if (collision.canMove(this, proposedPosition)) {
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
			Position pos = new Position(getPosition().getxPos()+50,getPosition().getyPos()+50);
			inventory.setPosition(pos);
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

	/**
	 * Setter for Player's fear level.
	 *
	 * @param fear
	 */
	public void setFear(int fear) {
		this.fear = fear;
		if (fear >= 1000) {
			tooScared();
		}
	}

	/**
	 * Getter for Player's fear level.
	 *
	 * @return
	 */
	public int getFear() {
		return fear;
	}

	/**
	 * This method increases the Player's fear level by the amount in the
	 * parameter.
	 *
	 * @param n
	 */
	public void increaseFear(int n) {
		fear += n;
		if (fear >= 150) {
			tooScared();
		}
	}

	/**
	 * When the Player's fear reaches the fear limit the Player dies and re-starts.
	 *
	 */
	private void tooScared() {
		this.alive = false;
		this.getInventory().empty();
		this.setFear(0);
		this.setPosition(new Position(Main.PLAYER_ONE_START_LOCATION_X, Main.PLAYER_ONE_START_LOCATION_Y));
	}

	// ========================================================
	// TODO: Bonnie added this!

	/**
	 * Getter for Player hasKey value.
	 *
	 * @return
	 */
	public boolean hasKey() {
		return hasKey;
	}

	/**
	 * Setter for Player hasKey value.
	 *
	 * @param hasKey
	 */
	public void setHasKey(boolean hasKey) {
		this.hasKey = hasKey;
	}

	/**
	 * Getter for Player attackPoints.
	 *
	 * @return
	 */
	public int getAttackPoints() {
		return attackPoints;
	}

	/**
	 * Setter for Player attack Points.
	 *
	 * @param attackPoints
	 */
	public void setAttackPoints(int attackPoints) {
		this.attackPoints = attackPoints;
	}

	/**
	 * Getter for Player alive status.
	 *
	 * @return
	 */
	public boolean isAlive() {
		return alive;
	}

	/**
	 * Setter for Player alive status.
	 *
	 * @param alive
	 */
	public void setAlive(boolean alive) {
		this.alive = alive;
	}

	/**
	 * Getter for Player Position x-element.
	 *
	 * @return Player Position x
	 */
	public int getX() {
		return this.getPosition().getxPos();
	}

	/**
	 * Getter for Player Position y-element.
	 *
	 * @return Player Position y
	 */
	public int getY() {
		return this.getPosition().getyPos();
	}

	/**
	 * Setter for Player bravery.
	 *
	 * @param bravery
	 */
	public void setBravery(int bravery) {
		this.bravery = bravery;
	}

	/**
	 * Getter for Player bravery.
	 *
	 * @return bravery
	 */
	public int getBravery() {
		return bravery;
	}

	/**
	 * Setter for Player clientNum
	 *
	 * @param clientNum
	 */
	public void setClientNum(int clientNum) {
		this.clientNum = clientNum;
	}

	/**
	 * Getter for Player Inventory
	 *
	 * @return
	 */
	public Inventory getInventory() {
		return this.inventory;
	}

	// ========================================================

}