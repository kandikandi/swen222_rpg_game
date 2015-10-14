package view;

import control.GlobalConst;
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



    /**
     *Returns list of tiles that are near the player
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
    /**
     *Returns list of Actors that are near the player
     *
     */
    public List<Actor> getActorView(GameState gameState, int playerNum) {

        List<Actor> viewActors = new ArrayList<>();
        List<Actor> worldActors = gameState.getActors();
        final Actor playerActor = gameState.findPlayer(playerNum);
        if(playerActor == null ){
            return null;
        }
        final int hWidth = GlobalConst.C_WIDTH / 2;
        final int hHeight = GlobalConst.C_HEIGHT / 2;
        final int tSize = GlobalConst.TILE_SIZE;
        final int playerXPos = playerActor.getPosition().getxPos();
        final int PlayerYPos = playerActor.getPosition().getyPos();
        final int minX = playerXPos - hWidth - hWidth/2 + tSize;
        final int maxX = playerXPos + hWidth;
        final int minY = PlayerYPos - hHeight - hHeight;
        final int maxY = PlayerYPos + hWidth + hWidth;
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
                continue;
            }
            viewActors.add(actor);
        }
        return viewActors;
    }

    private int calculateRowMin(int playerY) {
        int result = -6 + (playerY / GlobalConst.TILE_SIZE) - (GlobalConst.C_HEIGHT / GlobalConst.TILE_SIZE / 2);
        if (result < 0) {
            result = 0;
        }
        return result;
    }

    private int calculateRowMax(int playerY, int rowCeiling) {
        int result = 6 + (playerY / GlobalConst.TILE_SIZE) + (GlobalConst.C_HEIGHT / GlobalConst.TILE_SIZE / 2);
        if (result > rowCeiling) {
            result = rowCeiling;
        }
        return result;
    }

    private int calculateColMin(int playerX) {
        int result = -6 + (playerX / GlobalConst.TILE_SIZE) - (GlobalConst.C_WIDTH / GlobalConst.TILE_SIZE / 2);
        if (result < 0) {
            result = 0;
        }
        return result;
    }

    private int calculateColMax(int playerX, int colCeiling) {
        int result = 6 + (playerX / GlobalConst.TILE_SIZE) + (GlobalConst.C_WIDTH / GlobalConst.TILE_SIZE / 2);
        if (result > colCeiling) {
            result = colCeiling;
        }
        return result;
    }

}
