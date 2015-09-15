package control;

import factory.TileFactory;
import model.Actor;
import model.Tile;
import system.GameSystem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cuan on 9/9/15.
 */
public class GameController{

    private final int NUM_KEYS = 4;
    public boolean[] keyArray = new boolean[NUM_KEYS];
    private final Tile[][] world;

    private final List<GameSystem> systemList;
    private final List<Actor> actorList;


    public GameController(){
        systemList = new ArrayList<>();
        actorList = new ArrayList<>();
        if(Main.TEST_MODE){ world = TileFactory.createTestWorld();
        }else{
            world = TileFactory.createWorld();
        }
    }



    public void executeAllSystems(){
        systemList.forEach(GameSystem::performSystem);
        for(Actor actor: actorList){
            actor.tick();
        }

    }


    public void addSystem(GameSystem system){
        systemList.add(system);
    }

    public void addActor(Actor actor){
        actorList.add(actor);
    }
    public List<Actor> getActors(){
        return actorList;
    }

    public Tile[][] getWorld() {
        return world;
    }
}
