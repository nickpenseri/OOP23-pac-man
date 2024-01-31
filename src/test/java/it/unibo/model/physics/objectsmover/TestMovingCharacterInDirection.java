package it.unibo.model.physics.objectsmover;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import java.awt.Dimension;
import java.awt.Point;

import it.unibo.model.api.GameObject;
import it.unibo.model.api.GameObjectFactory;
import it.unibo.model.ghost.impl.GhostImpl;
import it.unibo.model.impl.GameObjectFactoryImpl;
import it.unibo.model.api.Character;
import it.unibo.model.physics.objectsmover.api.DirectionSelector;
import it.unibo.model.physics.objectsmover.impl.DirectionSelectorImpl;

class TestMovingCharacterInDirection {
    private static final int GAME_OBJ_SIZE = 10;
    private static final int INIT_POSITION = 10;

    private final Dimension dim = new Dimension(GAME_OBJ_SIZE, GAME_OBJ_SIZE);
    private final DirectionSelector selector = new DirectionSelectorImpl();
    private final GameObjectFactory factory  = new GameObjectFactoryImpl();

    @Test
    void moveUp() {
        final Character ghost = new GhostImpl(new Point(INIT_POSITION, INIT_POSITION), dim, 1);
        final GameObject target = factory.createGameObjectWithEmptyGraphics(new Point(INIT_POSITION, INIT_POSITION + 1), dim);
        final int yDiff = ghost.getPosition().y - target.getPosition().y;
        selector.setDirection(ghost, target);
        ghost.updateState(1000);
        final int yDiffAfter = ghost.getPosition().y - target.getPosition().y;
        assertTrue(Math.abs(yDiffAfter) < Math.abs(yDiff));
    }


    @Test
    void moveDown() {
        final Character ghost = new GhostImpl(new Point(INIT_POSITION, INIT_POSITION), dim, 1);
        final GameObject target = factory.createGameObjectWithEmptyGraphics(new Point(INIT_POSITION, INIT_POSITION - 1), dim);
        final int yDiff = ghost.getPosition().y - target.getPosition().y;
        selector.setDirection(ghost, target);
        ghost.updateState(1000);
        final int yDiffAfter = ghost.getPosition().y - target.getPosition().y;
        assertTrue(Math.abs(yDiffAfter) < Math.abs(yDiff));
    }


    @Test
    void moveRight() {
        final Character ghost = new GhostImpl(new Point(INIT_POSITION, INIT_POSITION), dim, 1);
        final GameObject target = factory.createGameObjectWithEmptyGraphics(new Point(INIT_POSITION + 1, INIT_POSITION), dim);
        final int xDiff = ghost.getPosition().x - target.getPosition().x;
        selector.setDirection(ghost, target);
        ghost.updateState(1000);
        final int xDiffAfter = ghost.getPosition().x - target.getPosition().x;
        assertTrue(Math.abs(xDiffAfter) < Math.abs(xDiff));
    }

    @Test
    void moveLeft() {
        final Character ghost = new GhostImpl(new Point(INIT_POSITION, INIT_POSITION), dim, 1);
        final GameObject target = factory.createGameObjectWithEmptyGraphics(new Point(INIT_POSITION - 1, INIT_POSITION), dim);
        final int xDiff = ghost.getPosition().x - target.getPosition().x;
        selector.setDirection(ghost, target);
        ghost.updateState(1000);
        final int xDiffAfter = ghost.getPosition().x - target.getPosition().x;
        assertTrue(Math.abs(xDiffAfter) < Math.abs(xDiff));
    }


    @Test
    void noMove() {
        final Character ghost = new GhostImpl(new Point(INIT_POSITION, INIT_POSITION), dim, 1);
        final GameObject target = factory.createGameObjectWithEmptyGraphics(new Point(INIT_POSITION, INIT_POSITION), dim);
        selector.setDirection(ghost, target);
        ghost.updateState(1000);
        assertEquals(target.getPosition(), ghost.getPosition());
    }
}
