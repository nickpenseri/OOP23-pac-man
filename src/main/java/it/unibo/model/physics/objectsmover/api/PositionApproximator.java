package it.unibo.model.physics.objectsmover.api;

import java.util.Optional;
import java.util.Set;

import it.unibo.model.api.GameObject;

/**
 * This interface models an object that approximates the position of a target in a list of game objects.
 */
public interface PositionApproximator {
    /**
     * Approximates the position of a target in a list of game objects.
     * @param target the target to approximate
     * @param list the list of game objects
     * @return the game object that approximates the position of the target
     */
    Optional<GameObject> getApproximatedTarget(GameObject target, Set<GameObject> list);

    /**
     * Returns the distance between two game objects.
     * @param object1  the first game object 
     * @param object2  the second game object
     * @return the distance between the two game objects
     */
    Double getDistance(GameObject object1, GameObject object2);

    /**
     * Returns true if the distance between two game objects is close enough.
     * @param object1  the first game object 
     * @param object2  the second game object
     * @param tollerance the tollerance to consider
     * @return true if the distance between the two game objects is close enough
     */
    Boolean isPositionCloseEnough(GameObject object1, GameObject object2, Double tollerance);
}
