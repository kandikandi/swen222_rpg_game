package model;

import control.Main;

import java.util.*;
import java.util.List;

import view.ActorAssets;
import view.ID;
import view.TestWorlds;

/**
 * This factory class is used to create most of the required
 * state elements for a game. Two map files are read. One is the
 * Tile map, which has an array of Tiles, which are "dumb" elements.
 * The other map contains the game's Actors, which include the Players,
 * Enemies, Walls, Collectables, etc. The other methods construct
 * standard Actor objects with convenient syntax that only requires
 * a position for initial placement.
 *
 */

public class TestModeFactory  {

	public TestModeFactory() {

	}

	/**
	 * This method reads in an object mapfrom the View package and constructs
	 * the required Actors instantiations.
	 *
	 */
	public List<Actor> createActorList() {

		char[][] tileCode = TestWorlds.getMazeObjects();
		List<Actor> actors = new ArrayList<>();
		for (int row = 0; row < tileCode.length; row++) {
			for (int col = 0; col < tileCode[0].length; col++) {

				char asciiCode = tileCode[row][col];
				if (asciiCode == '0') {
					continue;
				}
				Position position = new Position(col * Main.TILE_SIZE, row
						* Main.TILE_SIZE);
				int size = Main.ITEM_SIZE;
				ActorAssets asset = ActorAssets.getAssetName(asciiCode);
				Actor actor = null;
				switch (asset) {
				case COIN:
					actor = createCoin(position.getxPos(), position.getyPos());
					break;
				case COINBAG:
					actor = createCoinBag(position.getxPos(),
							position.getyPos());
					break;
				case COLLECTABLE:
					actor = createCollectable(position.getxPos(),
							position.getyPos());
					break;
				case DOOR:
					actor = createDoor(position.getxPos(), position.getyPos());
					break;
				case ENEMY:
					actor = createEnemyActor(position.getxPos(),
							position.getyPos());
					break;
				case KEY:
					actor = createKey(position.getxPos(), position.getyPos());
					break;
				case WALL:
					actor = createWall(position.getxPos(), position.getyPos());
					break;
				}

				if (actor == null) {
					try {
						throw new GameException(
								"TestModeFactory: failed to create actor");
					} catch (GameException e) {
						System.out.println(e.getMessage());
						e.printStackTrace();
					}
				}

				actors.add(actor);
			}
		}
		return actors;
	}

	/**
	 * This method reads in an object map from the View package and constructs
	 * the required Tile objects.
	 */
	public Tile[][] createWorldTiles() {
		char[][] tileCode = TestWorlds.getMazeTiles();
		Tile[][] result = new Tile[tileCode.length][tileCode[0].length];

		for (int row = 0; row < tileCode.length; row++) {
			for (int col = 0; col < tileCode[0].length; col++) {
				char asciiCode = tileCode[row][col];
				Position position = new Position(col * Main.TILE_SIZE, row
						* Main.TILE_SIZE);
				result[row][col] = new Tile(asciiCode, position);
			}
		}
		return result;
	}

	/**
	 * This method constructs a standard EnemyActor object.
	 *
	 */
	public Enemy createEnemyActor(int xPos, int yPos) {
		Position pos = new Position(xPos, yPos);
		char asciiCode = ActorAssets.ENEMY.getAsciiCode();
		int size = Main.ITEM_SIZE;
		Enemy enemy = new Enemy(ID.ENEMY, pos, asciiCode, true, true, size);
		return enemy;
	}

	/**
	 * This method constructs a standard Door object.
	 *
	 */
	public Door createDoor(int xPos, int yPos) {
		Position pos = new Position(xPos, yPos);
		char asciiCode = ActorAssets.DOOR.getAsciiCode();
		int size = Main.ITEM_SIZE;
		Door door = new Door(ID.DOOR, pos, asciiCode, true, true, size);
		return door;
	}

	/**
	 * This method constructs a standard Key object.
	 *
	 */
	public Key createKey(int xPos, int yPos) {
		Position pos = new Position(xPos, yPos);
		char asciiCode = ActorAssets.KEY.getAsciiCode();
		int size = Main.ITEM_SIZE;
		Key key = new Key(ID.KEY, pos, asciiCode, true, true, size);
		key.setDescription("It looks like an ordinary key.");
		return key;
	}

	/**
	 * This method constructs a standard Wall object.
	 *
	 */
	public Wall createWall(int xPos, int yPos) {
		Position pos = new Position(xPos, yPos);
		char asciiCode = ActorAssets.WALL.getAsciiCode();
		int size = Main.ITEM_SIZE;
		Wall wall = new Wall(ID.WALL, pos, asciiCode, true, true, size);
		return wall;
	}

	/**
	 * This method constructs a standard PlayerActor object.
	 *
	 */
	public Player createPlayerActor(int clientNum) {
		Position loc = new Position(75, 75);
		char asciiCode = ActorAssets.PLAYER.getAsciiCode();
		Player player = new Player(ID.PLAYER, loc, asciiCode, true, true,
				Main.PLAYER_SIZE, clientNum);
		// player.setInventory(createInventory(true, 10, 10));
		return player;
	}

	/**
	 * This method constructs a standard Coin object.
	 *
	 */
	public Coin createCoin(int xPos, int yPos) {
		Position pos = new Position(xPos, yPos);
		char asciiCode = ActorAssets.COIN.getAsciiCode();
		int size = Main.ITEM_SIZE;
		Coin coin = new Coin(ID.KEY, pos, asciiCode, true, true, size);
		return coin;
	}

	/**
	 * This method constructs a standard CoinBag object.
	 *
	 */
	public CoinBag createCoinBag(int xPos, int yPos) {
		Position pos = new Position(xPos, yPos);
		char asciiCode = ActorAssets.COINBAG.getAsciiCode();
		int size = Main.ITEM_SIZE;
		CoinBag coinBag = new CoinBag(ID.KEY, pos, asciiCode, true, true, size);
		return coinBag;
	}

	/**
	 * This method constructs a standard Collectable object.
	 *
	 */

	public Collectable createCollectable(int xPos, int yPos) {
		Position pos = new Position(xPos, yPos);
		char asciiCode = ActorAssets.COLLECTABLE.getAsciiCode();
		int size = Main.ITEM_SIZE;
		Collectable collectable = new Collectable(ID.COLLECTABLE, pos,
				asciiCode, true, true, size);
		return collectable;
	}

	/**
	 * This method constructs a standard Container object.
	 *
	 */
	public Container createContainer(int xPos, int yPos) {
		Position pos = new Position(xPos, yPos);
		char asciiCode = ActorAssets.COLLECTABLE.getAsciiCode();
		int size = Main.ITEM_SIZE;
		Container container = new Container(ID.CONTAINER, pos,
				asciiCode, true, true, size);
		return container;
	}

}