package model;

import java.util.Random;

public class Enemy extends Actor {

	private boolean isAttacking;
	private boolean alive = true;
	private int attackPoints;
	private int health;
	private int horizontalSpeed;
	private int verticalSpeed;
	private int moveType;
	private final int MAX_SPEED = 3;
	// private int randomDirection; // 1-4
	private final int startX;
	private final int startY;
	private int count; // just want way to alternate attacking and not
						// attacking. Bit weird maybe.
						// Tick will increment and set attack depending on
						// value.

	public Enemy(Position location, char imagePath, boolean collidable,
				 boolean drawable) {
		super(location, imagePath, collidable, drawable);
		this.attackPoints = 1;
		this.health = 100;
		count = 0;
		this.startX = location.getxPos();
		this.startY = location.getyPos();
		Random rn = new Random();
		this.moveType = rn.nextInt(3) + 1;
		setRandomSpeeds();
	}

	// @Override
	public void tick(GameState gameState) {
		alternateAttacking();
		if (moveType == 1) {
			backAndForthMove(gameState);
		} else if (moveType == 2) {
			zigZagMove(gameState);
		} else if (moveType == 3) {
			randomMove(gameState);
		}
		// }else if(moveType==4){
		// sentryMove(gameState);
		// }
		else {

		}

	}

	/**
	 * Set enemy movement type. 1->BackAndforth, 2-> zigZag, 3->Random
	 *
	 * @param type
	 */
	public void setMovementType(int type){
		this.moveType = type;
	}

	private void randomMove(GameState gameState) {
		Collision collision = new Collision(gameState);

		Position proposedPosition = new Position(getX() + horizontalSpeed,
				getY() + verticalSpeed);

		if (collision.canMoveEnemy(this, proposedPosition)) {
			this.setPosition(proposedPosition);
		} else {
			setRandomSpeeds();
		}
	}

	@Override
	public void tick() {
	}

	private void backAndForthMove(GameState gameState) {
		Collision collision = new Collision(gameState);
		Position proposedPosition = new Position(getX() + horizontalSpeed,
				getY());

		if (collision.canMoveEnemy(this, proposedPosition)) {
			this.setPosition(proposedPosition);
		} else {
			horizontalSpeed = -horizontalSpeed;
		}

		if (getX() > startX + 100 || getX() < startX - 100) {
			horizontalSpeed = -horizontalSpeed;
		}
		if(getX()==0 && getY()==0){
			setRandomSpeeds();
		}
	}

	private void zigZagMove(GameState gameState) {
		Collision collision = new Collision(gameState);
		Position proposedPosition = new Position(getX() + horizontalSpeed,
				getY() + verticalSpeed);

		if (collision.canMoveEnemy(this, proposedPosition)) {
			this.setPosition(proposedPosition);
		} else {
			horizontalSpeed = -horizontalSpeed;
		}

		if (getX() > startX + 100 || getX() < startX - 100) {
			horizontalSpeed = -horizontalSpeed;
		}
		if (getY() > startY + 10 || getY() < startY - 10) {
			verticalSpeed = -verticalSpeed;
		}
		if(getX()==0 && getY()==0){
			setRandomSpeeds();
		}
	}

	// private void sentryMove(GameState gameState){
	// if (getX() > startX + 100) {
	// horizontalSpeed = 0;
	// verticalSpeed=1;
	// }else if(getY() > startY + 100){
	// verticalSpeed=0;
	// horizontalSpeed=-1;
	// }else if(getX() < startX - 100) {
	// horizontalSpeed = 0;
	// verticalSpeed=-1;
	// }else if(getY() < startY - 100) {
	// horizontalSpeed = 1;
	// verticalSpeed=0;
	// }
	// }

	private int getX() {
		return this.getPosition().getxPos();
	}

	private void setRandomSpeeds() {
		Random rn = new Random();
		this.horizontalSpeed = rn.nextInt(4) - 2; //want range pos to neg
		this.verticalSpeed = rn.nextInt(4) - 2;
		if (horizontalSpeed == 0 || verticalSpeed == 0) {
			setRandomSpeeds();
		}

	}

	private int getY() {
		return this.getPosition().getyPos();
	}

	/**
	 * An enemy alternates between attacking and not attacking. The tick
	 *
	 *
	 *
	 */
	private void alternateAttacking() {
		count++;
		if (count < 100) {
			setAttack(true);
		} else if (count >= 10 && count < 500) {
			setAttack(false);
		} else {
			count = 0;
		}
	}

	/**
	 * Getter for whether is attacking.
	 *
	 * @return
	 */
	public boolean isAttacking() {
		return isAttacking;
	}

	public void setAttack(boolean playerIsAttacking) {
		this.isAttacking = playerIsAttacking;
	}

	/**
	 * Getter for attack points.
	 *
	 * @return
	 */
	public int getAttackPoints() {
		return attackPoints;
	}

	/**
	 * Setter for attack points.
	 *
	 * @return
	 */
	public void setAttackPoints(int attackPoints) {
		this.attackPoints = attackPoints;
	}

	/**
	 * Getter for health.
	 *
	 * @return
	 */
	public int getHealth() {
		return health;
	}

	/**
	 * This method reduces the Enemy's health
	 * by the amount enterd to the parameter.
	 *
	 * @param n
	 */
	public void reduceHealth(int n) {
		health -= n;
		if (health < 1) {
			this.setCollidable(false);
			this.kill();
		}
	}

	/**
	 * This method sets alive to false and removes Enemy's
	 * visibility and collidability.
	 *
	 */
	private void kill() {
		alive = false;
		this.setDrawable(false);
		this.setCollidable(false);
	}

	/**
	 * Getter for alive status.
	 *
	 * @return
	 */
	public boolean getIsAlive() {
		return alive;
	}

	// ==========================================================
	// TODO: Bonnie added these!


	/**
	 * Bonnie added this!
	 *
	 * @return
	 */

	/**
	 * Getter for count.
	 *
	 * @return
	 */
	public int getCount() {
		return count;
	}

	/**
	 * Setter for count.
	 *
	 * @param count
	 */
	public void setCount(int count) {
		this.count = count;
	}


	/**
	 * Setter for alive status.
	 *
	 * @param isalive
	 */
	public void setAlive(boolean isalive) {
		this.alive = isalive;
	}

	/**
	 * Setter for health.
	 *
	 * @param health
	 */
	public void setHealth(int health) {
		this.health = health;
	}


	// ==========================================================

}
