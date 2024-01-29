package it.unibo.model.physics.objectsmover.api;

import it.unibo.model.api.GameObject;

/**
 * This interface models an object that can move an object to the position of another object.
 */
public interface ObjectMover {
    /**
     * Method to move an object to the position of another object.
     * @param toMove the object to move
     * @param target the object to reach
     */
    void move(GameObject toMove, GameObject target);
}
