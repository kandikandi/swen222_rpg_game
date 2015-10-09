package model;

import java.awt.*;

/**
 * Created by lategacuan on 9/10/15.
 */
public class BoundingBox extends Rectangle {

    private Position position;

    BoundingBox(Position position,int width,int height, int offsetX, int offsetY){
        super(position.getxPos()+offsetX,position.getyPos()+offsetY,width,height);
        this.position = position;
    }

    BoundingBox(Position position, int width, int height){
        super(position.getxPos(), position.getyPos(), width,height);
        this.position = position;
    }

}
