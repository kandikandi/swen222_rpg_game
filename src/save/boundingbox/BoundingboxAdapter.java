package save.boundingbox;

import javax.xml.bind.annotation.adapters.XmlAdapter;

import model.BoundingBox;

public class BoundingboxAdapter extends XmlAdapter<AdaptedBoundingbox, BoundingBox> {

	@Override
	public AdaptedBoundingbox marshal(BoundingBox bb) throws Exception {
		AdaptedBoundingbox abb = new AdaptedBoundingbox();
		abb.setxOffset(bb.getXOffset());
		abb.setyOffset(bb.getYOffset());
		abb.setHeight(bb.height);
		abb.setWidth(bb.width);
		return abb;
	}

	@Override
	public BoundingBox unmarshal(AdaptedBoundingbox abb) throws Exception {
		return new BoundingBox(abb.getWidth(), abb.getHeight(), abb.getxOffset(), abb.getyOffset());
	}

}
