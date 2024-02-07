package it.unibo.model.pacman;

import java.awt.Dimension;
import java.awt.Point;

/**
 * This class is used to test the behaviour of objects of the class PacManWalls.
 * @see PacManWalls
 */
class TestWallsPacMan {
    private static final int STARTING_LIVES = 3;
    private static final int HEIGTH = 50;
    private static final int WIDTH = 50;
    private static final Point STARTING_POS = new Point(0, 0);
    private static final Dimension DIMENSION = new Dimension(WIDTH, HEIGTH);
    private static final double BASE_SPEED = 100.0;
    private static final long ELAPSED = 20;
    private static final int MOVEMENT = (int) (BASE_SPEED * ELAPSED / 1000);
    
}
