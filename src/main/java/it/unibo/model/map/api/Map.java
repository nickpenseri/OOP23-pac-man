package it.unibo.model.map.api;

import java.awt.Point;
import java.util.List;

/**
 * this interface is used to manage maps within the game.
 */
public interface Map {

    /**
     * method to obtain the game map.
     * @return returns the game map represented with a two-dimensional integer matrix.
     */
    int[][] getMap();

    /**
     * method to obtain the ghost fence.
     * @return returns a list of Points that represent the ghost fence.
     */
    List<Point> getFenceGhost();

    /**
     * Method to get Pac-Man's location at the start of the game.
     * @return returns a Point that represents Pacman's initial position on the game map.
     */
    Point getPacManSpawn();

    /**
     * method to obtain the boxes on the map where the collectible objects are located.
     * @return returns a Point-type list representing all the boxes on the map where objects can be collected.
     */
    List<Point> getCollectibleItems();
}
