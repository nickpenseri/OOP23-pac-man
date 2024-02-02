package it.unibo.model.physics.objectsmover;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.awt.Point;

import org.junit.jupiter.api.Test;

import it.unibo.model.api.GameObjectFactory;
import it.unibo.model.ghost.api.GhostColor;
import it.unibo.model.impl.GameObjectFactoryImpl;
import it.unibo.model.impl.GameObjectImpl;
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
    private static final int GAME_OBJ_SIZE2 = 1;
    private static final int INIT_POSITION = 10;

    private final DirectionSelector selector = new DirectionSelectorImpl();
    private final GameObjectFactory factory = new GameObjectFactoryImpl(GAME_OBJ_SIZE, GAME_OBJ_SIZE, GAME_OBJ_SIZE2, GAME_OBJ_SIZE2);

    @Test
    void upDirection() {
        final Character ghost =  factory.createGhost(new Point(INIT_POSITION, INIT_POSITION), 1, GhostColor.BLUE);
        final GameObjectImpl target = factory.createGameObject(new Point(INIT_POSITION, INIT_POSITION + 1), GameObjectImpl.Type.FLOR);
        selector.setDirection(ghost, target);
        assertEquals(Direction.UP, ghost.getDirection().get());
    }

    @Test
    void downDirection() {
        final Character ghost =  factory.createGhost(new Point(INIT_POSITION, INIT_POSITION), 1, GhostColor.BLUE);
        final GameObject target = factory.createGameObject(new Point(INIT_POSITION, INIT_POSITION - 1), GameObjectImpl.Type.FLOR);
        selector.setDirection(ghost, target);
        assertEquals(Direction.DOWN, ghost.getDirection().get());
    }

    @Test
    void leftDirection() {
        final Character ghost =  factory.createGhost(new Point(INIT_POSITION, INIT_POSITION), 1, GhostColor.BLUE);
        final GameObject target = factory.createGameObject(new Point(INIT_POSITION - 1, INIT_POSITION), GameObjectImpl.Type.FLOR);
        selector.setDirection(ghost, target);
        assertEquals(Direction.LEFT, ghost.getDirection().get());
    }

    @Test
    void rightDirection() {
        final Character ghost =  factory.createGhost(new Point(INIT_POSITION, INIT_POSITION), 1, GhostColor.BLUE);
        final GameObject target = factory.createGameObject(new Point(INIT_POSITION + 1, INIT_POSITION), GameObjectImpl.Type.FLOR);
        selector.setDirection(ghost, target);
        assertEquals(Direction.RIGHT, ghost.getDirection().get());
    }

    @Test
    void twoDirections() {
        final Character ghost =  factory.createGhost(new Point(INIT_POSITION, INIT_POSITION), 1, GhostColor.BLUE);
        GameObject target = factory.createGameObject(new Point(INIT_POSITION + 1, INIT_POSITION + 1), GameObjectImpl.Type.FLOR);
        selector.setDirection(ghost, target);
        assertEquals(Direction.RIGHT, ghost.getDirection().get());

        target = factory.createGameObject(new Point(INIT_POSITION + 2, INIT_POSITION + 1), GameObjectImpl.Type.FLOR);
        selector.setDirection(ghost, target);
        assertEquals(Direction.RIGHT, ghost.getDirection().get());

        target =  factory.createGameObject(new Point(INIT_POSITION + 1, INIT_POSITION + 2), GameObjectImpl.Type.FLOR);
        selector.setDirection(ghost, target);
        assertEquals(Direction.UP, ghost.getDirection().get());
    }

    @Test
    void noDirection() {
        final Character ghost =  factory.createGhost(new Point(INIT_POSITION, INIT_POSITION), 1, GhostColor.BLUE);
        GameObject target = factory.createGameObject(new Point(INIT_POSITION, INIT_POSITION), GameObjectImpl.Type.FLOR);
        selector.setDirection(ghost, target);
        assertFalse(ghost.getDirection().isPresent());

        target =  factory.createGameObject(new Point(INIT_POSITION + 2, INIT_POSITION + 1), GameObjectImpl.Type.FLOR);
        selector.setDirection(ghost, target);
        assertTrue(ghost.getDirection().isPresent());

        target =  factory.createGameObject(new Point(INIT_POSITION + 2, INIT_POSITION + 1), GameObjectImpl.Type.FLOR);
        selector.setDirection(ghost, target);
        assertTrue(ghost.getDirection().isPresent());
    }
}
