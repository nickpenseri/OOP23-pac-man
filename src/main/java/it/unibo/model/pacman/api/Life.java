package it.unibo.model.pacman.api;

import it.unibo.model.api.GameObject;

/**
 * This interface models the life of the game.
 */
public interface Life extends GameObject {

    /**
     * Decrease the life of the player.
     */
    void decreaseLife();

    /**
     * Increase the life of the player.
     */
    void increaseLife();

    /**
     * Return the current life.
     * 
     * @return the current life.
     */
    int getNumLife();
}
