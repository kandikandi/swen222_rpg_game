package game;

import component.GameComponent;
import entity.Entity;
import system.GameSystem;
import system.SDraw;
import system.SMove;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by cuan on 9/9/15.
 */
public class GameController{
    private final List<Entity> entityList;
    private final List<GameSystem> systemList;


    public GameController(){
        entityList = new ArrayList<>();
        systemList = new ArrayList<>();
    }

    public Iterator<Entity> getEntityIterator(){
        return entityList.iterator();
    }

    public void executeAllSystems(){
        systemList.forEach(system.GameSystem::performSystem);

    }

    public Entity createEntity(int ID){
        Entity result = new Entity(ID);
        entityList.add(result);
        return result;
    }

    public void addSystem(GameSystem system){
        systemList.add(system);
    }

}
