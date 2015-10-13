package model;

public class Candy extends Collectable {

	int fearRemoved = 30;

	public Candy(Position position, char imagePath, boolean collidable,
			boolean drawable) {
		super(position, imagePath, collidable, drawable);
	}

	public int eat(int currentFear){
		if(currentFear<fearRemoved){
			return 0;
		}else{
			return currentFear - fearRemoved;
		}
	}

}
