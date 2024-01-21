package it.unibo.model.map.api;

/**
 * interface that reads a matrix from a file.
 */
public interface MapReader {

    /**
     * returns the map read from file.
     * @return the game map returns.
     */
    int[][] getMap();

}
