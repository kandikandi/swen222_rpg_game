package control;

import factory.TileFactory;
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


    public GameController(){
        systemList = new ArrayList<>();
        if(Main.TEST_MODE){ world = TileFactory.createTestWorld();
        }else{
            world = TileFactory.createWorld();
        }
    }



    public void executeAllSystems(){
        systemList.forEach(GameSystem::performSystem);

    }


    public void addSystem(GameSystem system){
        systemList.add(system);
    }

    public Tile[][] getWorld() {
        return world;
    }
}
