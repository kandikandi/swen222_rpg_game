package model;

import control.Main;

import java.util.*;
import java.util.List;


import view.ActorAssets;
import view.ID;
import view.TestWorlds;


public class TestModeFactory extends AbstractFactory {

    public TestModeFactory() {

    }

    @Override
    public List<Actor> createActorList() {

    	char[][] tileCode = TestWorlds.getMazeObjects();
        List<Actor> actors = new ArrayList<>();
        for (int row = 0; row < tileCode.length; row++) {
            for (int col = 0; col < tileCode[0].length; col++) {

                char asciiCode = tileCode[row][col];
                if (asciiCode == '0') {
                    continue;
                }
                Position position = new Position(col * Main.TILE_SIZE, row * Main.TILE_SIZE);
                int size = Main.ITEM_SIZE;
                ActorAssets asset = ActorAssets.getAssetName(asciiCode);
                Actor actor = null;
                switch (asset) {
                	case COIN:
                        actor = createCoin(position.getxPos(), position.getyPos());
                		break;
                	case COINBAG:
                        actor = createCoinBag(position.getxPos(), position.getyPos());
                		break;
                	case KEY:
                        actor = createKey(position.getxPos(), position.getyPos());
                        break;
                    case ENEMY:
                    	actor = createEnemyActor(position.getxPos(), position.getyPos());
                    	break;
                    case DOOR:
                    	actor = createDoor(position.getxPos(), position.getyPos());
                    	break;
                    case COLLECTABLE:
                    	actor = createCollectable(position.getxPos(), position.getyPos());
                    	break;
                    case WALL:
                    	actor = createWall(position.getxPos(), position.getyPos());
                    	break;
                }

                if (actor == null) {
                    try {
                        throw new GameException("TestModeFactory: failed to create actor");
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

    @Override
    public Enemy createEnemyActor(int xPos, int yPos) {
    	Position pos = new Position(xPos, yPos);
        char asciiCode = ActorAssets.ENEMY.getAsciiCode();
        int size = Main.ITEM_SIZE;
        Enemy enemy = new Enemy(ID.ENEMY, pos, asciiCode, true, true, size);
        return enemy;
    }

    @Override
    public Door createDoor(int xPos, int yPos){
    	Position pos = new Position(xPos, yPos);
    	char asciiCode = ActorAssets.DOOR.getAsciiCode();
    	int size = Main.ITEM_SIZE;
    	Door door = new Door(ID.DOOR, pos, asciiCode, true, true, size);
    	return door;
    }

    @Override
    public Key createKey(int xPos, int yPos) {
        Position pos = new Position(xPos, yPos);
        char asciiCode = ActorAssets.KEY.getAsciiCode();
        int size = Main.ITEM_SIZE;
        Key key = new Key(ID.KEY, pos, asciiCode, true, true, size);
        key.setDescription("It looks like an ordinary key.");
        return key;
    }

    @Override
    public Wall createWall(int xPos, int yPos) {
        Position pos = new Position(xPos, yPos);
        char asciiCode = ActorAssets.WALL.getAsciiCode();
        int size = Main.ITEM_SIZE;
        Wall wall = new Wall(ID.WALL, pos, asciiCode, true, true, size);
        return wall;
    }

	@Override
	public Player createPlayerActor(int clientNum) {
	        Position loc = new Position(75, 75);
	        char asciiCode= ActorAssets.PLAYER.getAsciiCode();
	        Player player = new Player(ID.PLAYER, loc, asciiCode, true, true, Main.PLAYER_SIZE, clientNum);
	       // player.setInventory(createInventory(true, 10, 10));
	        return player;
	}

	@Override
	public Tile[][] createWorldTiles() {
        char[][] tileCode = TestWorlds.getMazeTiles();
        Tile[][] result = new Tile[tileCode.length][tileCode[0].length];

        for (int row = 0; row < tileCode.length; row++) {
            for (int col = 0; col < tileCode[0].length; col++) {
                char asciiCode = tileCode[row][col];
                Position position = new Position(col * Main.TILE_SIZE, row * Main.TILE_SIZE);
                result[row][col] = new Tile(asciiCode, position);
            }
        }
        return result;
    }


	@Override
	public Coin createCoin(int xPos, int yPos) {
		   Position pos = new Position(xPos, yPos);
	       char asciiCode = ActorAssets.COIN.getAsciiCode();
	       int size = Main.ITEM_SIZE;
	       Coin coin = new Coin(ID.KEY, pos, asciiCode, true, true, size);
	       return coin;
	}

	public CoinBag createCoinBag(int xPos, int yPos){
		   Position pos = new Position(xPos, yPos);
	       char asciiCode = ActorAssets.COINBAG.getAsciiCode();
	       int size = Main.ITEM_SIZE;
	       CoinBag coinBag = new CoinBag(ID.KEY, pos, asciiCode, true, true, size);
	       return coinBag;
	}



//	@Override
//	public Inventory createInventory(int xPos, int yPos) {
////		 Position pos = new Position(xPos, yPos);
////	     //char asciiCode = ActorAssets.COINBAG.getAsciiCode();
////	     //int size = Main.ITEM_SIZE;
////	     Inventory inventory = new Inventory(ID.INVENTORY, pos, asciiCode, false, false, size);
////
////		 return inventory;
//		return null;
//	}

	@Override
	public Collectable createCollectable(int xPos, int yPos) {
		   Position pos = new Position(xPos, yPos);
	       char asciiCode = ActorAssets.COLLECTABLE.getAsciiCode();
	       int size = Main.ITEM_SIZE;
	       Collectable collectable = new Collectable(ID.COLLECTABLE, pos, asciiCode, true, true, size);
	       return collectable;
	}

}
