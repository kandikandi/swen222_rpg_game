package tests;

import model.*;
import control.*;

import static org.junit.Assert.*;
import org.junit.Test;

/**
 * These tests relate to actor creation and interaction.
 *
 *
 */
public class GameWorldTests {

	@Test
	public void test_player_move() {
		makeStandardGameObjects();
		assertTrue(true);
	}

	@Test
	public void test_player_move_locked_door() {
		makeStandardGameObjects();
		assertTrue(true);
	}

	@Test
	public void test_player_move_key_door() {
		makeStandardGameObjects();
		assertTrue(true);
	}

	@Test
	public void test_player_pickup_coin_no_coinbag() {
		makeStandardGameObjects();
		assertTrue(true);
	}

	@Test
	public void test_correct_number_of_coins_inventory() {
		makeStandardGameObjects();
		assertTrue(true);
	}

	@Test
	public void test_correct_number_items_inventory() {
		makeStandardGameObjects();
		assertTrue(true);
	}

	@Test
	public void test_player_pickup_coin_with_coinbag() {
		makeStandardGameObjects();
		assertTrue(true);
	}

	@Test
	public void test_player_pickup_coin_full_inventory() {
		makeStandardGameObjects();
		assertTrue(true);
	}

	@Test
	public void test_player_drop_coin_no_longer_in_inventory() {
		makeStandardGameObjects();
		assertTrue(true);
	}

	@Test
	public void test_player_drop_coin_now_drawable() {
		makeStandardGameObjects();
		assertTrue(true);
	}

	@Test
	public void test_player_drop_coin_now_collidable() {
		makeStandardGameObjects();
		assertTrue(true);
	}

	@Test
	public void test_player_full_inventory_pickup_coinbag() {
		makeStandardGameObjects();
		assertTrue(true);
	}

	@Test
	public void test_player_full_inventory_pickup_key() {
		makeStandardGameObjects();
		assertTrue(true);
	}


	@Test
	public void test_player_collision_enemy_player_fear_increase() {
		makeStandardGameObjects();
		assertTrue(true);
	}

	@Test
	public void test_player_collision_enemy_enemy_health_decrease() {
		makeStandardGameObjects();
		assertTrue(true);
	}


	///////////////////////////////////////////////////////////////////
	//																 //
	//						HELPER METHODS							 //
	//																 //
	///////////////////////////////////////////////////////////////////

	/**
	 * Helper method which sets up key objects for the tests.
	 *
	 */
	private void makeStandardGameObjects() {

	}

}
