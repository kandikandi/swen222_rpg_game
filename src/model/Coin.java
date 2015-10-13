package model;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 * A Coin is an object a player can pick up and can store in either an Inventory
 * or CoinBag.
 *
 * @author dalyandr
 *
 */
//@XmlJavaTypeAdapter(CoinAdapter.class)
public class Coin extends Collectable {

	public Coin(Position position, char imagePath, boolean collidable,
			boolean drawable) {
		super(position, imagePath, collidable, drawable);
	}
}

class CoinAdapter extends XmlAdapter<CoinAdapter, Coin>{
	private Position position;
	private char asciiCode;
	private boolean collidable;
	private boolean drawable;

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

	public char getAsciiCode() {
		return asciiCode;
	}

	public void setAsciiCode(char asciiCode) {
		this.asciiCode = asciiCode;
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

	@Override
	public Coin unmarshal(CoinAdapter ca) throws Exception {
		return new Coin(ca.position, ca.asciiCode, ca.collidable, ca.drawable);
	}

	@Override
	public CoinAdapter marshal(Coin c) throws Exception {
		CoinAdapter ca = new CoinAdapter();
		ca.setAsciiCode(c.asciiCode);
		ca.setCollidable(c.collidable);
		ca.setDrawable(c.drawable);
		ca.setPosition(c.position);
		return ca;
	}

}
