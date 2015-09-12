package model;

/**
 * Created by cuan on 9/13/15.
 *
 *
 */


@FunctionalInterface
/**
 * Any world object that moves or animates is an actor.
 * Actors should be tick(called to act) by either a GameSystem
 * or by creating a TickObserver
 */
public interface Tickable {
     void tick();
}
