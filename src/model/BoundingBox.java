package model;

import java.awt.*;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import save.boundingbox.BoundingboxAdapter;

/**
 * Created by lategacuan on 9/10/15.
 * This class will be used to collisions as well as the painter algro in renderer
 *
 */
@XmlRootElement(name = "boundingbox")
@XmlJavaTypeAdapter(BoundingboxAdapter.class)
public class BoundingBox extends Rectangle {
    private final int xOffset;
    private final int yOffset;

    //TODO: Bonnie added the public
    public BoundingBox(int width, int height, int xOffset, int yOffset){
        super(width,height);
        this.xOffset = xOffset;
        this.yOffset = yOffset;
    }
    BoundingBox(int width, int height){
        super(width,height);
        this.xOffset = 0;
        this.yOffset = 0;
    }
    
    public int getXOffset(){
    	return xOffset;
    }
    
    public int getYOffset(){
    	return yOffset;
    }
}
