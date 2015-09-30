package model;

import control.GameController;
import system.GameSystem;
import java.awt.*;
import java.util.List;

/**
 * Created by cuan on 9/30/15.
 *
 * This class will be used to check collisions between actors
 */
public class SCollision implements GameSystem {

    private final GameController gameController;

    public SCollision(GameController gameController) {
        this.gameController = gameController;
    }


    /**
     * n^2 operation checking each actors
     * not too sure if its needed, as the collision detection could be
     * calculated only when an actor moves
     */
    @Override
    public void performSystem() {
        List<Actor> actors = gameController.getAllActors();

        for (int i = 1; i < actors.size(); i++) {
            Actor actorA = actors.get(i);
            Rectangle bBoxA = actorA.getBoundingBox();
            if (actorA instanceof ActorStrategy) {
                Position pos = getProposedPosition((ActorStrategy) actorA);
                bBoxA = new Rectangle(pos.getxPos(), pos.getyPos(), actorA.boundingBoxSize, actorA.boundingBoxSize);
            }


            for (int j = i+1; j < actors.size(); j++) {
                Actor actorB = actors.get(j);
                Rectangle bBoxB = actorB.getBoundingBox();
                if (actorB instanceof ActorStrategy) {
                    Position pos = getProposedPosition((ActorStrategy)actorB);
                    bBoxB = new Rectangle(pos.getxPos(), pos.getyPos(), actorB.boundingBoxSize, actorB.boundingBoxSize);
                }

                if (bBoxA.intersects(bBoxB) || bBoxB.intersects(bBoxA)) {
                    System.out.println("SCollision: "+actorA.id + ": " + i + "  " + actorB.getID() + ": " + j);
                    if(actorA.isCollidable() && actorB.isCollidable()){
                        actorA.collide(actorB);
                        actorB.collide(actorA);
                        //TODO: work out how to best remove collectable-actor from GameState actor list
                    }
                }
            }
        }
    }


    /**
     * This method returns a position based on the instantaneous direction
     */
    public Position getProposedPosition(ActorStrategy actor) {

        if (!actor.isMoving()) {
            //System.out.println("SCollision: StratAct "+actor.getID()+": not moving ---------");
            return actor.getPosition();
        }
        //System.out.println("SCollision: StratAct "+actor.getID()+": moving ++++++++++");
        Position newPosition = new Position(actor.getPosition().getxPos(),
                actor.getPosition().getyPos());
        switch (actor.getDirection()) {
            case UP:
                newPosition.setyPos(actor.getPosition().getyPos() - actor.getSpeed());
                break;
            case DOWN:
                newPosition.setyPos(actor.getPosition().getyPos() + actor.getSpeed());
                break;
            case LEFT:
                newPosition.setxPos(actor.getPosition().getxPos() - actor.getSpeed());
                break;
            case RIGHT:
                newPosition.setxPos(actor.getPosition().getxPos() + actor.getSpeed());
                break;
        }
        return newPosition;

    }

}

