package view;

import control.ClientControl;
import control.Main;
import model.Actor;
import model.GameState;
import model.Player;
import model.Tile;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by cuan on 9/19/15.
 */
public class GameCamera {
    //private GameState gameState;
    //private Player playerActor;
    //private Tile[][] worldTiles;
    //private List<Tile> viewTiles;
    //private GameClient client;
    //private List<Actor> worldActors;


    public GameCamera(/*GameState gameState, GameClient client*/) {
       /* this.client = client;
        this.gameState = gameState;
        //playerActor = gameState.findPlayer(client.getClientNum());
        worldTiles = gameState.getWorld();
        viewTiles = new ArrayList<>();
*/
    }

    /**
     *
     */
    public List<Tile> getTileView(GameState gameState, int playerNum) {
        List<Tile> viewTiles = new ArrayList<>();
        Tile[][] worldTiles = gameState.getWorld();
        Player playerActor = gameState.findPlayer(playerNum);
        if(playerActor == null ){ return null; }
        int colMin, colMax, colCeiling, rowMin, rowMax, rowCeiling;
        int playerX = playerActor.getPosition().getxPos();
        int playerY = playerActor.getPosition().getyPos();
        colCeiling = worldTiles[0].length;
        rowCeiling = worldTiles.length;

        colMin = calculateColMin(playerX);
        colMax = calculateColMax(playerX, colCeiling);
        rowMin = calculateRowMin(playerY);
        rowMax = calculateRowMax(playerY, rowCeiling);

        for (int i = colMin; i < colMax; i++) {
            for (int j = rowMin; j < rowMax; j++) {
                viewTiles.add(worldTiles[j][i]);
            }
        }
        return viewTiles;

    }

    public List<Actor> getActorView(GameState gameState, int playerNum) {

        List<Actor> viewActors = new ArrayList<>();
        List<Actor> worldActors = gameState.getActors();
        final Actor playerActor = gameState.findPlayer(playerNum);
        if(playerActor == null ){
            //System.out.println("GameCamera getActorView has null playerActor");
            return null;
        }

        final int hWidth = Main.C_WIDTH / 2;
        final int hHeight = Main.C_HEIGHT / 2;
        final int tSize = Main.TILE_SIZE;
        final int playerXPos = playerActor.getPosition().getxPos();
        final int PlayerYPos = playerActor.getPosition().getyPos();
        final int minX = playerXPos - hWidth - tSize;
        final int maxX = playerXPos + hWidth;
        final int minY = PlayerYPos - hHeight - tSize;
        final int maxY = PlayerYPos + hWidth;
        for (Actor actor : worldActors) {
            final int actorXPos = actor.getPosition().getxPos();
            final int actorYPos = actor.getPosition().getyPos();
            if (actorXPos < minX) {
                continue;
            } else if (actorXPos > maxX) {
               continue;
            } else if (actorYPos < minY) {
               continue;
            } else if (actorYPos > maxY) {
                //System.out.println("GameCamera filtering actor: "+actor.getAsciiCode()+" > yPos: "+actorYPos+" LIMIT: "+maxY);
                continue;
            }
            viewActors.add(actor);
        }

        return viewActors;
    }

    private int calculateRowMin(int playerY) {
        int result = -2 + (playerY / Main.TILE_SIZE) - (Main.C_HEIGHT / Main.TILE_SIZE / 2);
        if (result < 0) {
            result = 0;
        }
        return result;
    }

    private int calculateRowMax(int playerY, int rowCeiling) {
        int result = 2 + (playerY / Main.TILE_SIZE) + (Main.C_HEIGHT / Main.TILE_SIZE / 2);
        if (result > rowCeiling) {
            result = rowCeiling;
        }
        return result;
    }

    private int calculateColMin(int playerX) {
        int result = -2 + (playerX / Main.TILE_SIZE) - (Main.C_WIDTH / Main.TILE_SIZE / 2);
        if (result < 0) {
            result = 0;
        }
        return result;
    }

    private int calculateColMax(int playerX, int colCeiling) {
        int result = 2 + (playerX / Main.TILE_SIZE) + (Main.C_WIDTH / Main.TILE_SIZE / 2);
        if (result > colCeiling) {
            result = colCeiling;
        }
        return result;
    }

}
