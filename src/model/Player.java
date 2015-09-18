package model;

import control.DIR;
import control.MovementStrategy;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by cuan on 9/15/15.
 */
public class Player extends ActorStrategy {
	MovementStrategy movementStrategy;
	private final int speed = 5;
	private Inventory inventory;

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
			position.setyPos(position.getyPos() - speed);
			break;
		case DOWN:
			position.setyPos(position.getyPos() + speed);
			break;
		case LEFT:
			position.setxPos(position.getxPos() - speed);
			break;
		case RIGHT:
			position.setxPos(position.getxPos() + speed);
			break;
		}
	}

	/**
	 * This method checks whether the proposed nes position for the player is valid.
	 *
	 *
	 */
	@Override
    public boolean canMove(DIR dir) {

		//////
		// this logic might need to get moved to controller at some point
		/////////

		//bounding box of new position
        Rectangle newBoundingBox = new Rectangle(getProposedPosition(dir).getxPos(),getProposedPosition(dir).getyPos());

        //check bounding boxes against other GameObject objects' bounding boxes
        //have checkCollision method that returns null if no collision otherwise returns gameobject you're colliding with
        GameObject collidingObject = GameState.checkCollision(newBoundingBox);
        if(collidingObject==null){ return true; } // there is no object at the new location, so can move there.

        // if we have a GameObject at the new location, we need to check the results of impacting that type of object.
        return checkCollisionResult(this,collidingObject);

    }



	private boolean checkCollisionResult(Player player,
			GameObject collidingObject) {


		// CASES : 	1. Collectable -> not collidable. add to inventory-> ret TRUE
		//			2. Coin -> not collidable -> score -> ret TRUE
		//			3. "Wall" -> nothing really happens, just return false;
		//			4. Trap(?) -> not collidable -> bad for health/
		//			5. Enemy 	-> can't move there -> return false
		//						-> depends on whether are holding space ie attacking
		//						-> if not attacking, bad for health
		//						-> if attacking, bad for enemy health
		//			6. Door (?) -> do you have a key, if yes-> return true


		if(collidingObject instanceof Collectable){ // will change to using ID instead of instance of
			pickup(((Collectable) collidingObject));
			return true;
		}


		return true;
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
	public void pickup(Collectable collectable) {
		if (collectable == null) {
			return;
		}
		if (inventory == null) {
			System.out.println("Error: Inventory not set up");
			return;
		}
		inventory.addItemToContainer(collectable);
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
		}
		if (inventory == null) {
			return;
		}
		inventory.removeItemFromContainer(collectable);
	}

	/**
	 * Getter method to return Inventory
	 *
	 * @return
	 */
	public Inventory getInventory() {
		return inventory;
	}

	public Position getProposedPosition(DIR dir) {
		Position newPosition = new Position(getPosition().getxPos(), getPosition().getyPos());
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

}
