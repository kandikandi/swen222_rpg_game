package save;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import view.ActorAssets;
import model.Actor;
import model.BoundingBox;
import model.Candy;
import model.Coin;
import model.CoinBag;
import model.Collectable;
import model.Door;
import model.Enemy;
import model.GameState;
import model.Inventory;
import model.Key;
import model.Player;
import model.Position;
import model.Wall;

/**
 * Created on 14/10/15
 *
 * Class for the loading of the game state
 *
 * @author Bonnie Liao
 *
 */
public class Parser {

	/**
	 * Method used to start the initial loading. Load is using the SAX xml
	 * parser library
	 *
	 * @param file
	 *            to load from
	 * @return the loaded game state
	 */
	public static GameState parseFile(File file) {
		// make sax factory
		SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
		try {
			// make sax parser
			SAXParser saxParser = saxParserFactory.newSAXParser();
			// make the handler
			XMLHandler handler = new XMLHandler();
			// start the parsing
			saxParser.parse(file, handler);

			// parsing finished by now, so get the loaded list of actors
			List<Actor> actorList = handler.getActorList();

			// make a new game state and add the actors then return!
			GameState gs = new GameState(true);
			gs.setActors(actorList);
			return gs;
		} catch (ParserConfigurationException | SAXException | IOException e) {
			e.printStackTrace();
		}
		return null;
	}

}

/**
 * Created on 14/10/15
 *
 * Handler used for the loading and does the working for the different types of
 * actors
 *
 * @author Bonnie Liao
 *
 */
class XMLHandler extends DefaultHandler {

	// the list of actors
	private List<Actor> actorList = new ArrayList<Actor>();

	// fields for the different actors and objects for the parsing
	private ParserActor pactor = null;
	private ParserPosition pposition = null;
	private ParserBBox pboundingBox = null;
	private ParserActor pitem;
	private ParserActor pinven;

	// the final actors and objects
	private Actor actor;
	private Position position;
	private BoundingBox boundingBox;
	private Inventory inventory;
	private List<Actor> itemsList = new ArrayList<Actor>();
	private Actor item;

	public List<Actor> getActorList() {
		return actorList;
	}

	// normal actors
	private boolean bascii = false;
	private boolean bcoll = false;
	private boolean bdraw = false;
	private boolean bdesc = false;

	// position
	private boolean bxpos = false;
	private boolean bypos = false;

	// bounding box
	private boolean bheight = false;
	private boolean bwidth = false;
	private boolean bxoffset = false;
	private boolean byoffset = false;

	// for coin and door
	private boolean isSpecial = false;

	// containers
	private boolean items = false;

	// collectables
	private boolean inContainer = false;

	// for door
	private boolean open = false;

	// for enemy and player
	private boolean alive = false;
	private boolean attackpoints = false;
	private boolean health = false;

	// for enemy
	private boolean moveType = false;

	// for player
	private boolean bfear = false;
	private boolean bbravery = false;
	private boolean bclientNum = false;
	private boolean hasKey = false;

	// flags for actors
	private boolean wall = false;
	private boolean coin = false;
	private boolean special = false;
	private boolean coinBag = false;
	private boolean collectable = false;
	private boolean door = false;
	private boolean enemy = false;
	private boolean key = false;
	private boolean candy = false;
	private boolean player = false;

