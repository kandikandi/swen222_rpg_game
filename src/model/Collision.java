package model;

/**
 * Created by cuan on 9/30/15.
 *
 * This class will be used to check collisions between actors.
 */
public class Collision {

	private final GameState gameState;

	public Collision(GameState gameState) {
		this.gameState = gameState;
	}

	/**
	 * This method is called by a Player object that has been called on to move.
	 *
	 * The method takes the proposed new position for the player, checks with
	 * the GameState class which holds the list of active Actors in the game.
	 * The GameState object will returns the first collidable non-player actor
	 * in that position if there is one.
	 *
	 * This method then handles the appropriate action depending on the type of
	 * object the Player is colliding with.
	 *
	 * @param player
	 * @param proposedPosition
	 * @return
	 */
	public boolean canMove(Player player, Position proposedPosition) {
		// First we get the (first) colliding actor from gameState.
		Actor collidingActor = gameState.playerCollisionCheck(proposedPosition);

		// We process the collision based on the type of Actor we are colliding
		// with.
		if (collidingActor == null) {
			return true;
		} else if (collidingActor instanceof Player) {
			return true;
		} else if (collidingActor instanceof Enemy) {
			enemyCollision(player, collidingActor);
			return true;
		} else if (collidingActor instanceof Coin) {
			pickup(player, (Coin) collidingActor);
			return true;
		} else if (collidingActor instanceof Collectable) {
			pickup(player, (Collectable) collidingActor);
			return true;
		} else if (collidingActor instanceof Door) {
			useKeyInDoor(player, (Door) collidingActor);
			return false;
		} else if (collidingActor instanceof CoinBag) {
			pickupCoinBag(player, (CoinBag) collidingActor);
		}
		return false;
	}

	/**
	 * This method is called by an Enemy object that has been called on to move
	 * (ie every tick of the game).
	 *
	 * The method takes the proposed new position for the enemyr, checks with
	 * the GameState class which holds the list of active Actors in the game.
	 * The GameState object will returns the first relevant collidable
	 * non-player actor in that position if there is one.
	 *
	 * This method then handles the appropriate action depending on the type of
	 * object the Enemy is colliding with.
	 *
	 * @param player
	 * @param proposedPosition
	 * @return
	 */
	public boolean canMoveEnemy(Enemy enemy, Position proposedPosition) {
		Actor collidingActor = gameState.enemyCollisionCheck(proposedPosition);
		if (collidingActor == null) {
			return true;
		} else if (collidingActor instanceof Player) {
			enemyCollision((Player) collidingActor, enemy);
			return true;
		} else if (collidingActor instanceof Enemy) {
			return false;
		} else if (collidingActor instanceof Coin) {
			return true;
		} else if (collidingActor instanceof Door) {
			return false;
		} else if (collidingActor instanceof Wall) {
			return false;
		}
		return true;
	}

	/**
	 * This method processes a collision where either a Player collides into an
	 * Enemy or vice versa. The outcome varies depending on the attacking states
	 * of each actor.
	 *
	 * @param player
	 * @param collidingActor
	 */
	private void enemyCollision(Player player, Actor collidingActor) {
		Enemy enemy = (Enemy) collidingActor;
		if (enemy.isAttacking() && !player.getAttacking()) {
			player.increaseFear(enemy.getAttackPoints() - player.getBravery());
		} else if (!enemy.isAttacking() && player.getAttacking()) {
			enemy.reduceHealth(player.getAttackPoints() + player.getBravery());
		} else {
			enemy.reduceHealth(1);
			player.increaseFear(1);
		}

	}

	/**
	 * If a player collides with a Door object, if the player has a key the door
	 * will open and the key will be used and disappear from the player's
	 * inventory.
	 *
	 * If the door is a special door, a special key is required to open it.
	 *
	 * @param door
	 * @return true if the player has a key
	 */
	public void useKeyInDoor(Player player, Door door) {
		if (door.isSpecial()) { // test for special door
			if (player.getInventory().containsSpecialKey()) {
				door.setOpen();
				Key key = player.getInventory().getSpecialKey();
				player.getInventory().removeItemFromContainer(key);
				key.setCollidable(false);
				key.setDrawable(false);
				key = null;
			}
		} else {
			if (player.getInventory().containsNormalKey()) {
				door.setOpen();
				Key key = player.getInventory().getNormalKey();
				player.getInventory().removeItemFromContainer(key);
				key.setCollidable(false);
				key.setDrawable(false);
				key = null;
			} else if (player.getInventory().containsKey()) {
				door.setOpen();
				Key key = player.getInventory().getKey();
				player.getInventory().removeItemFromContainer(key);
				key.setCollidable(false);
				key.setDrawable(false);
				key = null;
			}
		}
	}

	/**
	 * This method adds a collectable to the inventory.
	 *
	 * @param collectable
	 */
	public void pickup(Player player, Collectable collectable) {
		if (collectable == null) {
			return;
		} else if (player.getInventory() == null) {
			System.out.println("Error: Inventory not set up"); // TODO: throw
																// exceptionRenderer
																// here aswell
			return;
		} else {
			player.getInventory().addItemToContainer(collectable);
			return;
		}
	}

	/**
	 * This method adds a CoinBag to the inventory, if the player has an
	 * inventory.
	 *
	 * @param
	 */
	public void pickupCoinBag(Player player, CoinBag coinBag) {
		if (coinBag == null) {
			return;
		} else if (player.getInventory() == null) {
			System.out.println("Error: Inventory not set up");
			return;
		} else {
			player.getInventory().addItemToContainer(coinBag);
			return;
		}
	}
}
