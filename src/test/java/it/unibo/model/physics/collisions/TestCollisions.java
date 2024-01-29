package it.unibo.model.physics.collisions;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.awt.Dimension;
import java.awt.Point;

import org.junit.jupiter.api.Test;

import it.unibo.model.pacman.api.PacMan;
import it.unibo.model.pacman.impl.PacManImpl;
import it.unibo.model.physics.collisions.api.CollisionCheckerFactory;
import it.unibo.model.physics.collisions.impl.CollisionCheckerFactoryImpl;

/**
 * This class is used to test the behaviour of CollisionFactoryImpl.
 */
class TestCollisions {
    private static final PacMan PCMN_1 = new PacManImpl(1, new Dimension(100, 100), 1, new Point(0, 0));
    private static final PacMan PCMN_2 = new PacManImpl(1, new Dimension(10, 10), 1, new Point(50, 50));
    private static final PacMan PCMN_3 = new PacManImpl(1, new Dimension(200, 200), 1, new Point(100, 100));
    private static final PacMan PCMN_4 = new PacManImpl(1, new Dimension(50, 50), 1, new Point(-50, -50));

    private final CollisionCheckerFactory factory = new CollisionCheckerFactoryImpl();

    /**
     * Tests the cheker beetween gameObjects given by method gameObjectChecker of
     * the factory.
     */
    @Test
    void testGameObjects() {
        final var checker = this.factory.gameObjectChecker();
        assertTrue(checker.areColliding(PCMN_1, PCMN_2));
        assertFalse(checker.areColliding(PCMN_2, PCMN_3));
        assertFalse(checker.areColliding(PCMN_1, PCMN_3));
        assertFalse(checker.areColliding(PCMN_3, PCMN_4));
        assertFalse(checker.areColliding(PCMN_2, PCMN_4));
        assertFalse(checker.areColliding(PCMN_1, PCMN_4));
    }
}