	// flags for items
	private boolean icoin = false;
	private boolean icoinBag = false;
	private boolean icollectable = false;
	private boolean ikey = false;
	private boolean icandy = false;

	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {

		// checks the 'start' tag of the xml code and sets a flag for each set
		// of information
		if (qName.equalsIgnoreCase("actor")) {
			pactor = new ParserActor();
		} else if (qName.equalsIgnoreCase("asciicode")) {
			bascii = true;
		} else if (qName.equalsIgnoreCase("collidable")) {
			bcoll = true;
		} else if (qName.equalsIgnoreCase("description")) {
			bdesc = true;
		} else if (qName.equalsIgnoreCase("drawable")) {
			bdraw = true;
		} else if (qName.equalsIgnoreCase("position")) {
			pposition = new ParserPosition();
		} else if (qName.equalsIgnoreCase("boundingBox")) {
			pboundingBox = new ParserBBox();
		} else if (qName.equalsIgnoreCase("height")) {
			bheight = true;
		} else if (qName.equalsIgnoreCase("width")) {
			bwidth = true;
		} else if (qName.equalsIgnoreCase("xOffset")) {
			bxoffset = true;
		} else if (qName.equalsIgnoreCase("yOffset")) {
			byoffset = true;
		} else if (qName.equalsIgnoreCase("xPos")) {
			bxpos = true;
		} else if (qName.equalsIgnoreCase("yPos")) {
			bypos = true;
		} else if (qName.equalsIgnoreCase("special")) {
			special = true;
		} else if (qName.equalsIgnoreCase("item")) {
			pitem = new ParserActor();
			items = true;
		} else if (qName.equalsIgnoreCase("open")) {
			open = true;
		} else if (qName.equalsIgnoreCase("inContainer")) {
			inContainer = true;
		} else if (qName.equalsIgnoreCase("alive")) {
			alive = true;
		} else if (qName.equalsIgnoreCase("attackPoints")) {
			attackpoints = true;
		} else if (qName.equalsIgnoreCase("health")) {
			health = true;
		} else if (qName.equalsIgnoreCase("moveType")) {
			moveType = true;
		} else if (qName.equalsIgnoreCase("inventory")) {
			pinven = new ParserActor();
		} else if (qName.equalsIgnoreCase("hasKey")) {
			hasKey = true;
		} else if (qName.equalsIgnoreCase("fear")) {
			bfear = true;
		} else if (qName.equalsIgnoreCase("bravery")) {
			bbravery = true;
		} else if (qName.equalsIgnoreCase("clientNum")) {
			bclientNum = true;
		}

	}

	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		// checks the 'end' tag and makes the actors and objects depending on
		// the different flags
		if (qName.equalsIgnoreCase("actor")) {
			if (coin) {
				actor = createCoin(pactor);
				coin = false;
			} else if (coinBag) {
				actor = createCoinBag(pactor);
				coinBag = false;
			} else if (collectable) {
				actor = createCollectable(pactor);
				collectable = false;
			} else if (door) {
				actor = createDoor(pactor);
				door = false;
			} else if (enemy) {
				actor = createEnemy(pactor);
				enemy = false;
			} else if (key) {
				actor = createKey(pactor);
				key = false;
			} else if (wall) {
				actor = new Wall(position, pactor.getAsciiCode(),
						pactor.isCollidable(), pactor.isDrawable());
				actor.setDescription(pactor.getActorDescription());
				wall = false;
			} else if (candy) {
				actor = new Candy(position, pactor.getAsciiCode(),
						pactor.isCollidable(), pactor.isDrawable());
				actor.setDescription(pactor.getActorDescription());
				candy = false;
			} else if (player) {
				actor = createPlayer(pactor);
				player = false;
			} else {
				actor = new Actor(position, pactor.getAsciiCode(),
						pactor.isCollidable(), pactor.isDrawable());
				actor.setDescription(pactor.getActorDescription());
			}
			actorList.add(actor);
		} else if (qName.equalsIgnoreCase("item")) {
			if (icoin) {
				item = createCoin(pitem);
				icoin = false;
			} else if (icollectable) {
				item = createCollectable(pitem);
				icollectable = false;
			} else if (ikey) {
				item = createKey(pitem);
				ikey = false;
			} else if (icoinBag) {
				item = createCoinBag(pitem);
				icoinBag = false;
			} else if (icandy) {
				item = new Candy(position, pitem.getAsciiCode(),
						pitem.isCollidable(), pitem.isDrawable());
				item.setDescription(pitem.getActorDescription());
				icandy = false;
			}
			itemsList.add(item);
		} else if (qName.equalsIgnoreCase("position")) {
			position = new Position(pposition.getxPos(), pposition.getyPos(),
					boundingBox);
		} else if (qName.equalsIgnoreCase("boundingBox")) {
			boundingBox = new BoundingBox(pboundingBox.getWidth(),
					pboundingBox.getHeight(), pboundingBox.getxOffset(),
					pboundingBox.getyOffset());
		} else if (qName.equalsIgnoreCase("inventory")) {
			inventory = new Inventory(pinven.getPosition(),
					pinven.getAsciiCode(), pinven.isCollidable(),
					pinven.isDrawable(), new Actor[] {});
			for (Actor act : itemsList) {
				inventory.addItemToContainer(act);
			}
		}
	}

	/**
	 * Creates a player with the given info
	 *
	 * @param act
	 *            the parser actor
	 * @return actor
	 */
	private Actor createPlayer(ParserActor act) {
		Player a = new Player(position, act.getAsciiCode(), act.isCollidable(),
				act.isDrawable(), act.getClientNum());
		a.setDescription(act.getActorDescription());
		a.setInventory(inventory);
		a.setHasKey(hasKey);
		hasKey = false;
		a.setAttackPoints(act.getAttackpoints());
		a.setFear(act.getFear());
		a.setAlive(alive);
		alive = false;
		a.setBravery(act.getBravery());
		a.setClientNum(act.getClientNum());
		return a;

	}

	/**
	 * Creates a key, or special key
	 *
	 * @param act
	 *            act the parser actor
	 * @return key
	 */
	private Actor createKey(ParserActor act) {
		Key a = new Key(position, act.getAsciiCode(), act.isCollidable(),
				act.isDrawable());
		a.setDescription(act.getActorDescription());

		if (isSpecial) {
			a.isSpecial();
			isSpecial = false;
		}

		if (inContainer) {
			a.setInContainer(inContainer);
			inContainer = false;
		}
		return a;
	}

	/**
	 * Creates the enemy from the parser actor
	 *
	 * @param act
	 *            parser actor
	 * @return enemy
	 */
	private Actor createEnemy(ParserActor act) {
		Enemy a = new Enemy(position, act.getAsciiCode(), act.isCollidable(),
				act.isDrawable());
		if (alive) {
			a.setAlive(alive);
			alive = false;
		}
		a.setAttackPoints(act.getAttackpoints());
		a.setHealth(act.getHealth());
		a.setMovementType(act.getMoveType());
		return a;
	}

	/**
	 * Creates a door from the parser actor
	 *
	 * @param act
	 *            parser actor
	 * @return door
	 */
	private Actor createDoor(ParserActor act) {
		Door a = new Door(position, act.getAsciiCode(), act.isCollidable(),
				act.isDrawable());
		a.setDescription(pactor.getActorDescription());

		if (open) {
			a.setOpen();
			open = false;
		}
		if (isSpecial) {
			a.setSpecial(true);
			isSpecial = false;
		}
		return a;
	}

	/**
	 * Creates a collectable from the parser actor
	 *
	 * @param act
	 *            parser actor
	 * @return collectable
	 */
	private Actor createCollectable(ParserActor act) {
		Collectable a = new Collectable(position, act.getAsciiCode(),
				act.isCollidable(), act.isDrawable());
		a.setDescription(act.getActorDescription());
		if (inContainer) {
			a.setInContainer(inContainer);
			inContainer = false;
		}
		return a;
	}

	/**
	 * Creates a coin bag from the parser actor
	 *
	 * @param act
	 *            parser actor
	 * @return coin bag
	 */
	private CoinBag createCoinBag(ParserActor act) {
		CoinBag a = new CoinBag(position, act.getAsciiCode(),
				act.isCollidable(), act.isDrawable());
		a.setDescription(act.getActorDescription());
		for (Actor item : itemsList) {
			a.addItemToContainer(item);
		}
		return a;
	}

	/**
	 * Creates a coin from the parser actor
	 *
	 * @param act
	 *            parser actor
	 * @return coin
	 */
	private Coin createCoin(ParserActor act) {
		Coin a = new Coin(position, act.getAsciiCode(), act.isCollidable(),
				act.isDrawable());
		a.setDescription(act.getActorDescription());

		if (isSpecial) {
			a.isSpecial();
			isSpecial = false;
		}

		if (inContainer) {
			a.setInContainer(inContainer);
			inContainer = false;
		}
		return a;
	}

	@Override
	public void characters(char ch[], int start, int length)
			throws SAXException {
		String token = new String(ch, start, length);
		// based on the flags, gets the information and sets it to the parser
		// classes
		if (bascii) {
			if (items) {
				pitem.setAsciiCode((char) Integer.parseInt(token));
				setItem(pitem.getAsciiCode());
			} else {
				pactor.setAsciiCode((char) Integer.parseInt(token));
				setActor(pactor.getAsciiCode());
			}
			bascii = false;
		} else if (bcoll) {
			if (token.equals("true")) {
				if (items)
					pitem.setCollidable(true);
				else
					pactor.setCollidable(true);
			} else {
				if (items)
					pitem.setCollidable(false);
				else
					pactor.setCollidable(false);
			}
			bcoll = false;
		} else if (bdraw) {
			if (token.equals("true")) {
				if (items)
					pitem.setDrawable(true);
				else
					pactor.setDrawable(true);
			} else {
				if (items)
					pitem.setDrawable(false);
				else
					pactor.setDrawable(false);
			}
			bdraw = false;
		} else if (bdesc) {
			if (items)
				pitem.setActorDescription(token);
			else
				pactor.setActorDescription(token);
			bdesc = false;
		} else if (bxpos) {
			pposition.setxPos(Integer.parseInt(token));
			bxpos = false;
		} else if (bypos) {
			pposition.setyPos(Integer.parseInt(token));
			bypos = false;
		} else if (bheight) {
			pboundingBox.setHeight(Integer.parseInt(token));
			bheight = false;
		} else if (bwidth) {
			pboundingBox.setWidth(Integer.parseInt(token));
			bwidth = false;
		} else if (bxoffset) {
			pboundingBox.setxOffset(Integer.parseInt(token));
			bxoffset = false;
		} else if (byoffset) {
			pboundingBox.setyOffset(Integer.parseInt(token));
			byoffset = false;
		} else if (special) {
			if (token.equals("true"))
				isSpecial = true;
			else
				isSpecial = false;
			special = false;
		} else if (open) {
			if (token.equals("true"))
				open = true;
			else
				open = false;
		} else if (inContainer) {
			if (token.equals("true"))
				inContainer = true;
			else
				inContainer = false;
		} else if (alive) {
			if (token.equals("true"))
				alive = true;
			else
				alive = false;
		} else if (attackpoints) {
			pactor.setAttackpoints(Integer.parseInt(token));
			attackpoints = false;
		} else if (health) {
			pactor.setHealth(Integer.parseInt(token));
			health = false;
		} else if (moveType) {
			pactor.setMoveType(Integer.parseInt(token));
			moveType = false;
		} else if (hasKey) {
			if (token.equals("true"))
				hasKey = true;
			else
				hasKey = false;
		} else if (bfear) {
			pactor.setFear(Integer.parseInt(token));
			bfear = false;
		} else if (bbravery) {
			pactor.setBravery(Integer.parseInt(token));
			bbravery = false;
		} else if (bclientNum) {
			pactor.setClientNum(Integer.parseInt(token));
			bclientNum = false;
		}
	}

	private void setItem(char asciiCode) {
		ActorAssets asset = ActorAssets.getAssetName(asciiCode);
		switch (asset) {
		case COIN:
			icoin = true;
			break;
		case TREASURE:
			icoin = true;
			break;
		case COINBAG:
			icoinBag = true;
			break;
		case COLLECTABLE:
			icollectable = true;
			break;
		case KEY:
			ikey = true;
			break;
		case CANDY:
			icandy = true;
			break;
		}
	}

	/**
	 * sets the flags
	 *
	 * @param asciiCode
	 *            the unique asciicode code for each object
	 */
	private void setActor(char asciiCode) {
		ActorAssets asset = ActorAssets.getAssetName(asciiCode);
		switch (asset) {
		case COIN:
			coin = true;
			break;
		case TREASURE:
			coin = true;
			break;
		case COINBAG:
			coinBag = true;
			break;
		case COLLECTABLE:
			collectable = true;
			break;
		case DOOR:
			door = true;
			break;
		case SPECIAL_DOOR:
			door = true;
			break;
		case ENEMY:
			enemy = true;
			break;
		case KEY:
			key = true;
			break;
		case WALL:
			wall = true;
			break;
		case CANDY:
			candy = true;
			break;
		case PLAYER:
			player = true;
			break;
		case INVENTORY:
			break;
		}
	}
}

