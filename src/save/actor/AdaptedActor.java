package save.actor;

import model.BoundingBox;
import model.Inventory;
import model.Position;

/**
 * Created on 05/10/2015
 *
 * @author Bonnie Liao
 *
 *         Classes used for saving the different kinds of actors. Doesn't have a
 *         constructor and only has getters and setters for the purpose of
 *         saving the state of an object
 */
public class AdaptedActor {

	// for normal actors
	private Position position;
	private boolean collidable;
	private boolean drawable;
	private char asciiCode;

	// for door
	private boolean open;

	// for the enemy
	private int health;
	private int count;

	// for the player
	private boolean hasKey;
	private int fear;
	private int bravery;
	private int clientNum;

	private Inventory inventory;

	// for both player and enemy
	private int attackPoints;
	private boolean alive;

	// so we know what type to instantiate
	private String type;

	public Inventory getInventory() {
		return inventory;
	}

	public void setInventory(Inventory inventory) {
		this.inventory = inventory;
	}

	public boolean hasKey() {
		return hasKey;
	}

	public void setHasKey(boolean hasKey) {
		this.hasKey = hasKey;
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

	public int getAttackPoints() {
		return attackPoints;
	}

	public void setAttackPoints(int attackPoints) {
		this.attackPoints = attackPoints;
	}

	public boolean isAlive() {
		return alive;
	}

	public void setAlive(boolean alive) {
		this.alive = alive;
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public boolean isOpen() {
		return open;
	}

	public void setOpen(boolean open) {
		this.open = open;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
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

	public char getAsciiCode() {
		return asciiCode;
	}

	public void setAsciiCode(char asciiCode) {
		this.asciiCode = asciiCode;
	}

}
