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
}