/**
 * Parser class
 *
 * @author Bonnie Liao
 *
 */
class ParserActor {
	private char asciiCode;
	private Position position;
	private boolean collidable;
	private boolean drawable;
	private String actorDescription;

	// for enemy and player
	private int attackpoints;
	private int health;

	// for enemy
	private int moveType;

	// for the player
	private Inventory inventory;
	private int fear;
	private int bravery;
	private int clientNum;

	public Inventory getInventory() {
		return inventory;
	}

	public void setInventory(Inventory inventory) {
		this.inventory = inventory;
	}

	public int getFear() {
		return fear;
	}

	public void setFear(int fear) {
		this.fear = fear;
	}

	public int getBravery() {
		return bravery;
	}

	public void setBravery(int bravery) {
		this.bravery = bravery;
	}

	public int getClientNum() {
		return clientNum;
	}

	public void setClientNum(int clientNum) {
		this.clientNum = clientNum;
	}

	public char getAsciiCode() {
		return asciiCode;
	}

	public int getAttackpoints() {
		return attackpoints;
	}

	public void setAttackpoints(int attackpoints) {
		this.attackpoints = attackpoints;
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public int getMoveType() {
		return moveType;
	}

	public void setMoveType(int moveType) {
		this.moveType = moveType;
	}

	public void setAsciiCode(char asciiCode) {
		this.asciiCode = asciiCode;
	}

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

	public boolean isCollidable() {
		return collidable;
	}

	public void setCollidable(boolean collidable) {
		this.collidable = collidable;
	}

	public boolean isDrawable() {
		return drawable;
	}

	public void setDrawable(boolean drawable) {
		this.drawable = drawable;
	}

	public String getActorDescription() {
		return actorDescription;
	}

	public void setActorDescription(String actorDescription) {
		this.actorDescription = actorDescription;
	}

}

class ParserPosition {
	private int xPos, yPos;
	private BoundingBox boundingBox;

	public int getxPos() {
		return xPos;
	}

	public void setxPos(int xPos) {
		this.xPos = xPos;
	}

	public int getyPos() {
		return yPos;
	}

	public void setyPos(int yPos) {
		this.yPos = yPos;
	}

	public BoundingBox getBoundingBox() {
		return boundingBox;
	}

	public void setBoundingBox(BoundingBox boundingBox) {
		this.boundingBox = boundingBox;
	}

}

class ParserBBox {
	private int xOffset;
	private int yOffset;
	private int width;
	private int height;

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getxOffset() {
		return xOffset;
	}

	public void setxOffset(int xOffset) {
		this.xOffset = xOffset;
	}

	public int getyOffset() {
		return yOffset;
	}

	public void setyOffset(int yOffset) {
		this.yOffset = yOffset;
	}

}