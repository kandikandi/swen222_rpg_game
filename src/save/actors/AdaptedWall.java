package save.actors;

import javax.xml.bind.annotation.XmlRootElement;

import model.Position;

public class AdaptedWall {
	private Position position;
	private boolean drawable;
	private char asciiCode;
	private String actorDescription;

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
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

}
