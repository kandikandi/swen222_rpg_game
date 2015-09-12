package model;


import control.DIR;
import system.GameException;

import java.awt.*;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by cuan on 9/13/15.
 */
public final class Tile extends AbstractGameObject {
    private List<AbstractGameObject> neighbours = new LinkedList<>();


    public Tile(Image image, Location loc,
                boolean passable, boolean drawable,  List<AbstractGameObject> neighbours) {
        super(ID.TILE,loc, image, passable, drawable);
        this.neighbours = neighbours;

    }

    public AbstractGameObject getNeighbour(int dir) {
        return neighbours.get(dir);
    }

    public void setNeighbour(DIR dir, AbstractGameObject neigh) {
        if (neighbours.get(dir.getValue()) == null) {
            neighbours.set(dir.getValue(), neigh);
        } else {
            try {
                throw new GameException("Tile loc:" + location.toString() + " already has neighbour");
            } catch (GameException e) {
                System.out.println(e.getMessage());
                e.printStackTrace();
            }
        }
    }


}
