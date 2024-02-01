package it.unibo.model.pacman;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.awt.Dimension;
import java.awt.Point;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.model.api.Direction;
import it.unibo.model.pacman.api.PacMan;
import it.unibo.model.pacman.impl.PacManBordered;
import it.unibo.model.pacman.impl.PacManImpl;

/**
 * This class is used to test the behaviour of an object of the class
 * PacManBordered.
 * 
 * @see PacManBordered
 */
class TestBorderedPacMan {

    private static final int STARTING_LIVES = 3;
    private static final int HEIGHTH = 50;
    private static final int WIDTH = 50;
    private static final int BORDER_UP = 1000;
    private static final int BORDER_RIGHT = 1000;
    private static final Point STARTING_POS = new Point(0, 0);
    private static final Dimension DIMENSION = new Dimension(WIDTH, HEIGHTH);
    private static final double BASE_SPEED = 100.0;
    private static final long ELAPSED = 1000;
    private static final int MOVEMENT = (int) (BASE_SPEED * (ELAPSED / 1000.0));

    private PacMan pacman = new PacManBordered(new PacManImpl(STARTING_LIVES, DIMENSION, BASE_SPEED, STARTING_POS),
            BORDER_UP,
            BORDER_RIGHT);

    /**
     * Creates an object of class PacManBordered to test its behaviour, this objects
     * decorates an object of PacManImpl.
     */
    @BeforeEach
    public void setUp() {
        pacman = new PacManBordered(new PacManImpl(STARTING_LIVES, DIMENSION, BASE_SPEED, STARTING_POS),
                BORDER_UP,
                BORDER_RIGHT);
    }

    /**
     * Tests the initialization of the object of PacManBordered.
     */
    @Test
    void testInitialization() {
        assertEquals(STARTING_POS, pacman.getPosition());
        assertEquals(DIMENSION, pacman.getDimension());
        assertEquals(STARTING_LIVES, pacman.getRemainingLives());
        assertEquals(0, pacman.getSpeedLevel());
    }

    /**
     * Tests pacman's movement across the borders.
     */
    @Test
    void testMovement() {
        pacman.updateState(ELAPSED);
        assertEquals(STARTING_POS, pacman.getPosition());
        pacman.setDirection(Direction.UP);
        pacman.updateState(ELAPSED);
        assertEquals(STARTING_POS, new Point(0, 0));
        assertEquals(new Point((int) STARTING_POS.getX(), (int) STARTING_POS.getY() + MOVEMENT), pacman.getPosition());
        pacman.setDirection(Direction.DOWN);
        pacman.updateState(ELAPSED);
        assertEquals(STARTING_POS, pacman.getPosition());
        pacman.resetDirection();
        pacman.updateState(ELAPSED);
        assertEquals(STARTING_POS, pacman.getPosition());
        pacman.setDirection(Direction.LEFT);
        pacman.updateState(ELAPSED);
        assertEquals(new Point(BORDER_RIGHT - MOVEMENT, (int) STARTING_POS.getY()), pacman.getPosition());
        pacman.setDirection(Direction.RIGHT);
        pacman.updateState(ELAPSED);
        assertEquals(STARTING_POS, pacman.getPosition());
        pacman.setDirection(Direction.DOWN);
        pacman.updateState(ELAPSED);
        assertEquals(new Point((int) STARTING_POS.getX(), BORDER_UP - MOVEMENT), pacman.getPosition());
    }

    /**
     * Tests pacman's respawn and exception throwing in case of a respawn with position out of borders.
     */
    @Test
    void testRespawn() {
        pacman.setDirection(Direction.UP);
        pacman.updateState(ELAPSED);
        pacman.respawn(STARTING_POS);
        pacman.updateState(ELAPSED);
        assertEquals(STARTING_POS, pacman.getPosition());
        assertThrows(IllegalArgumentException.class, () -> pacman.respawn(new Point(2 * BORDER_RIGHT, 0)));
        assertThrows(IllegalArgumentException.class, () -> pacman.respawn(new Point(0, 2 * BORDER_UP)));
    }
}
