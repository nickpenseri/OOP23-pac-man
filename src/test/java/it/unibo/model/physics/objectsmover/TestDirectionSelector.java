package it.unibo.model.physics.objectsmover;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


import java.awt.Dimension;
import java.awt.Point;

import org.junit.jupiter.api.Test;

import it.unibo.model.api.GameObjectFactory;
import it.unibo.model.ghost.impl.GhostImpl;
import it.unibo.model.impl.GameObjectFactoryImpl;
import it.unibo.model.physics.objectsmover.api.DirectionSelector;
import it.unibo.model.physics.objectsmover.impl.DirectionSelectorImpl;
import it.unibo.model.api.Character;
import it.unibo.model.api.Direction;
import it.unibo.model.api.GameObject;



/**
 * Test for the DirectionSelector.
 */
class TestDirectionSelector {
    private static final int GAME_OBJ_SIZE = 10;
    private static final int INIT_POSITION = 10;

    private final Dimension dim = new Dimension(GAME_OBJ_SIZE, GAME_OBJ_SIZE);
    private final DirectionSelector selector = new DirectionSelectorImpl();
    private final GameObjectFactory factory  = new GameObjectFactoryImpl();

    @Test
    void upDirection() {
        final Character ghost = new GhostImpl(new Point(INIT_POSITION, INIT_POSITION), dim, 1);
        final GameObject target = factory.createGameObjectWithEmptyGraphics(new Point(INIT_POSITION, INIT_POSITION + 1), dim);
        selector.setDirection(ghost, target);
        assertEquals(Direction.UP, ghost.getDirection().get());
    }


    @Test
    void downDirection() {
        final Character ghost = new GhostImpl(new Point(INIT_POSITION, INIT_POSITION), dim, 1);
        final GameObject target = factory.createGameObjectWithEmptyGraphics(new Point(INIT_POSITION, INIT_POSITION - 1), dim);
        selector.setDirection(ghost, target);
        assertEquals(Direction.DOWN, ghost.getDirection().get());
    }

    @Test
    void leftDirection() {
        final Character ghost = new GhostImpl(new Point(INIT_POSITION, INIT_POSITION), dim, 1);
        final GameObject target = factory.createGameObjectWithEmptyGraphics(new Point(INIT_POSITION - 1, INIT_POSITION), dim);
        selector.setDirection(ghost, target);
        assertEquals(Direction.LEFT, ghost.getDirection().get());
    }

    @Test
    void rightDirection() {
        final Character ghost = new GhostImpl(new Point(INIT_POSITION, INIT_POSITION), dim, 1);
        final GameObject target = factory.createGameObjectWithEmptyGraphics(new Point(INIT_POSITION + 1, INIT_POSITION), dim);
        selector.setDirection(ghost, target);
        assertEquals(Direction.RIGHT, ghost.getDirection().get());
    }

    @Test
    void twoDirections() {
        final Character ghost = new GhostImpl(new Point(INIT_POSITION, INIT_POSITION), dim, 1);
        GameObject target = factory.createGameObjectWithEmptyGraphics(new Point(INIT_POSITION + 1, INIT_POSITION + 1), dim);
        selector.setDirection(ghost, target);
        assertEquals(Direction.RIGHT, ghost.getDirection().get());

        target = factory.createGameObjectWithEmptyGraphics(new Point(INIT_POSITION + 2, INIT_POSITION + 1), dim);
        selector.setDirection(ghost, target);
        assertEquals(Direction.RIGHT, ghost.getDirection().get());

        target = factory.createGameObjectWithEmptyGraphics(new Point(INIT_POSITION + 1, INIT_POSITION + 2), dim);
        selector.setDirection(ghost, target);
        assertEquals(Direction.UP, ghost.getDirection().get());
    }

    @Test
    void noDirection() {
        final Character ghost = new GhostImpl(new Point(INIT_POSITION, INIT_POSITION), dim, 1);
        GameObject target = factory.createGameObjectWithEmptyGraphics(new Point(INIT_POSITION, INIT_POSITION), dim);
        selector.setDirection(ghost, target);
        assertFalse(ghost.getDirection().isPresent());

        target = factory.createGameObjectWithEmptyGraphics(new Point(INIT_POSITION + 1, INIT_POSITION), dim);
        selector.setDirection(ghost, target);
        assertTrue(ghost.getDirection().isPresent());

        target = factory.createGameObjectWithEmptyGraphics(new Point(INIT_POSITION, INIT_POSITION), dim);
        selector.setDirection(ghost, target);
        assertFalse(ghost.getDirection().isPresent());
    }
}
