package it.unibo.model.physics.objectsmover.api;

import java.util.List;
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
    GameObject getApproximatedPosition(GameObject target, List<GameObject> list);
}
