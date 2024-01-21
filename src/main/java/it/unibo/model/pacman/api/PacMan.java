package it.unibo.model.pacman.api;

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
     */
    void removeLife();

    /**
     * Increases the actual points gained by pacman of a specific quantity.
     * @param points the number of points to be added
     */
    void addPoints(int points);

    /**
     * Decreases the actual points gained by pacman of a specific quantity.
     * @param points the number of points to be detracted
     */
    void removePoints(int points);
}
