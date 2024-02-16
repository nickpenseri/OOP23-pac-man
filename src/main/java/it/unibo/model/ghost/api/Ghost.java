package it.unibo.model.ghost.api;
import it.unibo.model.api.Character;

/**
 * Interface for the ghost.
 */
public interface Ghost extends Character {

    /**
     * sets the state of the ghost.
     * @param state the state to set
     */
    void setState(GhostState state);

    /**
     * gets the state of the ghost.
     * @return the state of the ghost
     */
    GhostState getState();

    /**
     * Reset the behaviour of the ghost, it will search for the target again.
     */
    void resetBehaviour();
}
