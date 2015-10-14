package view;

import control.GlobalConst;
import model.*;
import java.util.Comparator;

/**
 * Created by cuan on 10/12/15.
 */
public class RotationCalculator {

    public static Comparator<Tile> getTileComparator(){
        Comparator<Tile> result;
        switch (GlobalConst.ROTATION){
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
        switch (GlobalConst.ROTATION){
            case 0:
                result = northActorComparator();
                break;
            case 1:
                result = eastActorComparator();
                break;
            case 2:
                result = southActorComparator();
                break;
            case 3:
                result = westActorComparator();
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
    private static Comparator<Actor> eastActorComparator(){
        return new Comparator<Actor>() {
            @Override
            public int compare(Actor o1, Actor o2) {
                BoundingBox bboxA = o1.getBoundingBox();
                BoundingBox bboxB = o2.getBoundingBox();
                if(bboxA.getMaxX() < bboxB.getMaxX()){return 1;}
                else if(bboxA.getMaxX() > bboxB.getMaxX()){return -1;}
                else if(bboxA.getMaxY() < bboxB.getMaxY()){return 1;}
                else if(bboxA.getMaxY() > bboxB.getMaxY()){return -1;}
                return 0;
            }
        };
    }
    private static Comparator<Actor> westActorComparator(){
        return new Comparator<Actor>() {
            @Override
            public int compare(Actor o1, Actor o2) {
                BoundingBox bboxA = o1.getBoundingBox();
                BoundingBox bboxB = o2.getBoundingBox();
                if(bboxA.getMaxX() > bboxB.getMaxX()){return 1;}
                else if(bboxA.getMaxX() < bboxB.getMaxX()){return -1;}
                else if(bboxA.getMaxY() > bboxB.getMaxY()){return 1;}
                else if(bboxA.getMaxY() < bboxB.getMaxY()){return -1;}
                return 0;
            }
        };
    }

    public static int getScreenX(int relativeX, int relativeY, int width, int height){
        int HALF_C_WIDTH = GlobalConst.C_WIDTH/2;
        int screenX;
        switch (GlobalConst.ROTATION){
            case 0:
                screenX = relativeX + HALF_C_WIDTH;
                break;
            case 1:
                screenX = relativeY+ HALF_C_WIDTH;
                break;
            case 2:
                screenX = (relativeX*-1) - width + HALF_C_WIDTH;
                break;
            case 3:
                screenX = (relativeY*-1) - height + HALF_C_WIDTH;
                break;
            default:
                screenX = relativeX + HALF_C_WIDTH;
                break;
        }
        return screenX;
    }

    public static int getScreenY(int relativeX, int relativeY, int width, int height){
        int HALF_C_HEIGHT = GlobalConst.C_HEIGHT/2;
        int screenY;
        switch (GlobalConst.ROTATION){
            case 0:
                screenY = relativeY + HALF_C_HEIGHT;
                break;
            case 1:
                screenY = (relativeX*-1)-width + HALF_C_HEIGHT;
                break;
            case 2:
                screenY = (relativeY*-1)-height + HALF_C_HEIGHT;
                break;
            case 3:
                screenY = relativeX + HALF_C_HEIGHT;
                break;
            default:
                screenY = relativeY + HALF_C_HEIGHT;
                break;
        }
        return screenY;
    }

}
