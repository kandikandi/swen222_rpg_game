package save.actors;

import model.Inventory;
import model.Position;

public class AdaptedPlayer {
	private Inventory inventory;
	private boolean hasKey;
	private boolean playerIsAttacking;
	private int attackPoints = 2;
	private int fear = 0; // if get too scared, you lose
	private boolean alive = true;
	private int bravery = 0;
	private int clientNum;
	private Position position;
	private boolean collidable;
	private boolean drawable;
	private char asciiCode;
	private String actorDescription;

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

	public String getActorDescription() {
		return actorDescription;
	}

	public void setActorDescription(String actorDescription) {
		this.actorDescription = actorDescription;
	}

	public Inventory getInventory() {
		return inventory;
	}

	public void setInventory(Inventory inventory) {
		this.inventory = inventory;
	}

	public boolean isHasKey() {
		return hasKey;
	}

	public void setHasKey(boolean hasKey) {
		this.hasKey = hasKey;
	}

	public boolean isPlayerIsAttacking() {
		return playerIsAttacking;
	}

	public void setPlayerIsAttacking(boolean playerIsAttacking) {
		this.playerIsAttacking = playerIsAttacking;
	}

	public int getAttackPoints() {
		return attackPoints;
	}

	public void setAttackPoints(int attackPoints) {
		this.attackPoints = attackPoints;
	}

	public int getFear() {
		return fear;
	}

	public void setFear(int fear) {
		this.fear = fear;
	}

	public boolean isAlive() {
		return alive;
	}

	public void setAlive(boolean alive) {
		this.alive = alive;
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

}
