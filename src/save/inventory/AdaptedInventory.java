package save.inventory;

import java.util.List;

import model.Actor;
import model.Position;

public class AdaptedInventory {

	private Position position;
	private boolean collidable;
	private boolean drawable;
	private char asciiCode;
	private List<Actor> items;

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

	public List<Actor> getItems() {
		return items;
	}

	public void setItems(List<Actor> items) {
		this.items = items;
	}

}
