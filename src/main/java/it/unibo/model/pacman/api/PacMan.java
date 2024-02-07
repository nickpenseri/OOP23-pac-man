package it.unibo.model.pacman.api;

import java.awt.Point;

import it.unibo.model.api.Character;
/**
 * This interface models an entity of pacman, the character moved from the player.
 * It has lives that are lost whenever it touches a ghost, unless it is eatable. 
 * It has points that can be gained or lost with interactions with other GameObjects.
 * @see Character
 */
public interface PacMan extends Character {
    /**
     * Gets the points gained by pacman from pickable objects and ghosts.
     * @return the points gained by pacman
     */
    int getPoints();

    /**
     * Gets pacman's remaining lives.
     * @return the number of lives remaining
     */
    int getRemainingLives();

    /**
     * Increases the number of lives.
     */
    void addLife();

    /**
     * Decreases the number of lives.
     * @throws IllegalStateException if PacMan has no lives remaining
     */
    void removeLife();

    /**
     * Increases the actual points gained by pacman of a specific quantity.
     * @param points the number of points to be added
     * @throws IllegalArgument exception if points are less than zero
     */
    void addPoints(int points);

    /**
     * Decreases the actual points gained by pacman of a specific quantity.
     * If this quantity is greater than the actual points, the final points will be zero.
     * @param points the number of points to be detracted
     * @throws IllegalArgument exception if points are less than zero
     */
    void removePoints(int points);

    /**
     * Changes PacMan's position to the given Point and resets the direction.
     * @param spawnPoint new PacMan's position
     */
    void respawn(Point spawnPoint);
}
