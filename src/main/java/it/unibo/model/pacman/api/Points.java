package it.unibo.model.pacman.api;

import it.unibo.model.api.GameObject;

/**
 * This interface models the points of the game.
 */
public interface Points extends GameObject {
    /**
     * Remove points from the current points.
     * 
     * @param points the points to remove.
     */
    void removePoints(int points);

    /**
     * Add points to the current points.
     * 
     * @param points the points to add.
     */
    void addPoints(int points);

    /**
     * Return the current points.
     * 
     * @return the current points.
     */
    int getPoints();
}
