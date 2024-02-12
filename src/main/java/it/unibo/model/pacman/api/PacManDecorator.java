package it.unibo.model.pacman.api;

/**
 * This interface models a PacMan that decorates another pacman, changing
 * his movement and respawn.
 */
public interface PacManDecorator extends PacMan {

    /**
     * Corrects the position of pacman, making it coherent with its limitation.
     */
    void correctPosition();
}
