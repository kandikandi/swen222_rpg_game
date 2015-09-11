package game;

import entity.GameEntity;
import system.GameSystem;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by cuan on 9/9/15.
 */
public class GameController{

    private final int NUM_KEYS = 4;
    public boolean[] keyArray = new boolean[NUM_KEYS];

    private final List<GameEntity> entityList;
    private final List<GameSystem> systemList;


    public GameController(){
        entityList = new ArrayList<>();
        systemList = new ArrayList<>();
    }

    public Iterator<GameEntity> getEntityIterator(){
        return entityList.iterator();
    }

    public void executeAllSystems(){
        systemList.forEach(system.GameSystem::performSystem);

    }

    public GameEntity createEntity(int ID){
        GameEntity result = new GameEntity(ID);
        entityList.add(result);
        return result;
    }

    public void addSystem(GameSystem system){
        systemList.add(system);
    }

}
