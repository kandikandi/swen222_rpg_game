package Model;

import Control.Main;

import java.awt.*;
import java.io.Serializable;
import java.net.InetAddress;
import java.util.*;
import java.util.List;


import View.ActorAssets;
import View.ID;
import View.TestWorlds;


public class TestModeFactory extends AbstractFactory {

    public TestModeFactory() {

    }

    @Override
    public List<Actor> createActorList() {

    	char[][] tileCode = TestWorlds.getSmallObjectMap();
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
                		actor = new Coin(ID.COLLECTABLE, position, asciiCode, true, true, size);
                        break;
                	case COINBAG:
                		actor = new CoinBag(ID.CONTAINER, position, asciiCode, true, true, size);
                        break;
                	case KEY:
                        actor = new Key(ID.KEY, position, asciiCode, true, true, size);
                        break;
                    case ENEMY:
                    	actor = new Enemy(ID.ENEMY,position, asciiCode, true, true, size);
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
    public Key createKey(int xPos, int yPos) {
        Position pos = new Position(xPos, yPos);
        char asciiCode = ActorAssets.KEY.getAsciiCode();
        int size = Main.ITEM_SIZE;
        Key key = new Key(ID.KEY, pos, asciiCode, true, true, size);
        return key;
    }

	@Override
	public Player createPlayerActor(int clientNum) {
	        Position loc = new Position(5, 5);
	        char asciiCode= ActorAssets.PLAYER.getAsciiCode();
	        Player player = new Player(ID.PLAYER, loc, asciiCode, true, true, Main.PLAYER_SIZE, clientNum);
	       // player.setInventory(createInventory(true, 10, 10));
	        return player;
	}

	@Override
	public Tile[][] createWorldTiles() {
        char[][] tileCode = TestWorlds.getSmallMap();
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
	public Coin createCoin() {

		return null;
	}

	@Override
	public Inventory createInventory(boolean pickedUP, int xPos, int yPos) {

		return null;
	}

	@Override
	public Collectable createCollectable() {

		return null;
	}

}
