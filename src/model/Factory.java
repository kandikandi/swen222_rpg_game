package model;

import control.Main;

import java.util.*;
import java.util.List;
import view.ActorAssets;
import view.TestWorlds;

/**
 * This factory class is used to create most of the required
 * state elements for a game. Two map files are read. One is the
 * Tile map, which has an array of Tiles, which are "dumb" elements.
 * The other map contains the game's Actors, which include the Players,
 * Enemies, Walls, Collectables, etc. The other methods construct
 * standard Actor objects with convenient syntax that only requires
 * a position for initial placement.
 */

public class Factory {

    public Factory() {

    }

    /**
     * This method reads in an object mapfrom the View package and constructs
     * the required Actors instantiations.
     */
    public List<Actor> createActorList() {
        char[][] tileCode = TestWorlds.getFullObjectMap();
//        char[][] tileCode = TestWorlds.getMazeObjects();
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
                    case SPECIAL_DOOR:
                        actor = createSpecialDoor(position.getxPos(), position.getyPos(),true);
                        break;
                    case ENEMY:
                        actor = createEnemyActor(position.getxPos(),
                                position.getyPos());
                        break;
                    case KEY:
                        actor = createKey(position.getxPos(), position.getyPos());
                        break;
                    case SPECIAL_KEY:
                        actor = createKey(position.getxPos(), position.getyPos(), true);
                        break;
                    case WALL:
                        actor = createWall(position.getxPos(), position.getyPos());
                        break;
                    case TREE:
                        actor = createTree(position.getxPos(), position.getyPos());
                        break;
                    case VEGEWALL:
                        actor = createWall(position.getxPos(), position.getyPos());
                        break;
                    case SCHOOLWALL:
                        actor = createWall(position.getxPos(), position.getyPos());
                        break;
                    case CANDY:
                        actor = createCandy(position.getxPos(), position.getyPos());
                        break;
                    case CIRCUSWALL:
                        actor = createWall(position.getxPos(), position.getyPos());
                        break;
                }

