package view;

import control.Main;
import model.*;

import javax.swing.*;
import java.awt.*;
import java.util.Comparator;

/**
 * Created by cuan on 10/12/15.
 */
public class RotationCalculator {

    public static Comparator<Tile> getTileComparator(){
        Comparator<Tile> result;
        switch (Main.ROTATION){
            case 0:
                result = northTileComparator();
                break;
            case 1:
                result = northTileComparator();
                break;
            case 2:
                result = southTileComparator();
                break;
            case 3:
                result = northTileComparator();
                break;
            default:
                result = northTileComparator();
                break;
        }
        return result;
    }
    public static Comparator<Actor> getActorComparator(){
        Comparator<Actor> result;
        switch (Main.ROTATION){
            case 0:
                result = northActorComparator();
                break;
            case 1:
                result = northActorComparator();
                break;
            case 2:
                result = southActorComparator();
                break;
            case 3:
                result = northActorComparator();
                break;
            default:
                result = northActorComparator();
                break;
        }
        return result;
    }

    private static Comparator<Tile> northTileComparator(){
        return new Comparator<Tile>() {
            @Override
            public int compare(Tile o1, Tile o2) {
                Position posA =  o1.getPosition();
                Position posB = o2.getPosition();
                BoundingBox bboxA = posA.getBoundingBox();
                BoundingBox bboxB = posB.getBoundingBox();
                if(bboxA.getMaxY() > bboxB.getMaxY()){return 1;}
                else if(bboxA.getMaxY() < bboxB.getMaxY()){return -1;}
                else if(bboxA.getMaxX() > bboxB.getMaxX()){return 1;}
                else if(bboxA.getMaxX() < bboxB.getMaxX()){return -1;}
                return 0;
            }
        };
    }
    private static Comparator<Tile> southTileComparator(){
        return new Comparator<Tile>() {
            @Override
            public int compare(Tile o1, Tile o2) {
                Position posA =  o1.getPosition();
                Position posB = o2.getPosition();
                BoundingBox bboxA = posA.getBoundingBox();
                BoundingBox bboxB = posB.getBoundingBox();
                if(bboxA.getMaxY() < bboxB.getMaxY()){return 1;}
                else if(bboxA.getMaxY() > bboxB.getMaxY()){return -1;}
                else if(bboxA.getMaxX() < bboxB.getMaxX()){return 1;}
                else if(bboxA.getMaxX() > bboxB.getMaxX()){return -1;}
                return 0;
            }
        };
    }
    private static Comparator<Actor> northActorComparator(){
        return new Comparator<Actor>() {
            @Override
            public int compare(Actor o1, Actor o2) {
                BoundingBox bboxA = o1.getBoundingBox();
                BoundingBox bboxB = o2.getBoundingBox();
                if(bboxA.getMaxY() > bboxB.getMaxY()){return 1;}
                else if(bboxA.getMaxY() < bboxB.getMaxY()){return -1;}
                else if(bboxA.getMaxX() > bboxB.getMaxX()){return 1;}
                else if(bboxA.getMaxX() < bboxB.getMaxX()){return -1;}
                return 0;
            }
        };
    }
    private static Comparator<Actor> southActorComparator(){
        return new Comparator<Actor>() {
            @Override
            public int compare(Actor o1, Actor o2) {
                BoundingBox bboxA = o1.getBoundingBox();
                BoundingBox bboxB = o2.getBoundingBox();
                if(bboxA.getMaxY() < bboxB.getMaxY()){return 1;}
                else if(bboxA.getMaxY() > bboxB.getMaxY()){return -1;}
                else if(bboxA.getMaxX() < bboxB.getMaxX()){return 1;}
                else if(bboxA.getMaxX() > bboxB.getMaxX()){return -1;}
                return 0;
            }
        };
    }
  /*  public static int getScreenX(int relativeX, int relativeY, int width, int height){
        int screenX;
        switch (Main.ROTATION){
            case 0:
                screenX = relativeX;
                break;
            case 1:
                screenX = (relativeY*-1);
                break;
            case 2:
                screenX = (relativeY*-1);
                break;
            case 3:
                screenX = (relativeY*-1) - height;
                break;
            default:
                screenX = relativeX;
                break;
        }
        return screenX;
    }

    public static int getScreenY(int relativeX, int relativeY, int width, int height){
        int screenY;
        switch (Main.ROTATION){
            case 0:
                screenY = relativeY;
                break;
            case 1:
                screenY = (relativeX*-1)-width;
                break;
            case 2:
                screenY = (relativeY*-1)-height;
                break;
            case 3:
                screenY = relativeX;
                break;
            default:
                screenY = relativeY;
                break;
        }
        return screenY;
    }*/
    public static int getScreenX(int relativeX, int relativeY, int width, int height){
        int screenX;
        switch (Main.ROTATION){
            case 0:
                screenX = relativeX;
                break;
            case 1:
                screenX = relativeY;
                break;
            case 2:
                screenX = (relativeX*-1) -width;
                break;
            case 3:
                screenX = (relativeY*-1) - height;
                break;
            default:
                screenX = relativeX;
                break;
        }
        return screenX;
    }

    public static int getScreenY(int relativeX, int relativeY, int width, int height){
        int screenY;
        switch (Main.ROTATION){
            case 0:
                screenY = relativeY;
                break;
            case 1:
                screenY = (relativeX*-1)-width;
                break;
            case 2:
                screenY = (relativeY*-1)-height;
                break;
            case 3:
                screenY = relativeX;
                break;
            default:
                screenY = relativeY;
                break;
        }
        return screenY;
    }

}
