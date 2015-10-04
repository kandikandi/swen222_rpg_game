package save.player;

import java.awt.Image;

import control.GameController;
import model.ID;
import model.Position;

/**
 * Created on 04/10/2015
 *
 * @author Bonnie Liao
 *
 */
public class AdaptedPlayer {

	private ID id;
	private Position position;
	private Image image;
	private boolean collidable;
	private boolean drawable;
	private int boundingBoxSize;
	private GameController gameController;

	public ID getId() {
		return id;
	}

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	public void setId(ID id) {
		this.id = id;
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

	public int getBoundingBoxSize() {
		return boundingBoxSize;
	}

	public void setBoundingBoxSize(int boundingBoxSize) {
		this.boundingBoxSize = boundingBoxSize;
	}

	public GameController getGameController() {
		return gameController;
	}

	public void setGameController(GameController gameController) {
		this.gameController = gameController;
	}

}
