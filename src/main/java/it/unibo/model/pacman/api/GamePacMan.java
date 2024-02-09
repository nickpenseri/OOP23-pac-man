package it.unibo.model.pacman.api;


import java.awt.Point;
import java.util.List;

import it.unibo.model.api.GameObject;

/**
 * This interface represents a PacMan which has a map that can be changed.
 */
public interface GamePacMan extends PacManDecorator {
    /**
     * Changes the map of the pacman and respawns it.
     * @param walls the walls of the new map
     * @param spawnPoint the spawnPoint of the new map
     */
    void changeMap(List<GameObject> walls, Point spawnPoint);
}
