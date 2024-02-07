package it.unibo.model.physics.objectsmover.api;

import it.unibo.model.api.GameObject;
import it.unibo.model.api.Character;

/**
 * This interface models an object that sets the direction of a character to move to reach a target.
 */
public interface DirectionSelector {
    /**
     * Sets the direction of a character to move to reach a target.
     * @param toMove the object to move
     * @param target the object to reach
     * @param elapsedTime the time elapsed since the last update
     */
    void setDirection(Character toMove, GameObject target, long elapsedTime);
}