                if (actor == null) {
                    try {
                        throw new GameException(
                                "Factory: failed to create actor");
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


	private Actor createCandy(int xPos, int yPos) {
        int size = Main.TILE_SIZE;
        Position pos = new Position(xPos, yPos);
        Actor tree = new Actor(pos, ActorAssets.TREE.getAsciiCode(), true, true);
        //Candy candy = new Candy(pos, ActorAssets.CANDY.getAsciiCode(), true, true);
        //return candy;
        return tree;
	}

	/**
     * This method constructs a Actor object with Tree assets.
     */
    private Actor createTree(int x, int y) {
        int size = Main.TILE_SIZE;
        BoundingBox bbox = new BoundingBox(size, size, 40, 80);
        Position pos = new Position(x, y, bbox);
        Actor tree = new Actor(pos, ActorAssets.TREE.getAsciiCode(), true, true);
        return tree;
    }

    /**
     * This method reads in an object map from the View package and constructs
     * the required Tile objects.
     */
    public Tile[][] createWorldTiles() {
        char[][] tileCode = TestWorlds.getFullMap();
//        char[][] tileCode = TestWorlds.getMazeTiles();
        Tile[][] result = new Tile[tileCode.length][tileCode[0].length];

        for (int row = 0; row < tileCode.length; row++) {
            for (int col = 0; col < tileCode[0].length; col++) {
                char asciiCode = tileCode[row][col];
                final int tileSize = Main.TILE_SIZE;
                BoundingBox bBox = new BoundingBox(tileSize, tileSize, 0, 0);
                Position position = new Position(col * tileSize, row
                        * tileSize, bBox);
                result[row][col] = new Tile(asciiCode, position);
            }
        }
        return result;
    }

    /**
     * This method constructs a standard EnemyActor object.
     */
    public Enemy createEnemyActor(int xPos, int yPos) {
        int size = Main.ITEM_SIZE;
        Position pos = new Position(xPos, yPos);
        char asciiCode = ActorAssets.ENEMY.getAsciiCode();
        Enemy enemy = new Enemy(pos, asciiCode, true, true);
        return enemy;
    }

    /**
     * This method constructs a standard Door object.
     */
    public Door createDoor(int xPos, int yPos) {
        Position pos = new Position(xPos, yPos);
        char asciiCode = ActorAssets.DOOR.getAsciiCode();
        Door door = new Door(pos, asciiCode, true, true);
        return door;
    }

    /**
     * This method constructs a standard Door object.
     */
    public Door createSpecialDoor(int xPos, int yPos, boolean special) {
        Position pos = new Position(xPos, yPos);
        char asciiCode = ActorAssets.SPECIAL_DOOR.getAsciiCode();
        Door door = new Door(pos, asciiCode, true, true);
        door.setSpecial(special);
        return door;
    }

    /**
     * This method constructs a standard Key object.
     */
    public Key createKey(int xPos, int yPos) {
        Position pos = new Position(xPos, yPos);
        char asciiCode = ActorAssets.KEY.getAsciiCode();
        Key key = new Key(pos, asciiCode, true, true);
        key.setDescription("It looks like an ordinary key.");
        return key;
    }

    /**
     * This method constructs a special Key object.
     */
    private Actor createKey(int xPos, int yPos, boolean special) {
        Position pos = new Position(xPos, yPos);
        char asciiCode = ActorAssets.SPECIAL_KEY.getAsciiCode();
        Key key = new Key(pos, asciiCode, true, true);
        key.setSpecial(special);
        key.setDescription("It looks like a special key.");
        return key;
	}



    /**
     * This method constructs a standard Wall object.
     */
    public Wall createWall(int xPos, int yPos) {
        Position pos = new Position(xPos, yPos);
        int size = Main.ITEM_SIZE;
        char asciiCode = ActorAssets.WALL.getAsciiCode();
        Wall wall = new Wall(pos, asciiCode, true, true);
        return wall;
    }

    /**
     * This method constructs a standard PlayerActor object.
     */
    public Player createPlayerActor(int clientNum) {
        BoundingBox bBox = new BoundingBox(26, 30, 7, 15);
        Position loc = new Position(Main.PLAYER_ONE_START_LOCATION_X, Main.PLAYER_ONE_START_LOCATION_Y, bBox);
        /*if (clientNum == 1) {
            loc = new Position(Main.PLAYER_TWO_START_LOCATION_X, Main.PLAYER_TWO_START_LOCATION_Y, bBox);
        }*/
        char asciiCode = ActorAssets.PLAYER.getAsciiCode();
        Player player = new Player(loc, asciiCode, true, true, clientNum);
        // player.setInventory(createInventory(true, 10, 10));
        return player;
    }

    /**
     * This method constructs a standard Coin object.
     */
    public Coin createCoin(int xPos, int yPos) {
        Position pos = new Position(xPos, yPos);
        int size = Main.ITEM_SIZE;
        char asciiCode = ActorAssets.COIN.getAsciiCode();
        Coin coin = new Coin(pos, asciiCode, true, true);
        return coin;
    }

    /**
     * This method constructs a standard CoinBag object.
     */
    public CoinBag createCoinBag(int xPos, int yPos) {
        Position pos = new Position(xPos, yPos);
        int size = Main.ITEM_SIZE;
        char asciiCode = ActorAssets.COINBAG.getAsciiCode();
        CoinBag coinBag = new CoinBag(pos, asciiCode, true, true);
        return coinBag;
    }

    /**
     * This method constructs a standard Collectable object.
     */

    public Collectable createCollectable(int xPos, int yPos) {
        Position pos = new Position(xPos, yPos);
        int size = Main.ITEM_SIZE;
        BoundingBox bBox = pos.getBoundingBox();
        char asciiCode = ActorAssets.COLLECTABLE.getAsciiCode();
        Collectable collectable = new Collectable(pos,
                asciiCode, true, true);
        return collectable;
    }

    /**
     * This method constructs a standard Container object.
     */
    public Container createContainer(int xPos, int yPos) {
        Position pos = new Position(xPos, yPos);
        int size = Main.ITEM_SIZE;
        char asciiCode = ActorAssets.COLLECTABLE.getAsciiCode();
        Container container = new Container(pos,asciiCode, true, true);
        return container;
    }

}
