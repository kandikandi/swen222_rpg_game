package factory;

import control.*;
import model.*;
import system.GameException;
import view.ActorAssets;
import view.TestWorlds;
import view.TileAssets;

import java.awt.*;
import java.io.Serializable;
import java.net.InetAddress;
import java.util.*;
import java.util.List;


public class TestModeFactory extends AbstractFactory implements Serializable {

    public TestModeFactory(GameController gameController) {
        super(gameController);
    }

    /**
     * Used in testing only, reads a dummy "text file" from TestWorlds
     *
     * @return a 2-D array of Tile objects.
     */
    @Override
    public Tile[][] createWorldTiles() {
        //parse "fake file"
        char[][] tileCode = TestWorlds.getSmallMap();
        Tile[][] result = new Tile[tileCode.length][tileCode[0].length];

        for (int row = 0; row < tileCode.length; row++) {
            for (int col = 0; col < tileCode[0].length; col++) {
                char asciiCode = tileCode[row][col];
                //Image image = TileAssets.getAssetName(asciiCode).getImage();
                //image = image.getScaledInstance(Main.TILE_SIZE, Main.TILE_SIZE, Image.SCALE_FAST);
                Position position = new Position(col * Main.TILE_SIZE, row * Main.TILE_SIZE);
                result[row][col] = new Tile(asciiCode, position);
            }
        }
        return result;
    }

    @Override
    public List<Actor> createActorList() {
        //parse "fake file"
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
                //char imagePath = ActorAssets.getAsciiCode();
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
    public Coin createCoin() {
        return null;
    }

    @Override
    public Inventory createInventory(boolean pickedUP, int xPos, int yPos) {
        boolean collidable;
        boolean drawable;
        if (pickedUP) {
            collidable = false;
            drawable = false;
        } else {
            collidable = true;
            drawable = true;
        }
        int size = Main.ITEM_SIZE;
        Position position = new Position(xPos, yPos);
        char imagePath = ActorAssets.INVENTORY.getAsciiCode();
        Inventory inventory = new Inventory(ID.CONTAINER, position, imagePath, collidable, drawable, size, null);

        return inventory;
    }


    @Override
    public Collectable createCollectable() {
        return null;
    }

    @Override
    public Enemy createEnemyActor(int xPos, int yPos) {
    	Position pos = new Position(xPos, yPos);
        char imagePath = ActorAssets.ENEMY.getAsciiCode();
        int size = Main.ITEM_SIZE;
        Enemy enemy = new Enemy(ID.ENEMY, pos, imagePath, true, true, size);
        return enemy;
    }


    @Override
    public Key createKey(int xPos, int yPos) {
        Position pos = new Position(xPos, yPos);
        char imagePath = ActorAssets.KEY.getAsciiCode();
        int size = Main.ITEM_SIZE;
        Key key = new Key(ID.KEY, pos, imagePath, true, true, size);
        return key;
    }

	@Override
	public Player createPlayerActor(GameController gameController) {
	     MovementStrategy ms = new PlayerMoveStrategy(gameController);
	        Position loc = new Position(5, 5);
	        char imagePath= ActorAssets.PLAYER1.getAsciiCode();
	        Player player = new Player(ID.PLAYER, loc, imagePath, true, true, Main.PLAYER_SIZE);
	        player.setMoveStrat(ms);
	//        player.setInventory(createInventory(true, 10, 10));
	        return player;
	}

//	@Override
//	public Player createPlayerActor(GameController gameController,
//			String username, InetAddress ipAddress, int port, int playernum) {
//		// TODO Auto-generated method stub
//		return null;
//	}


}
