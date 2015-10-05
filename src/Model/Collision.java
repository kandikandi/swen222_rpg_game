package Model;

import java.awt.*;
import java.util.List;

/**
 * Created by cuan on 9/30/15.
 *
 * This class will be used to check collisions between actors
 */
public class Collision {

	// private final GameController gameController;

	private final GameState gameState;

	public Collision(GameState gameState) {
		this.gameState = gameState;
	}

	public boolean canMove(Player player) {
		Actor collidingActor = gameState.getColliding(player); // gets actor
																// colliding
																// with from
																// game state
		if (collidingActor == null) {
			return true;
		} else if (collidingActor instanceof Player) {
			return true;
		} else if (collidingActor instanceof Enemy) {
			enemyCollision(player, collidingActor);
			return false;
		} else if (collidingActor instanceof Coin) {
			pickup(player, (Coin) collidingActor);
			return true;
		} else if (collidingActor instanceof Collectable) {
			pickup(player, (Collectable) collidingActor);
			return true;
		} else if (collidingActor instanceof Door) {
			return useKeyInDoor(player, (Door) collidingActor);
		} else if (collidingActor instanceof CoinBag) {
			pickupCoinBag(player, (CoinBag) collidingActor);
		}
		return false;
	}

	private void enemyCollision(Player player, Actor collidingActor) {
		Enemy enemy = (Enemy) collidingActor;
		if (enemy.isAttacking() && !player.getAttacking()) {
			player.increaseFear(enemy.getAttackPoints() - player.getBravery());
		} else if (!enemy.isAttacking() && player.getAttacking()) {// if enemy
																	// is not
			// attacking and you
			// are: you +
			// monster -
			enemy.reduceHealth(player.getAttackPoints() + player.getBravery());
		} else { // if neither is attacking both lose a little
			enemy.reduceHealth(1);
			player.increaseFear(1);
		}

	}

	/**
	 * If a player collides with a Door object, if the player has a key the door
	 * will open and the key will be used and disappear from the player's
	 * inventory.
	 *
	 * @param door
	 * @return true if the player has a key
	 */
	public boolean useKeyInDoor(Player player, Door door) {
		if (player.getInventory().containsKey()) {
			door.open();
			Key key = player.getInventory().getKey();
			key.useInDoor();
			player.getInventory().removeItemFromContainer(key);
			return true;
		}
		return false;
	}

	/**
	 * This method adds a collectable to the inventory. TODO: if pickup is being
	 * called with a null, a exception should be thrown
	 *
	 * @param collectable
	 */
	public boolean pickup(Player player, Collectable collectable) {
		if (collectable == null) {
			return false;
		} else if (player.getInventory() == null) {
			System.out.println("Error: Inventory not set up"); // TODO: throw
																// exception
																// here aswell
			return false;
		} else {
			return player.getInventory().addItemToContainer(collectable);
		}
	}

	/**
	 * This method adds a CoinBag to the inventory, if the player has an
	 * inventory.
	 *
	 * @param
	 */
	public boolean pickupCoinBag(Player player, CoinBag coinBag) {
		if (coinBag == null) {
			return false;
		} else if (player.getInventory() == null) {
			System.out.println("Error: Inventory not set up");
			return false;
		} else {
			return player.getInventory().addItemToContainer(coinBag);
		}
	}

	//
	// for (int i = 1; i < actors.size(); i++) {
	// Actor actorA = actors.get(i);
	// Rectangle bBoxA = actorA.getBoundingBox();
	// if (actorA instanceof ActorStrategy) {
	// Position pos = getProposedPosition((ActorStrategy) actorA);
	// bBoxA = new Rectangle(pos.getxPos(), pos.getyPos(),
	// actorA.boundingBoxSize, actorA.boundingBoxSize);
	// }
	//
	//
	// for (int j = i+1; j < actors.size(); j++) {
	// Actor actorB = actors.get(j);
	// Rectangle bBoxB = actorB.getBoundingBox();
	// if (actorB instanceof ActorStrategy) {
	// Position pos = getProposedPosition((ActorStrategy)actorB);
	// bBoxB = new Rectangle(pos.getxPos(), pos.getyPos(),
	// actorB.boundingBoxSize, actorB.boundingBoxSize);
	// }
	//
	// if (bBoxA.intersects(bBoxB) || bBoxB.intersects(bBoxA)) {
	// //System.out.println("SCollision: "+actorA.id + ": " + i + "  " +
	// actorB.getID() + ": " + j);
	// if(actorA.isCollidable() && actorB.isCollidable()){
	// actorA.collide(actorB);
	// actorB.collide(actorA);
	// //TODO: work out how to best remove collectable-actor from GameState
	// actor list
	// }
	// }
	// }
	// }
	// }

	// /**
	// * This method returns a position based on the instantaneous direction
	// */
	// public Position getProposedPosition(ActorStrategy actor) {
	//
	// if (!actor.isMoving()) {
	// //System.out.println("SCollision: StratAct "+actor.getID()+": not moving ---------");
	// return actor.getPosition();
	// }
	// //System.out.println("SCollision: StratAct "+actor.getID()+": moving ++++++++++");
	// Position newPosition = new Position(actor.getPosition().getxPos(),
	// actor.getPosition().getyPos());
	// switch (actor.getDirection()) {
	// case UP:
	// newPosition.setyPos(actor.getPosition().getyPos() - actor.getSpeed());
	// break;
	// case DOWN:
	// newPosition.setyPos(actor.getPosition().getyPos() + actor.getSpeed());
	// break;
	// case LEFT:
	// newPosition.setxPos(actor.getPosition().getxPos() - actor.getSpeed());
	// break;
	// case RIGHT:
	// newPosition.setxPos(actor.getPosition().getxPos() + actor.getSpeed());
	// break;
	// }
	// return newPosition;
	//
	// }

}