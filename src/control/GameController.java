package control;

import factory.TileFactory;
import model.Actor;
import model.Tile;
import system.GameSystem;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is designed to control the game loop
 * This is where maybe we can keep the collections of
 * game objects
 *
 *
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


    //Call tick on all actors to update animation state or location
    //them execute systems to draw and send to network maybe
    public void executeAllSystems(){
        actorList.forEach(model.Actor::tick);
        systemList.forEach(GameSystem::performSystem);

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
