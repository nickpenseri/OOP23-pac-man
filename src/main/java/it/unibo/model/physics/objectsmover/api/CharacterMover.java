package it.unibo.model.physics.objectsmover.api;

import it.unibo.model.api.GameObject;
import it.unibo.model.api.Character;

/**
 * This interface models an object that moves a character to reach a target.
 */
public interface CharacterMover {
    /**
     * Sets the direction of a character to move to reach a target.
     * @param toMove the object to move
     * @param target the object to reach
     * @param elapsedTime the time elapsed since the last update
     */
    void moveCharacter(Character toMove, GameObject target, long elapsedTime);

    /**
     * Resets the direction selector.
     */
    void reset();
}
