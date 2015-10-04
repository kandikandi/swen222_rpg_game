package model;

import control.DIR;
import control.GameController;
import control.Main;
import control.MovementStrategy;

import java.awt.*;
import java.util.ArrayList;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import save.player.AdaptedPlayer;
import save.player.PlayerAdapter;

/**
 * Created by cuan on 9/15/15.
 *
 * The player class holds the data for each player including position,
 */
//@XmlRootElement(name = "player") //TODO: Bonnie added this line!
//@XmlAccessorType(XmlAccessType.FIELD) //TODO: Bonnie added this line!
@XmlJavaTypeAdapter(PlayerAdapter.class)
public class Player extends ActorStrategy {
	@XmlTransient //TODO: Bonnie added this line!
	MovementStrategy movementStrategy;
	private final int speed = 5;
	private Inventory inventory;
	private boolean hasKey;
	private boolean playerIsAttacking;
	private int attackPoints = 2;
	private int fear = 0; // if get too scared, you lose
	private boolean alive = true;
	private int bravery = 0;

//	@XmlTransient //TODO: Bonnie added this line!
	protected final GameController gameController;

	public Player(ID id, Position position, Image image, boolean collidable,
				  boolean drawable, int boundingBoxSize, GameController gameController) {
		super(id, position, image, collidable, drawable, boundingBoxSize);
		this.gameController = gameController;

	}

	//====================================================//
	// TODO: Bonnie added these lines!
	public GameController getController(){
		return gameController;
	}
	// Bonnie ends here!
	//====================================================//

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
			if (canMove(DIR.UP)) {
				position.setyPos(position.getyPos() - speed);
			}
			break;
		case DOWN:
			if (canMove(DIR.DOWN)) {
				position.setyPos(position.getyPos() + speed);
			}
			break;
		case LEFT:
			if (canMove(DIR.LEFT)) {
				position.setxPos(position.getxPos() - speed);
			}
			break;
		case RIGHT:
			if (canMove(DIR.RIGHT)) {
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
		// bounding box of new position
		Rectangle newBoundingBox = new Rectangle(getProposedPosition(dir)
				.getxPos(), getProposedPosition(dir).getyPos(),
				Main.PLAYER_SIZE, Main.PLAYER_SIZE);

		// check bounding boxes against other GameObject objects' bounding boxes
		// have checkCollision method that returns null if no collision
		// otherwise returns actor you're colliding with
		Actor collidingObject = gameController.checkCollision(newBoundingBox);
		if (collidingObject == null) {
			return true;// there is no object at the new location, so can move
						// there.
		}

		// if we have an Actor at the new location, we need to effect the
		// impact
		collide(collidingObject);

		if (collidingObject instanceof Enemy) {
			return false;
		}

		// need to add logic for stuff we can't walk through
		return true;

	}

	/**
	 * If there is a collision, this method carries out the appropriate logic
	 * for the collision type.
	 *
	 *
	 * @param collidingObject
	 */
	private void collide(Actor collidingObject) {
		if (collidingObject instanceof Coin) {
			pickup((Coin) collidingObject);
		} else if (collidingObject instanceof Collectable) {
			pickup((Collectable) collidingObject);
		} else if (collidingObject instanceof CoinBag) {
			pickupCoinBag((CoinBag) collidingObject);
		} else if (collidingObject instanceof Door) {
			useKeyInDoor((Door) collidingObject);
		} else if (collidingObject instanceof Key) {
			pickup((Key) collidingObject);
		} else if (collidingObject instanceof Enemy) {
			fight(collidingObject);
		} else {

		}
	}

	private void fight(Actor collidingObject) {
		Enemy enemy = (Enemy) collidingObject;

		if (enemy.isAttacking() && !getAttacking()) {
			increaseFear(enemy.getAttackPoints() - bravery);
		} else if (!enemy.isAttacking() && getAttacking()) {// if enemy is not
															// attacking and you
															// are: you +
															// monster -
			enemy.reduceHealth(attackPoints + bravery);
		} else {
			enemy.reduceHealth(1);
			increaseFear(1);
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
	 * Setter for whether player attacking.
	 *
	 * @param playerIsAttacking
	 */
	@Override
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

}
