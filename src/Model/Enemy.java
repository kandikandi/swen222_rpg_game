package Model;

import java.awt.Image;

import View.ID;


public class Enemy extends Actor{

	private boolean isAttacking;
	private boolean alive = true;
	private int attackPoints;
	private int health;
	private int count; 	// just want way to alternate attacking and not attacking. Bit weird maybe.
						//Tick will increment and set attack depending on value.

	public Enemy(ID id, Position location, char imagePath, boolean collidable,
			boolean drawable, int boundingBoxSize) {
		super(id, location, imagePath, collidable, drawable, boundingBoxSize);
		this.attackPoints = 2;
		this.health = 100;
		count = 0;
	}


	@Override
	public void tick() {
		alternateAttacking();
	}

	/**
	 * An enemy alternates between attacking and not attacking. The tick
	 *
	 *
	 *
	 */
	private void alternateAttacking() {
		count++;
		if(count<10){
			setAttack(true);
		}else if(count>=10 && count<50){
			setAttack(false);
		}else{
			count = 0;
		}
	}

	/**
	 * Getter for whether is attacking.
	 *
	 * @return
	 */
	public boolean isAttacking(){
		return isAttacking;
	}


	public void setAttack(boolean playerIsAttacking){
		this.isAttacking = playerIsAttacking;
	}


	/**
	 * Getter for attack points.
	 *
	 * @return
	 */
	public int getAttackPoints(){
		return attackPoints;
	}

	/**
	 * Setter for attack points.
	 *
	 * @return
	 */
	public void setAttackPoints(int attackPoints){
		this.attackPoints = attackPoints;
	}

	public int getHealth(){
		return health;
	}
	public void reduceHealth(int n) {
		health -= n;
		if(health<1){
			this.setCollidable(false);
			this.kill();
		}
	}

	private void kill() {
		alive = false;
		setDrawable(false);
	}


	public boolean getIsAlive(){
		return alive;
	}

	//==========================================================
	// TODO: Bonnie added these!
	/**
	 * Bonnie added this!
	 * @return
	 */
	public int getCount(){
		return count;
	}

	public void setAlive(boolean isalive) {
		this.alive = isalive;
	}


	public void setHealth(int health) {
		this.health = health;
	}


	public void setCount(int count) {
		this.count = count;
	}
	//==========================================================







}
