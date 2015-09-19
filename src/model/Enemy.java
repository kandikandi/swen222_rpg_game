package model;

import java.awt.Image;

import control.DIR;
import control.MovementStrategy;

public class Enemy extends ActorStrategy{

	private boolean attacking;
	private int attackPoints;
	private int count; 	// just want way to alternate attacking and not attacking. Bit weird maybe.
						//Tick will increment and set attack depending on value.

	public Enemy(ID id, Position location, Image image, boolean collidable,
			boolean drawable, int boundingBoxSize) {
		super(id, location, image, collidable, drawable, boundingBoxSize);
		attackPoints = 1;
		count = 0;
	}

	@Override
	public void setMoveStrat(MovementStrategy moveStrat) {}

	@Override
	public void move(DIR dir) {}

	@Override
	public boolean canMove(DIR dir) {return false;}

	@Override
	public MovementStrategy getMoveStrat() {return null;}

	@Override
	public void tick() {
		setAttacking();
	}

	/**
	 * An enemy alternates between attacking and not attacking. The tick
	 *
	 *
	 *
	 */
	private void setAttacking() {
		count++;
		if(count<10){
			attacking = true;
		}else if(count>=10 && count<50){
			attacking = false;
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
		return attacking;
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

}
