package model;

import java.awt.Image;

public class Door extends GameObject {

	boolean open;

	public Door(ID id, Position position, Image image, boolean collidable,
			boolean drawable, int boundingBoxSize) {
		super(id, position, image, collidable, drawable, boundingBoxSize);

	}

	public void open() {
		open = true;
		collidable = false;
	}

}
