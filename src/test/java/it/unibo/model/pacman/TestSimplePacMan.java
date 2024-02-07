package it.unibo.model.pacman;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.awt.Dimension;
import java.awt.Point;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.model.api.Direction;
import it.unibo.model.pacman.api.PacMan;
import it.unibo.model.pacman.impl.PacManImpl;

/**
 * This class is used to test the behaviour of the clas PacManImpl.
 */
class TestSimplePacMan {

    private static final int SPEED_DECREASED_2 = -2;
    private static final int SPEED_DECREASED_3 = -3;
    private static final int STARTING_LIVES = 2;
    private static final int HEIGHTH = 100;
    private static final int WIDTH = 100;
    private static final long ELAPSED = 20;
    private static final Point STARTING_POS = new Point(0, 0);
    private static final Dimension DIMENSION = new Dimension(WIDTH, HEIGHTH);
    private static final double BASE_SPEED = 1000.0;
    private static final int POINTS = 100;
    private static final int MOVEMENT = (int) (BASE_SPEED * (ELAPSED / 1000.0));
    private static final Point RESPAWN_POINT = new Point(500, 500);
    private static final double SPEED_MULTIPLIER = 0.10;
    private static final int MOVEMENT_1_PLUS = MOVEMENT + (int) (MOVEMENT * SPEED_MULTIPLIER);
    private static final int MOVEMENT_2_PLUS = MOVEMENT + (int) (MOVEMENT * SPEED_MULTIPLIER * 2);
    private static final int MOVEMENT_3_PLUS = MOVEMENT + (int) (MOVEMENT * SPEED_MULTIPLIER * 3);
    private static final int MOVEMENT_1_MIN = MOVEMENT + (int) (MOVEMENT * SPEED_MULTIPLIER * (-1));
    private static final int MOVEMENT_2_MIN = MOVEMENT + (int) (MOVEMENT * SPEED_MULTIPLIER * SPEED_DECREASED_2);
    private static final int MOVEMENT_3_MIN = MOVEMENT + (int) (MOVEMENT * SPEED_MULTIPLIER * SPEED_DECREASED_3);
    private PacMan pacman;

    /**
     * Creates an object of class PacManImpl to test its behaviour.
     */
    @BeforeEach
    public void setUp() {
        this.pacman = new PacManImpl(STARTING_LIVES, DIMENSION, BASE_SPEED, STARTING_POS);
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
     * Tests speedIncrease, which should increase the speed a limited number of
     * times.
     */
    @Test
    void testSpeedIncrease() {
        assertTrue(pacman.increaseSpeed());
        assertTrue(pacman.increaseSpeed());
        assertTrue(pacman.increaseSpeed());
        assertFalse(pacman.increaseSpeed());
    }

    /**
     * Tests speedDecrease, which should decrease the speed a limited number of
     * times.
     */
    @Test
    void testSpeedDecrease() {
        assertTrue(pacman.decreaseSpeed());
        assertTrue(pacman.decreaseSpeed());
        assertTrue(pacman.decreaseSpeed());
        assertFalse(pacman.decreaseSpeed());
    }

    /**
     * Tests adding and removing points, which cannot go below zero.
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

    /**
     * Tests that IllegalArgumentException are thrown if negative number are passed
     * as the points to be decracted or added.
     */
    @Test
    void testPointsExceptions() {
        assertThrows(IllegalArgumentException.class, () -> pacman.removePoints(-POINTS));
        assertThrows(IllegalArgumentException.class, () -> pacman.addPoints(-POINTS));
    }

    /**
     * Test for method updateState with every direction possible for pacman.
     */
    @Test
    void testMovement() {
        pacman.updateState(ELAPSED);
        assertEquals(STARTING_POS, pacman.getPosition());
        pacman.setDirection(Direction.RIGHT);
        pacman.updateState(ELAPSED);
        assertEquals(new Point(MOVEMENT, 0), pacman.getPosition());
        pacman.setDirection(Direction.LEFT);
        pacman.updateState(ELAPSED);
        assertEquals(STARTING_POS, pacman.getPosition());
        pacman.setDirection(Direction.UP);
        pacman.updateState(0);
        assertEquals(STARTING_POS, pacman.getPosition());
        pacman.setDirection(Direction.DOWN);
        pacman.updateState(ELAPSED);
        assertEquals(new Point(0, -MOVEMENT), pacman.getPosition());
        pacman.setDirection(Direction.UP);
        pacman.updateState(ELAPSED);
        assertEquals(STARTING_POS, pacman.getPosition());
    }

    /**
     * Test for method respawn.
     */
    @Test
    void testRespawn() {
        pacman.updateState(ELAPSED);
        assertEquals(STARTING_POS, pacman.getPosition());
        pacman.setDirection(Direction.UP);
        pacman.respawn(RESPAWN_POINT);
        assertEquals(RESPAWN_POINT, pacman.getPosition());
        pacman.updateState(ELAPSED);
        assertEquals(RESPAWN_POINT, pacman.getPosition());
    }

    /**
     * Test for movement with increased speed.
     */
    @Test
    void testMovementIncreased() {
        assertEquals(0, pacman.getSpeedLevel());
        pacman.increaseSpeed();
        pacman.setDirection(Direction.RIGHT);
        pacman.updateState(ELAPSED);
        assertEquals(1, pacman.getSpeedLevel());
        assertEquals(new Point(MOVEMENT_1_PLUS, 0), pacman.getPosition());
        pacman.respawn(STARTING_POS);
        pacman.increaseSpeed();
        pacman.setDirection(Direction.RIGHT);
        pacman.updateState(ELAPSED);
        assertEquals(2, pacman.getSpeedLevel());
        assertEquals(new Point(MOVEMENT_2_PLUS, 0), pacman.getPosition());
        pacman.respawn(STARTING_POS);
        pacman.increaseSpeed();
        pacman.setDirection(Direction.RIGHT);
        pacman.updateState(ELAPSED);
        assertEquals(3, pacman.getSpeedLevel());
        assertEquals(new Point(MOVEMENT_3_PLUS, 0), pacman.getPosition());
        pacman.increaseSpeed();
        assertEquals(3, pacman.getSpeedLevel());
    }

    /**
     * Test for movement with decreased speed.
     */
    @Test
    void testMovementDecreased() {
        assertEquals(0, pacman.getSpeedLevel());
        pacman.decreaseSpeed();
        pacman.setDirection(Direction.UP);
        pacman.updateState(ELAPSED);
        assertEquals(-1, pacman.getSpeedLevel());
        assertEquals(new Point(0, MOVEMENT_1_MIN), pacman.getPosition());
        pacman.respawn(STARTING_POS);
        pacman.decreaseSpeed();
        pacman.setDirection(Direction.DOWN);
        pacman.updateState(ELAPSED);
        assertEquals(SPEED_DECREASED_2, pacman.getSpeedLevel());
        assertEquals(new Point(0, -MOVEMENT_2_MIN), pacman.getPosition());
        pacman.respawn(STARTING_POS);
        pacman.decreaseSpeed();
        pacman.setDirection(Direction.UP);
        pacman.updateState(ELAPSED);
        assertEquals(SPEED_DECREASED_3, pacman.getSpeedLevel());
        assertEquals(new Point(0, MOVEMENT_3_MIN), pacman.getPosition());
        pacman.decreaseSpeed();
        assertEquals(SPEED_DECREASED_3, pacman.getSpeedLevel());
    }
}
