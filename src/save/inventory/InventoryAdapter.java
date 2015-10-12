package save.inventory;

import javax.xml.bind.annotation.adapters.XmlAdapter;

import model.Collectable;
import model.Inventory;

public class InventoryAdapter extends XmlAdapter<AdaptedInventory, Inventory> {

	@Override
	public Inventory unmarshal(AdaptedInventory ai) throws Exception {
		return new Inventory(ai.getPosition(), ai.getAsciiCode(),
				ai.isCollidable(), ai.isDrawable(), (Collectable[]) ai
						.getItems().toArray());
	}

	@Override
	public AdaptedInventory marshal(Inventory i) throws Exception {
		AdaptedInventory ai = new AdaptedInventory();
		if(i != null){
			ai.setPosition(i.getPosition());
			ai.setCollidable(i.isCollidable());
			ai.setDrawable(i.isDrawable());
			ai.setAsciiCode(i.getAsciiCode());
			ai.setItems(i.returnContents());
		}
		return ai;
	}

}
