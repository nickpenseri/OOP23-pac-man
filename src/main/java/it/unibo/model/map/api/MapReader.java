package it.unibo.model.map.api;

/**
 * interface that reads a matrix from a file.
 */
public interface MapReader {

    /**
     * method that returns the game map.
     * @return the game map returns.
     */
    int[][] getMap();

    /**
     * method which, given a file path, reads it.
     * @param pathFileName path to the file containing the two-dimensional matrix.
     */
    void readFileMap(String pathFileName);

}
