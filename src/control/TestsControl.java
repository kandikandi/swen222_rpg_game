package control;
import static org.junit.Assert.*;

import java.awt.Image;
import java.awt.Point;

import org.junit.Test;

import model.*;

public class TestsControl {


	@Test public void test_01() {
		Player player = new Player(ID.PLAYER, new Position(50,50), null, true, true, 45);
		player.move(DIR.LEFT);
		player.move(DIR.LEFT);
		player.move(DIR.RIGHT);
		System.out.println(player.getPosition().toString());
		assertTrue(player.getPosition().getxPos() == 45 && player.getPosition().getyPos() == 50);
	}

	@Test public void test_02() {
		Player player = new Player(ID.PLAYER, new Position(50,50), null, true, true, 45);
		player.move(DIR.DOWN);
		assertTrue(player.getPosition().getyPos() == 55 && player.getPosition().getxPos() == 50);
	}

//	// check bounds
//	@Test public void test_03() {
//		Player player = new Player(ID.PLAYER, new Position(0,0,0,0), null, true, true, 45);
//		player.move(DIR.UP);
//		player.move(DIR.LEFT);
//		assertTrue(player.getPosition().getyPos() == 0 && player.getPosition().getxPos() == 0); //should stay put.
//	}

	@Test public void test_04() {
		Collectable collectable = new Collectable(ID.ITEM, new Position(5,5), null, true, true, 45);
		Container container = new Container(ID.CONTAINER, new Position(5,5), null, true, true, 45);
		container.addItemToContainer(collectable);
		container.removeItemFromContainer(collectable);
		container.removeItemFromContainer(collectable);
		container.removeItemFromContainer(collectable);
		assert(container.numberOfObjectInContainer()==0);
	}

	@Test public void test_05() {
		Collectable collectable = new Collectable(ID.ITEM, new Position(5,5), null, true, true,45);
		Container container = new Container(ID.CONTAINER, new Position(5,5), null, true, true, 45, collectable);
		assert(container.numberOfObjectInContainer()==1);
	}

	@Test public void test_06() {
		//tests whether container will not accept more items if full
		Collectable collectable1 = new Collectable(ID.ITEM, new Position(5,5), null, true, true,45);
		Collectable collectable2 = new Collectable(ID.ITEM, new Position(5,5), null, true, true,45);
		Collectable collectable3 = new Collectable(ID.ITEM, new Position(5,5), null, true, true,45);
		Collectable collectable4 = new Collectable(ID.ITEM, new Position(5,5), null, true, true,45);
		Collectable collectable5 = new Collectable(ID.ITEM, new Position(5,5), null, true, true,45);
		Collectable collectable6 = new Collectable(ID.ITEM, new Position(5,5), null, true, true,45);
		Collectable collectable7 = new Collectable(ID.ITEM, new Position(5,5), null, true, true,45);

		Container container = new Container(ID.CONTAINER, new Position(5,5), null, true, true,45,
				collectable1,
				collectable2,
				collectable3,
				collectable4,
				collectable5,
				collectable6,
				collectable7);
		assert(container.numberOfObjectInContainer()==5);
	}

	@Test public void test_07() {
		//tests whether items inside container move with container / player
		Player player = new Player(ID.PLAYER, new Position(5,5), null, true, true, 45);
		Collectable collectable = new Collectable(ID.ITEM, new Position(5000,5), null, true, true,45);
		Inventory inventory = new Inventory(ID.CONTAINER, new Position(50,5), null, true, true, 45,
				player);
		inventory.addItemToContainer(collectable);
		player.setInventory(inventory);
		player.move(DIR.RIGHT);
		player.move(DIR.RIGHT);
		player.drop(collectable);
		assertTrue(collectable.getPosition().getxPos()==player.getPosition().getxPos());
	}


	@Test public void test_08() {
		//tests whether items inside container move with container / player
		Player player = new Player(ID.PLAYER, new Position(5,5), null, true, true, 45);
		assertTrue(player.getBoundingBox().contains(new Point(10,10)));
	}

	@Test public void test_09() {
		//tests whether items inside container move with container / player
		Player player = new Player(ID.PLAYER, new Position(5,5), null, true, true, 45);
		assertFalse(player.getBoundingBox().contains(new Point(60,60)));
	}


	@Test public void test_10() {
		Player player = new Player(ID.PLAYER, new Position(5,5), null, true, true, 45);
		Player player2 = new Player(ID.PLAYER, new Position(15,15), null, true, true, 45);
		assertTrue(player.getBoundingBox().contains(player2.getPosition().getxPos(),player2.getPosition().getyPos()));
	}

	@Test public void test_12() {
		Player player = new Player(ID.PLAYER, new Position(5,5), null, true, true, 45);
		Collectable collectable = new Collectable(ID.ITEM, new Position(60,5), null, true, true,45);
		Inventory inventory = new Inventory(ID.CONTAINER, new Position(50,5), null, true, true, 45,
				player);
		player.setInventory(inventory);
		player.pickup(collectable);
		player.drop(collectable);
		assertTrue(player.getInventory().numberOfObjectInContainer()==0);
	}

	@Test public void test_13() {
		// if a player walks over a collectable and has space in inventory, should be added to inventory.
		Player player = new Player(ID.PLAYER, new Position(5,5), null, true, true, 45);
		Collectable collectable = new Collectable(ID.ITEM, new Position(60,5), null, true, true,45);
		Inventory inventory = new Inventory(ID.CONTAINER, new Position(50,5), null, true, true, 45,
				player);
		player.setInventory(inventory);
		player.move(DIR.RIGHT);
		player.move(DIR.RIGHT);
		player.move(DIR.RIGHT);
		assertTrue(player.getInventory().numberOfObjectInContainer()==1);
	}

	@Test public void test_14() {
		Player player = new Player(ID.PLAYER, new Position(5,5), null, true, true, 45);
		Collectable collectable = new Collectable(ID.ITEM, new Position(60,5), null, true, true,45);
		Inventory inventory = new Inventory(ID.CONTAINER, new Position(50,5), null, true, true, 45,
				player);
		player.setInventory(inventory);
		player.move(DIR.RIGHT);
		player.move(DIR.RIGHT);
		player.move(DIR.RIGHT);
		player.drop(collectable);
		assertTrue(player.getInventory().numberOfObjectInContainer()==1);
	}




}
