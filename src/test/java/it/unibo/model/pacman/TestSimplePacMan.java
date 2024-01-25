package it.unibo.model.pacman;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.awt.Dimension;
import java.awt.Point;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import it.unibo.model.pacman.api.PacMan;
import it.unibo.model.pacman.impl.PacManImpl;

/**
 * This class is used to test the behaviour of the clas PacManImpl.
 */
class TestSimplePacMan {

    private static final int STARTING_LIVES = 2;
    private static final int HEIGHTH = 100;
    private static final int WIDTH = 100;
    private static final long ELAPSED = 20;
    private static final Point STARTING_POS = new Point(0, 0);
    private static final Dimension DIMENSION = new Dimension(WIDTH, HEIGHTH);
    private static final double BASE_SPEED = 100.0;
    private static final int POINTS = 100;

    private PacMan pacman;

    /**
     * Creates an object of class PacManImpl to test its behaviour.
     */
    @BeforeEach
    public void setUp() {
        pacman = new PacManImpl(STARTING_LIVES, DIMENSION, BASE_SPEED, STARTING_POS);
    }

    /**
     * Tests pacman's initialization, each field should be the same as passed.
     */
    @Test
    void testInitialization() {
        assertEquals(STARTING_POS, pacman.getPosition());
        assertEquals(DIMENSION, pacman.getDimension());
        assertEquals(0, pacman.getPoints());
        pacman.updateState(ELAPSED);
        assertEquals(STARTING_POS, pacman.getPosition());
        assertEquals(DIMENSION, pacman.getDimension());
        assertEquals(0, pacman.getPoints());
    }

    /**
     * Tests adding and removing lives from pacman.
     * Calling removeLife with no more lives should throw an exception.
     */
    @Test
    void testLives() {
        assertEquals(STARTING_LIVES, pacman.getRemainingLives());
        pacman.addLife();
        assertEquals(STARTING_LIVES + 1, pacman.getRemainingLives());
        pacman.removeLife();
        assertEquals(STARTING_LIVES, pacman.getRemainingLives());
        pacman.removeLife();
        assertEquals(STARTING_LIVES - 1, pacman.getRemainingLives());
        pacman.removeLife();
        assertEquals(0, pacman.getRemainingLives());
    }

    /**
     * Tests that removeLife throws exception if PacMan has no lives.
     */
    @Test
    void testLivesException() {
        pacman.removeLife();
        pacman.removeLife();
        assertEquals(0, pacman.getRemainingLives());
        assertThrows(IllegalStateException.class, () -> pacman.removeLife());
    }

    /**
     * Tests speedIncrease, which should increase the speed a limited number of times.
     */
    @Test
    void testSpeedIncrease() {
        assertTrue(pacman.increaseSpeed());
        assertTrue(pacman.increaseSpeed());
        assertTrue(pacman.increaseSpeed());
        assertFalse(pacman.increaseSpeed());
    }

    /**
     * Tests speedDecrease, which should decrease the speed a limited number of times.
     */
    @Test
    void testSpeedDecrease() {
        assertTrue(pacman.decreaseSpeed());
        assertTrue(pacman.decreaseSpeed());
        assertTrue(pacman.decreaseSpeed());
        assertFalse(pacman.decreaseSpeed());
    }

    /**
     * Tests adding and removing points, which cannot be negative.
     */
    @Test
    void testPoints() {
        assertEquals(0, pacman.getPoints());
        pacman.removePoints(POINTS);
        assertEquals(0, pacman.getPoints());
        pacman.addPoints(POINTS);
        pacman.addPoints(POINTS);
        assertEquals(2 * POINTS, pacman.getPoints());
        pacman.removePoints(3 * POINTS);
        assertEquals(0, pacman.getPoints());
    }
}
