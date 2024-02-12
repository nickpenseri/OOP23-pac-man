package it.unibo.model.physics.objectsmover;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.awt.Point;

import org.junit.jupiter.api.Test;

import it.unibo.model.ghost.api.GhostFactory;
import it.unibo.model.ghost.impl.GhostFactoryImpl;
import it.unibo.model.physics.objectsmover.api.DirectionSelector;
import it.unibo.model.physics.objectsmover.impl.EuclideanDirectionSelector;
import it.unibo.model.api.Character;
import it.unibo.model.api.Direction;
import it.unibo.model.api.GameObject;

/**
 * Test for the DirectionSelector.
 */
class TestDirectionSelector {
    private static final int GAME_OBJ_SIZE = 10;
    private static final int INIT_POSITION = 10;
    private static final long ELAPSED = 20;
    private final DirectionSelector selector = new EuclideanDirectionSelector();
    private final GhostFactory factory = new GhostFactoryImpl(GAME_OBJ_SIZE, GAME_OBJ_SIZE);

    @Test
    void upDirection() {
        final Character ghost =  factory.createBlueGhost(new Point(INIT_POSITION, INIT_POSITION), 1);
        final GameObject target = factory.createBlueGhost(new Point(INIT_POSITION, INIT_POSITION + 1), 1);
        selector.setDirection(ghost, target, ELAPSED);
        assertEquals(Direction.UP, ghost.getDirection().get());
    }

    @Test
    void downDirection() {
        final Character ghost =  factory.createBlueGhost(new Point(INIT_POSITION, INIT_POSITION), 1);
        final GameObject target = factory.createBlueGhost(new Point(INIT_POSITION, INIT_POSITION - 1), 1);
        selector.setDirection(ghost, target, ELAPSED);
        assertEquals(Direction.DOWN, ghost.getDirection().get());
    }

    @Test
    void leftDirection() {
        final Character ghost =  factory.createBlueGhost(new Point(INIT_POSITION, INIT_POSITION), 1);
        final GameObject target = factory.createBlueGhost(new Point(INIT_POSITION - 1, INIT_POSITION), 1);
        selector.setDirection(ghost, target, ELAPSED);
        assertEquals(Direction.LEFT, ghost.getDirection().get());
    }

    @Test
    void rightDirection() {
        final Character ghost =  factory.createBlueGhost(new Point(INIT_POSITION, INIT_POSITION), 1);
        final GameObject target = factory.createBlueGhost(new Point(INIT_POSITION + 1, INIT_POSITION), 1);
        selector.setDirection(ghost, target, ELAPSED);
        assertEquals(Direction.RIGHT, ghost.getDirection().get());
    }

    @Test
    void twoDirections() {
        final Character ghost =  factory.createBlueGhost(new Point(INIT_POSITION, INIT_POSITION), 1);
        GameObject target = factory.createBlueGhost(new Point(INIT_POSITION + 1, INIT_POSITION + 1), 1);
        selector.setDirection(ghost, target, ELAPSED);
        assertEquals(Direction.RIGHT, ghost.getDirection().get());

        target = factory.createBlueGhost(new Point(INIT_POSITION + 2, INIT_POSITION + 1), 1);
        selector.setDirection(ghost, target, ELAPSED);
        assertEquals(Direction.RIGHT, ghost.getDirection().get());

        target = factory.createBlueGhost(new Point(INIT_POSITION + 1, INIT_POSITION + 2), 1);
        selector.setDirection(ghost, target, ELAPSED);
        assertEquals(Direction.UP, ghost.getDirection().get());
    }

    @Test
    void noDirection() {
        final Character ghost =  factory.createBlueGhost(new Point(INIT_POSITION, INIT_POSITION), 1);
        GameObject target = factory.createBlueGhost(new Point(INIT_POSITION, INIT_POSITION), 1);
        selector.setDirection(ghost, target, ELAPSED);
        assertFalse(ghost.getDirection().isPresent());

        target = factory.createBlueGhost(new Point(INIT_POSITION + 2, INIT_POSITION + 1), 1);
        selector.setDirection(ghost, target, ELAPSED);
        assertTrue(ghost.getDirection().isPresent());

        target = factory.createBlueGhost(new Point(INIT_POSITION + 2, INIT_POSITION + 1), 1);
        selector.setDirection(ghost, target, ELAPSED);
        assertTrue(ghost.getDirection().isPresent());
    }
}
