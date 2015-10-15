package tests;

import model.*;
import control.*;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

/**
 * These tests relate to actor creation and interaction.
 *
 *
 */
public class GameWorldTests {

	@Test
	public void test_player_move_no_collision() {
		GameState gameState = makeStandardGameObjects();
		Player player = gameState.findPlayer(1);

		player.move(gameState, "RIGHT");
		assertTrue(player.getPosition().getxPos() == 110);
	}

	@Test
	public void test_player_move_collision() {
		GameState gameState = makeStandardGameObjects();
		Player player = gameState.findPlayer(1);
		player.move(gameState, "LEFT");
		player.move(gameState, "LEFT");
		assertTrue(player.getPosition().getxPos() == 80);
	}

	@Test
	public void test_player_move_locked_door() {
		GameState gameState = makeStandardGameObjects();
		Player player = gameState.findPlayer(1);
		player.setPosition(new Position(385, 350));
		player.move(gameState, "LEFT");
		player.move(gameState, "LEFT");
		player.move(gameState, "LEFT");
		assertTrue(player.getPosition().getxPos() == 385);
	}

	@Test
	public void test_player_move_key_door() {
		GameState gameState = makeStandardGameObjects();
		Player player = gameState.findPlayer(1);
		player.setPosition(new Position(250, 350, 45));
		for (int i = 0; i < 10; i++) {
			player.move(gameState, "RIGHT");
		}
		assertTrue(player.getPosition().getxPos() == 340);
	}

	@Test
	public void test_player_pickup_coin_no_coinbag() {
		GameState gameState = makeStandardGameObjects();
		Player player = gameState.findPlayer(1);
		player.setPosition(new Position(1100, 1100));
		for (int i = 0; i < 5; i++) {
			player.move(gameState, "RIGHT");
		}
		assertTrue(player.getInventory().getCoinCount() == 1);
	}

	@Test
	public void test_correct_number_of_coins_inventory() {
		GameState gameState = makeStandardGameObjects();
		Player player = gameState.findPlayer(1);
		player.setPosition(new Position(1100, 1100));
		for (int i = 0; i < 5; i++) {
			player.move(gameState, "RIGHT");
		}

		assertTrue(player.getInventory().getCoinCount() == 1);
	}

	@Test
	public void test_correct_number_items_inventory() {
		GameState gameState = makeStandardGameObjects();
		Player player = gameState.findPlayer(1);
		player.setPosition(new Position(1100, 1100));
		for (int i = 0; i < 5; i++) {
			player.move(gameState, "RIGHT");
		}
		assertTrue(player.getInventory().numItemsInContainer() == 1);
	}

	@Test
	public void test_player_pickup_coin_with_coinbag() {
		GameState gameState = makeStandardGameObjects();
		Player player = gameState.findPlayer(1);
		player.setPosition(new Position(1400, 1100));
		for (int i = 0; i < 50; i++) {
			player.move(gameState, "RIGHT");
		}
		assertTrue(player.getInventory().getCoinBag().numItemsInContainer() == 1);
	}

	@Test
	public void test_player_pickup__coinbag() {
		GameState gameState = makeStandardGameObjects();
		Player player = gameState.findPlayer(1);
		player.setPosition(new Position(1400, 1100));
		for (int i = 0; i < 50; i++) {
			player.move(gameState, "RIGHT");
		}
		assertTrue(player.getInventory().containsCoinBag());
	}

	@Test
	public void test_player_pickup_coin_full_inventory() {
		GameState gameState = makeStandardGameObjects();
		Position pos = new Position(1, 1);
		Inventory inv = new Inventory(pos, 'a', false, false);
		// Position position, char imagePath, boolean collidable,boolean
		// drawable
		for (int i = 0; i < 100; i++) {
			Coin coin = new Coin(pos, 'a', false, false);
			inv.addItemToContainer(coin);
		}
		assertTrue(inv.numItemsInContainer() == 9);
	}

	@Test
	public void test_player_drop_coin_no_longer_in_inventory() {
		GameState gameState = makeStandardGameObjects();
		Position pos = new Position(1, 1);
		Player player = gameState.findPlayer(1);
		Inventory inv = player.getInventory();
		Coin coin = new Coin(pos, 'a', false, false);
		inv.addItemToContainer(coin);
		inv.removeItemFromContainer(coin);
		assertTrue(inv.numItemsInContainer() == 0);
	}

	@Test
	public void test_player_full_inventory_pickup_coinbag() {
		GameState gameState = makeStandardGameObjects();
		Position pos = new Position(1, 1);
		Inventory inv = new Inventory(pos, 'a', false, false);
		for (int i = 0; i < 100; i++) {
			Coin coin = new Coin(pos, 'a', false, false);
			inv.addItemToContainer(coin);
		}
		CoinBag coinBag = new CoinBag(pos, 'a', false, false);
		inv.addItemToContainer(coinBag);
		assertTrue(inv.numItemsInContainer() == 1);
	}

	@Test
	public void test_player_full_inventory_pickup_key() {
		GameState gameState = makeStandardGameObjects();
		Position pos = new Position(1, 1);
		Inventory inv = new Inventory(pos, 'a', false, false);
		for (int i = 0; i < 100; i++) {
			Coin coin = new Coin(pos, 'a', false, false);
			inv.addItemToContainer(coin);
		}
		Key key = new Key(pos, 'a', false, false);
		inv.addItemToContainer(key);
		assertTrue(inv.numItemsInContainer() == 9);
	}

	@Test
	public void test_player_collision_enemy_player_fear_increase() {
		GameState gameState = makeStandardGameObjects();
		Player player = gameState.findPlayer(1);
		player.setPosition(new Position(750, 800));
		for (int i = 0; i < 10; i++) {
			player.move(gameState, "RIGHT");
		}
		assertTrue(player.getFear() == 7);
	}

	@Test
	public void test_player_collision_enemy_health_decrease() {
		GameState gameState = makeStandardGameObjects();
		Player player = gameState.findPlayer(1);
		Position pos = new Position(1700,800, GlobalConst.PLAYER_SIZE);
		player.setPosition(pos);
		player.setAttack(true);
		Position pos2 = new Position(1750,800, GlobalConst.PLAYER_SIZE);
		Enemy enemy = new Enemy(pos2, 'a', true, true);
		enemy.setAttack(false);
		gameState.getActors().add(player);
		gameState.getActors().add(enemy);
		for (int i = 0; i < 10; i++) {
			player.move(gameState, "RIGHT");
		}
		assertTrue(enemy.getHealth() == 82);
	}

	// /////////////////////////////////////////////////////////////////
	// //
	// HELPER METHODS //
	// //
	// /////////////////////////////////////////////////////////////////

	/**
	 * Helper method which sets up key objects for the tests.
	 *
	 */
	private GameState makeStandardGameObjects() {

		Factory factory = new Factory();
		List<Actor> actors = new ArrayList<>();
		Player player = factory.createPlayerActor(1);
		actors.add(player);
		Enemy enemy = factory.createEnemyActor(800, 800);
		actors.add(enemy);
		Coin coin = factory.createCoin(1150, 1100);
		actors.add(coin);
		Coin coin2 = factory.createCoin(1500, 1100);
		actors.add(coin2);
		CoinBag coinBag = factory.createCoinBag(1450, 1100);
		actors.add(coinBag);
		Door door = factory.createDoor(350, 350);
		actors.add(door);
		Key key = factory.createKey(300, 350);
		actors.add(key);
		Wall wall = factory.createWall(90, 75);
		actors.add(wall);
		GameState gameState = new GameState(false);
		gameState.setActors(actors);
		return gameState;
	}

}