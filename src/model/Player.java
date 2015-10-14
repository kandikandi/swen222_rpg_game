package model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import control.Main;
import save.actors.PlayerAdapter;
import view.ActorAssets;


/**
 * Created by cuan on 9/15/15.
 *
 * The player class holds the data for each player including position,
 */

//@XmlRootElement(name = "player")
@XmlAccessorType(XmlAccessType.FIELD)
//@XmlJavaTypeAdapter(PlayerAdapter.class)
public class Player extends Actor implements Serializable {
	@XmlTransient
	private int speed = 10;
	private Inventory inventory;
	private boolean hasKey;
	@XmlTransient
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

	public Player(Position position, char asciiCode, boolean collidable,
				  boolean drawable, int clientNum) {
		super(position, asciiCode, collidable, drawable);
		this.clientNum = clientNum;
		this.inventory = new Inventory(position, asciiCode,false, false);
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
		case "SPACEDOWN":
			playerIsAttacking = true;
			//System.out.println("Player: attacking "+ playerIsAttacking);
			break;
		case "SPACEUP":
			playerIsAttacking = false;
			//System.out.println("Player: attacking "+ playerIsAttacking);
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
	 * @param actor
	 */
	public void drop(Actor actor) {

		if (actor == null) {
			return;
		} else if (inventory == null) {
			return;
		} else {
			inventory.setPosition(this.getPosition());
			Position pos = new Position(getPosition().getxPos()+35,getPosition().getyPos()+35); // drops off item slightly away fro player
        	actor.setPosition(pos); //update position
            actor.setCollidable(true);
            actor.setDrawable(true);
			inventory.removeItemFromContainer(actor);

		}

	}


	public void dropItemID(ActorAssets actorAsset) {
//		boolean itemDropped = false;
			for(int i = 0; i < this.getInventory().returnContents().size(); i++){
//				if(!itemDropped){

					if(this.getInventory().returnContents().get(i).getAsciiCode()==actorAsset.getAsciiCode()){
						drop(this.getInventory().returnContents().get(i));
//						itemDropped = true;
						return;

//					}
				}
			}
	}


//	/**
//	 * Getter method to return Inventory
//	 *
//	 * @return
//	 */
//	public Inventory getInventorytooScared() {
//		return inventory;
//	}

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
		if (fear >= 150) {
			tooScared();
		}
	}





	public void eatCandy(){
		System.out.println("Here");
		if(inventory.containsCandy()){
			inventory.eatCandy();
			if(fear>30){
				fear-=30;
			}else{
				fear = 0;
			}
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
	 * When the Player's fear reaches the fear limit the Player re-starts at start point.
	 *
	 */
	private void tooScared() {
		this.setFear(0);
		Position originalPosition = new Position(Main.PLAYER_ONE_START_LOCATION_X,Main.PLAYER_ONE_START_LOCATION_X,position.getBoundingBox());
		this.setPosition(originalPosition);
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

	public void setInventory(Inventory inventory) {
		this.inventory = inventory;
	}



	// ========================================================

}