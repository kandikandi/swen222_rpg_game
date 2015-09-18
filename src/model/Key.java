package model;

import java.awt.Image;

public class Key extends Collectable {

	public Key(ID id, Position position, Image image, boolean collidable, boolean drawable, int boundingBoxSize) {
		super(id, position, image, collidable, drawable, boundingBoxSize);

	}

	public void useInDoor(){
		drawable = false;
		collidable = false;
	}






}
