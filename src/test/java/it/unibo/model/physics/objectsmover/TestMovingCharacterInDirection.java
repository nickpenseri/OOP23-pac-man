package it.unibo.model.physics.objectsmover;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import java.awt.Point;

import it.unibo.model.api.GameObject;
import it.unibo.model.ghost.api.GhostFactory;
import it.unibo.model.ghost.impl.GhostFactoryImpl;
import it.unibo.model.api.Character;
import it.unibo.model.physics.objectsmover.api.CharacterMover;
import it.unibo.model.physics.objectsmover.impl.EuclideanCharacterMover;

class TestMovingCharacterInDirection {
    private static final int GAME_OBJ_SIZE = 10;
    private static final int INIT_POSITION = 10;
    private static final long ELAPSED = 20;
    private final CharacterMover selector = new EuclideanCharacterMover();
    private final GhostFactory factory = new GhostFactoryImpl(GAME_OBJ_SIZE, GAME_OBJ_SIZE);

    @Test
    void moveUp() {
        final Character ghost = factory.createBlueGhost(new Point(INIT_POSITION, INIT_POSITION), 1);
        final GameObject target =  factory.createBlueGhost(new Point(INIT_POSITION, INIT_POSITION + 1), 1);
        final int yDiff = ghost.getPosition().y - target.getPosition().y;
        selector.moveCharacter(ghost, target, ELAPSED);
        ghost.updateState(1000);
        final int yDiffAfter = ghost.getPosition().y - target.getPosition().y;
        assertTrue(Math.abs(yDiffAfter) < Math.abs(yDiff));
    }

    @Test
    void moveDown() {
        final Character ghost = factory.createBlueGhost(new Point(INIT_POSITION, INIT_POSITION), 1);
        final GameObject target =  factory.createBlueGhost(new Point(INIT_POSITION, INIT_POSITION - 1), 1);
        final int yDiff = ghost.getPosition().y - target.getPosition().y;
        selector.moveCharacter(ghost, target, ELAPSED);
        ghost.updateState(1000);
        final int yDiffAfter = ghost.getPosition().y - target.getPosition().y;
        assertTrue(Math.abs(yDiffAfter) < Math.abs(yDiff));
    }

    @Test
    void moveRight() {
        final Character ghost = factory.createBlueGhost(new Point(INIT_POSITION, INIT_POSITION), 1);
        final GameObject target =  factory.createBlueGhost(new Point(INIT_POSITION + 1, INIT_POSITION), 0);
        final int xDiff = ghost.getPosition().x - target.getPosition().x;
        selector.moveCharacter(ghost, target, ELAPSED);
        ghost.updateState(1000);
        final int xDiffAfter = ghost.getPosition().x - target.getPosition().x;
        assertTrue(Math.abs(xDiffAfter)  < Math.abs(xDiff));
    }

    @Test
    void moveLeft() {
        final Character ghost = factory.createBlueGhost(new Point(INIT_POSITION, INIT_POSITION), 1);
        final GameObject target =  factory.createBlueGhost(new Point(INIT_POSITION - 1, INIT_POSITION), 0);
        final int xDiff = ghost.getPosition().x - target.getPosition().x;
        selector.moveCharacter(ghost, target, ELAPSED);
        ghost.updateState(1000);
        final int xDiffAfter = ghost.getPosition().x - target.getPosition().x;
        assertTrue(Math.abs(xDiffAfter) < Math.abs(xDiff));
    }

    @Test
    void noMove() {
        final Character ghost = factory.createBlueGhost(new Point(INIT_POSITION, INIT_POSITION), 1);
        final GameObject target =  factory.createBlueGhost(new Point(INIT_POSITION, INIT_POSITION), 1);
        selector.moveCharacter(ghost, target, ELAPSED);
        ghost.updateState(1000);
        assertEquals(target.getPosition(), ghost.getPosition());
    }
}
