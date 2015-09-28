/*
package control;
import static org.junit.Assert.*;

import java.awt.Image;
import java.awt.Point;

import org.junit.Test;

import model.*;
import view.ActorAssets;

public class TestsControl {


	@Test public void test_player_move() {
		Player player = new Player(ID.PLAYER, new Position(50,50), null, true, true, 45, gameController);
		GameState gs = new GameState(new GameKeyListener());
		player.move(DIR.LEFT);
		assertTrue(player.getPosition().getxPos() == 45 && player.getPosition().getyPos() == 50);
	}

	@Test public void test_02() {
		Player player = new Player(ID.PLAYER, new Position(50,50), null, true, true, 45, gameController);
		GameState gs = new GameState(new GameKeyListener());
		player.move(DIR.DOWN);
		assertTrue(player.getPosition().getyPos() == 55 && player.getPosition().getxPos() == 50);
	}

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

	@Test public void test_collectable_moves() {
		//tests whether items inside container move with container / player
		Player player = new Player(ID.PLAYER, new Position(5,5), null, true, true, 45, gameController);
		Collectable collectable = new Collectable(ID.ITEM, new Position(5000,5), null, true, true,45);
		Inventory inventory = new Inventory(ID.CONTAINER, new Position(50,5), null, true, true, 45,
				player);
		inventory.addItemToContainer(collectable);
		GameState gs = new GameState(new GameKeyListener());
		gs.addActor(collectable, inventory);
		player.setInventory(inventory);
		player.move(DIR.RIGHT);
		player.move(DIR.RIGHT);
		player.drop(collectable);
		assertTrue(collectable.getPosition().getxPos()==player.getPosition().getxPos());
	}

	@Test public void test_08() {
		//tests whether items inside container move with container / player
		Player player = new Player(ID.PLAYER, new Position(5,5), null, true, true, 45, gameController);
		assertTrue(player.getBoundingBox().contains(new Point(10,10)));
	}

	@Test public void test_09() {
		//tests whether items inside container move with container / player
		Player player = new Player(ID.PLAYER, new Position(5,5), null, true, true, 45, gameController);
		assertFalse(player.getBoundingBox().contains(new Point(60,60)));
	}


	@Test public void test_10() { // just testing bounding box
		Player player = new Player(ID.PLAYER, new Position(5,5), null, true, true, 45, gameController);
		Player player2 = new Player(ID.PLAYER, new Position(15,15), null, true, true, 45, gameController);
		assertTrue(player.getBoundingBox().contains(player2.getPosition().getxPos(),player2.getPosition().getyPos()));
	}

	@Test public void test_12() {
		Player player = new Player(ID.PLAYER, new Position(5,5), null, true, true, 45, gameController);
		Collectable collectable = new Collectable(ID.ITEM, new Position(60,5), null, true, true,45);
		Inventory inventory = new Inventory(ID.CONTAINER, new Position(50,5), null, true, true, 45,
				player);
		GameState gs = new GameState(new GameKeyListener());
		gs.addActor(collectable, inventory);
		player.setInventory(inventory);
		player.pickup(collectable);
		player.drop(collectable);
		assertTrue(player.getInventory().numberOfObjectInContainer()==0);
	}

	@Test public void test_13() {
		// if a player walks over a collectable and has space in inventory, should be added to inventory.
		Player player = new Player(ID.PLAYER, new Position(5,5), null, true, true, Main.PLAYER_SIZE, gameController);
		Inventory inventory = new Inventory(ID.CONTAINER, new Position(50,5), null, true, true, 45,
				player);
		player.setInventory(inventory);
		Collectable collectable = new Collectable(ID.ITEM, new Position(15,5), null, true, true,Main.ITEM_SIZE );
		GameState gs = new GameState(new GameKeyListener());
		gs.addActor(inventory, collectable);
		player.move(DIR.RIGHT);
		player.move(DIR.RIGHT);
		player.move(DIR.RIGHT);
		assertTrue(player.getInventory().numberOfObjectInContainer()==1);
	}

	@Test public void test_14() {
		Player player = new Player(ID.PLAYER, new Position(5,5), null, true, true, Main.PLAYER_SIZE, gameController);
		Collectable collectable = new Collectable(ID.ITEM, new Position(60,5), null, true, true,45);
		Inventory inventory = new Inventory(ID.CONTAINER, new Position(50,5), null, true, true, 45,
				player);
		GameState gs = new GameState(new GameKeyListener());
		gs.addActor(inventory, collectable);
		player.setInventory(inventory);
		player.move(DIR.RIGHT);
		player.move(DIR.RIGHT);
		player.move(DIR.RIGHT);
		assertTrue(player.getInventory().numberOfObjectInContainer()==1);
	}


	// KEY / DOOR Tests
	@Test public void test_16() {
		Player player = new Player(ID.PLAYER, new Position(5,5), null, true, true, 10, gameController);
		Inventory inventory = new Inventory(ID.CONTAINER, new Position(50,5), null, true, true, 45,
				player);
		player.setInventory(inventory);
		Key key = new Key(ID.KEY,new Position(20,5),null,true,true,10);
//		Image im = new Image;
		Door door = new Door(ID.ITEM, new Position(35,5), ActorAssets.DOOR.getImage() , true, true, 10);
		GameState gs = new GameState(new GameKeyListener());
		gs.addActor(inventory, key, door);
		player.move(DIR.RIGHT);player.move(DIR.RIGHT);player.move(DIR.RIGHT);
		player.move(DIR.RIGHT);player.move(DIR.RIGHT);player.move(DIR.RIGHT);
		player.move(DIR.RIGHT);player.move(DIR.RIGHT);player.move(DIR.RIGHT);
		assertTrue(player.getInventory().containsKey());
	}

	// Key unlocks door
	@Test public void test__door_unlocks() {
		Player player = new Player(ID.PLAYER, new Position(5,5), null, true, true, 10, gameController);
		Inventory inventory = new Inventory(ID.CONTAINER, new Position(50,5), null, true, true, 45,
				player);
		player.setInventory(inventory);
		Key key = new Key(ID.KEY,new Position(20,5),null,true,true,10);
		Door door = new Door(ID.ITEM, new Position(35,5), ActorAssets.DOOR.getImage() , true, true, 10);
		GameState gs = new GameState(new GameKeyListener());
		gs.addActor(inventory, key, door);
		player.move(DIR.RIGHT);player.move(DIR.RIGHT);player.move(DIR.RIGHT);
		player.move(DIR.RIGHT);player.move(DIR.RIGHT);player.move(DIR.RIGHT);
		player.move(DIR.RIGHT);player.move(DIR.RIGHT);player.move(DIR.RIGHT);
		assertTrue(door.getIsOpen());
	}

	// Key unlocks door - door no longer collidable
	@Test public void test_door_open_non_collidable() {
		Player player = new Player(ID.PLAYER, new Position(5,5), null, true, true, 10, gameController);
		Inventory inventory = new Inventory(ID.CONTAINER, new Position(50,5), null, true, true, 45,
				player);
		player.setInventory(inventory);
		Key key = new Key(ID.KEY,new Position(20,5),null,true,true,10);
		Door door = new Door(ID.ITEM, new Position(35,5), ActorAssets.DOOR.getImage() , true, true, 10);
		GameState gs = new GameState(new GameKeyListener());
		gs.addActor(inventory, key, door);
		player.move(DIR.RIGHT);player.move(DIR.RIGHT);player.move(DIR.RIGHT);
		player.move(DIR.RIGHT);player.move(DIR.RIGHT);player.move(DIR.RIGHT);
		player.move(DIR.RIGHT);player.move(DIR.RIGHT);player.move(DIR.RIGHT);
		assertTrue(!door.isCollidable() );
	}


	// Coin picked up goes into inventory correctly
	@Test public void test_coin_001() {
		Player player = new Player(ID.PLAYER, new Position(5,5), null, true, true, 10, gameController);
		Coin coin1 = new Coin(ID.ITEM, new Position(200,5), ActorAssets.COIN.getImage() , true, true, 20);
		Coin coin2 = new Coin(ID.ITEM, new Position(200,30), ActorAssets.COIN.getImage() , true, true, 20);
		Coin coin3 = new Coin(ID.ITEM, new Position(200,55), ActorAssets.COIN.getImage() , true, true, 20);
		Coin coin4 = new Coin(ID.ITEM, new Position(200,80), ActorAssets.COIN.getImage() , true, true, 20);
		Coin coin5 = new Coin(ID.ITEM, new Position(200,105), ActorAssets.COIN.getImage() , true, true, 20);
		Coin coin6 = new Coin(ID.ITEM, new Position(200,130), ActorAssets.COIN.getImage() , true, true, 20);
		Inventory inventory = new Inventory(ID.CONTAINER, new Position(50,5), null, true, true, 45,player,
				coin1, coin2, coin3, coin4, coin5, coin6);
		player.setInventory(inventory);
		assertTrue(inventory.getCoinCount()==6);
	}

	// If pickup coin bag, add to inventory and move all coins into bag.
	@Test public void test_coin_bag() {
		Player player = new Player(ID.PLAYER, new Position(5,5), null, true, true, 10, gameController);
		Coin coin1 = new Coin(ID.ITEM, new Position(200,5), ActorAssets.COIN.getImage() , true, true, 20);
		Coin coin2 = new Coin(ID.ITEM, new Position(200,30), ActorAssets.COIN.getImage() , true, true, 20);
		Coin coin3 = new Coin(ID.ITEM, new Position(200,55), ActorAssets.COIN.getImage() , true, true, 20);
		Coin coin4 = new Coin(ID.ITEM, new Position(200,80), ActorAssets.COIN.getImage() , true, true, 20);
		Coin coin5 = new Coin(ID.ITEM, new Position(200,105), ActorAssets.COIN.getImage() , true, true, 20);
		Coin coin6 = new Coin(ID.ITEM, new Position(200,130), ActorAssets.COIN.getImage() , true, true, 20);
		Inventory inventory = new Inventory(ID.CONTAINER, new Position(20,5), null, true, true, 45,player,
				coin1, coin2, coin3, coin4, coin5, coin6);
		player.setInventory(inventory);
		CoinBag coinBag = new CoinBag(ID.CONTAINER, new Position(20,5),null,true,true,Main.ITEM_SIZE);
		GameState gs = new GameState(new GameKeyListener());
		gs.addActor(inventory, coinBag, coin1, coin2, coin3, coin4, coin5, coin6);
		player.move(DIR.RIGHT);player.move(DIR.RIGHT);player.move(DIR.RIGHT);
		assertTrue(coinBag.numberOfCoinsInCoinBag()==6);
	}

	// Enemy creation and starting attack points.
	@Test public void test_enemy_001(){
		GameState gs = new GameState(new GameKeyListener());
        Image image = ActorAssets.ENEMY.getImage();
        int size = Main.ITEM_SIZE;
        image = image.getScaledInstance(size, size, Image.SCALE_FAST);
        Enemy enemy = new Enemy(ID.ENEMY, new Position(50,50), image, true, true, size);
		gs.addActor(enemy);
		enemy.setAttackPoints(2);
		assertTrue(enemy.getAttackPoints()==2);
	}

	// If Player is attacking and enemy is not on collision, monster loses 2 health.
	@Test public void test_enemy_002(){
		Player player = new Player(ID.PLAYER, new Position(5,5), null, true, true, 10, gameController);
		Inventory inventory = new Inventory(ID.CONTAINER, new Position(20,5), null, true, true, 45,
				player);
		player.setInventory(inventory);
		GameState gs = new GameState(new GameKeyListener());
        Image image = ActorAssets.ENEMY.getImage();
        int size = Main.ITEM_SIZE;
        image = image.getScaledInstance(size, size, Image.SCALE_FAST);
        Enemy enemy = new Enemy(ID.ENEMY, new Position(50,5), image, true, true, size);
		gs.addActor(enemy);
		player.setAttack(true);
		enemy.setAttack(false);
		player.move(DIR.RIGHT);
		assertTrue(enemy.getHealth()==8);
	}

	// If player and enemy are both attacking, enemy loses one health, player add 1 fear.
	@Test public void test_enemy_003(){
		Player player = new Player(ID.PLAYER, new Position(5,5), null, true, true, 10, gameController);
		Inventory inventory = new Inventory(ID.CONTAINER, new Position(20,5), null, true, true, 45,
				player);
		player.setInventory(inventory);
		GameState gs = new GameState(new GameKeyListener());
        Image image = ActorAssets.ENEMY.getImage();
        int size = Main.ITEM_SIZE;
        image = image.getScaledInstance(size, size, Image.SCALE_FAST);
        Enemy enemy = new Enemy(ID.ENEMY, new Position(50,5), image, true, true, size);
		gs.addActor(enemy);
		player.setAttack(true);
		enemy.setAttack(true);
		player.move(DIR.RIGHT);

		assertTrue(enemy.getHealth()==9 && player.getFear()==1);
	}

	// If player  not attacking and enemy attacking, player loses 2 health.
	@Test public void test_enemy_004(){
		Player player = new Player(ID.PLAYER, new Position(5,5), null, true, true, 10, gameController);
		Inventory inventory = new Inventory(ID.CONTAINER, new Position(20,5), null, true, true, 45,
				player);
		player.setInventory(inventory);
		GameState gs = new GameState(new GameKeyListener());
        Image image = ActorAssets.ENEMY.getImage();
        int size = Main.ITEM_SIZE;
        image = image.getScaledInstance(size, size, Image.SCALE_FAST);
        Enemy enemy = new Enemy(ID.ENEMY, new Position(50,5), image, true, true, size);
		gs.addActor(enemy);
		player.setAttack(false);
		enemy.setAttack(true);
		player.move(DIR.RIGHT);
		assertTrue(enemy.getHealth()==10 && player.getFear()==2);
	}

	// If player  not attacking and enemy not attacking, player gains 1 fear, loses 1 health
	@Test public void test_enemy_005(){
		Player player = new Player(ID.PLAYER, new Position(5,5), null, true, true, 10, gameController);
		Inventory inventory = new Inventory(ID.CONTAINER, new Position(20,5), null, true, true, 45,
				player);
		player.setInventory(inventory);
		GameState gs = new GameState(new GameKeyListener());
        Image image = ActorAssets.ENEMY.getImage();
        int size = Main.ITEM_SIZE;
        image = image.getScaledInstance(size, size, Image.SCALE_FAST);
        Enemy enemy = new Enemy(ID.ENEMY, new Position(50,5), image, true, true, size);
		gs.addActor(enemy);
		player.setAttack(false);
		enemy.setAttack(false);
		player.move(DIR.RIGHT);
		assertTrue(enemy.getHealth()==9 && player.getFear()==1);
	}

	// Shouldn't be able to move through enemy
	@Test public void test_enemy_006(){
		Player player = new Player(ID.PLAYER, new Position(5,5), null, true, true, 45, gameController);
		Enemy enemy = new Enemy(ID.ENEMY, new Position(60,5), null, true, true, 45);
		GameState gs = new GameState(new GameKeyListener());
		gs.addActor(enemy);
		player.move(DIR.RIGHT);player.move(DIR.RIGHT);player.move(DIR.RIGHT);
		player.move(DIR.RIGHT);player.move(DIR.RIGHT);player.move(DIR.RIGHT);
		player.move(DIR.RIGHT);player.move(DIR.RIGHT);player.move(DIR.RIGHT);
		System.out.println(player.getPosition().getxPos());
		assertTrue(player.getPosition().getxPos()==15);
	}


}
*/
