package it.unibo.model.physics.objectsmover;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import java.awt.Point;

import it.unibo.model.api.GameObject;
import it.unibo.model.api.GameObjectFactory;
import it.unibo.model.ghost.api.GhostColor;
import it.unibo.model.impl.GameObjectFactoryImpl;
import it.unibo.model.impl.GameObjectImpl;
import it.unibo.model.api.Character;
import it.unibo.model.physics.objectsmover.api.DirectionSelector;
import it.unibo.model.physics.objectsmover.impl.EuclideanDirectionSelector;

class TestMovingCharacterInDirection {
    private static final int GAME_OBJ_SIZE = 10;
    private static final int MAP_SIZE = 1;
    private static final int INIT_POSITION = 10;

<<<<<<< HEAD
    private final GameObjectImpl.Type type = GameObjectImpl.Type.FLOR;
    private final DirectionSelector selector = new DirectionSelectorImpl();
    private final GameObjectFactory factory = new GameObjectFactoryImpl(GAME_OBJ_SIZE, INIT_POSITION, MAP_SIZE, MAP_SIZE);
=======
    private final Dimension dim = new Dimension(GAME_OBJ_SIZE, GAME_OBJ_SIZE);
    private final DirectionSelector selector = new EuclideanDirectionSelector();
    private final GameObjectFactory factory  = new GameObjectFactoryImpl();
>>>>>>> MoveOnGraph

    @Test
    void moveUp() {
        final Character ghost = factory.createGhost(new Point(INIT_POSITION, INIT_POSITION), 1, GhostColor.BLUE);
        final GameObject target =  factory.createGameObject(new Point(INIT_POSITION, INIT_POSITION + 1), type);
        final int yDiff = ghost.getPosition().y - target.getPosition().y;
        selector.setDirection(ghost, target);
        ghost.updateState(1000);
        final int yDiffAfter = ghost.getPosition().y - target.getPosition().y;
        assertTrue(Math.abs(yDiffAfter) < Math.abs(yDiff));
    }

    @Test
    void moveDown() {
        final Character ghost = factory.createGhost(new Point(INIT_POSITION, INIT_POSITION), 1, GhostColor.BLUE);
        final GameObject target =  factory.createGameObject(new Point(INIT_POSITION, INIT_POSITION - 1), type);
        final int yDiff = ghost.getPosition().y - target.getPosition().y;
        selector.setDirection(ghost, target);
        ghost.updateState(1000);
        final int yDiffAfter = ghost.getPosition().y - target.getPosition().y;
        assertTrue(Math.abs(yDiffAfter) < Math.abs(yDiff));
    }

    @Test
    void moveRight() {
        final Character ghost = factory.createGhost(new Point(INIT_POSITION, INIT_POSITION), 1, GhostColor.BLUE);
        final GameObject target =  factory.createGameObject(new Point(INIT_POSITION + 1, INIT_POSITION), type);
        final int xDiff = ghost.getPosition().x - target.getPosition().x;
        selector.setDirection(ghost, target);
        ghost.updateState(1000);
        final int xDiffAfter = ghost.getPosition().x - target.getPosition().x;
        assertTrue(Math.abs(xDiffAfter) < Math.abs(xDiff));
    }

    @Test
    void moveLeft() {
        final Character ghost = factory.createGhost(new Point(INIT_POSITION, INIT_POSITION), 1, GhostColor.BLUE);
        final GameObject target =  factory.createGameObject(new Point(INIT_POSITION - 1, INIT_POSITION), type);
        final int xDiff = ghost.getPosition().x - target.getPosition().x;
        selector.setDirection(ghost, target);
        ghost.updateState(1000);
        final int xDiffAfter = ghost.getPosition().x - target.getPosition().x;
        assertTrue(Math.abs(xDiffAfter) < Math.abs(xDiff));
    }

    @Test
    void noMove() {
        final Character ghost = factory.createGhost(new Point(INIT_POSITION, INIT_POSITION), 1, GhostColor.BLUE);
        final GameObject target =  factory.createGameObject(new Point(INIT_POSITION, INIT_POSITION), type);
        selector.setDirection(ghost, target);
        ghost.updateState(1000);
        assertEquals(target.getPosition(), ghost.getPosition());
    }
}
