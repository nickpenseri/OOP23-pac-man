package it.unibo.model.pacman;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.awt.Dimension;
import java.awt.Point;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.model.api.Direction;
import it.unibo.model.api.GameObject;
import it.unibo.model.api.GameObjectFactory;
import it.unibo.model.impl.GameObjectFactoryImpl;
import it.unibo.model.impl.GameObjectImpl.Type;
import it.unibo.model.pacman.api.GamePacMan;
import it.unibo.model.pacman.api.PacMan;
import it.unibo.model.pacman.impl.PacManImpl;
import it.unibo.model.pacman.impl.PacManWalls;

/**
 * This class is used to test the behaviour of objects of the class PacManWalls.
 * 
 * @see PacManWalls
 */
class TestWallsPacMan {
    private static final int STARTING_LIVES = 3;
    private static final int HEIGTH = 50;
    private static final int WIDTH = 50;
    private static final Point STARTING_POS = new Point(0, 0);
    private static final Dimension DIMENSION = new Dimension(WIDTH, HEIGTH);
    private static final double BASE_SPEED = 100.0;
    private static final long ELAPSED = 20;
    private static final int MOVEMENT = (int) (BASE_SPEED * ELAPSED / 1000);
    private static final GameObjectFactory FACTORY = new GameObjectFactoryImpl(HEIGTH, WIDTH, 1, 1);
    private static final Point POS_1 = new Point(52, 0);
    private static final Point POS_2 = new Point(50, 0);
    private static final Point POS_3 = new Point(0, 50);
    private static final Point POS_4 = new Point(-50, 0);
    private static final Point POS_5 = new Point(0, -50);
    private static final Point RESPAWN_WALL = new Point(55, 0);
    private static final Point RESPAWN_CORRECT = new Point(100, 100);

    private PacMan decorated = new PacManImpl(STARTING_LIVES, DIMENSION, BASE_SPEED, STARTING_POS);

    /**
     * This method initializes the decorated pacman before each test.
     */
    @BeforeEach
    void setUp() {
        this.decorated = new PacManImpl(STARTING_LIVES, DIMENSION, BASE_SPEED, STARTING_POS);
    }

    /**
     * Test for pacman movement with and without a wall.
     */
    @Test
    void testMovement() {
        final GameObject obj1 = FACTORY.createGameObject(POS_1, Type.WALL);
        final GamePacMan pacman = new PacManWalls(decorated, List.of(obj1));
        pacman.setDirection(Direction.RIGHT);
        pacman.updateState(ELAPSED);
        assertEquals(new Point(MOVEMENT, 0), pacman.getPosition());
        pacman.updateState(ELAPSED);
        pacman.updateState(ELAPSED);
        assertEquals(new Point(MOVEMENT, 0), pacman.getPosition());
        pacman.respawn(STARTING_POS);
        pacman.setDirection(Direction.LEFT);
        pacman.updateState(ELAPSED);
        assertEquals(new Point(-MOVEMENT, 0), pacman.getPosition());
        pacman.setDirection(Direction.RIGHT);
        pacman.updateState(ELAPSED);
        assertEquals(STARTING_POS, pacman.getPosition());
    }

    /**
     * This method tests the movement of pacman when it is surrounded by walls, the
     * position should never change.
     */
    @Test
    void testBlocked() {
        final GameObject wall1 = FACTORY.createGameObject(POS_2, Type.WALL);
        final GameObject wall2 = FACTORY.createGameObject(POS_3, Type.WALL);
        final GameObject wall3 = FACTORY.createGameObject(POS_4, Type.WALL);
        final GameObject wall4 = FACTORY.createGameObject(POS_5, Type.WALL);
        final GamePacMan pacMan = new PacManWalls(decorated, List.of(wall1, wall2, wall3, wall4));
        pacMan.updateState(ELAPSED);
        assertEquals(STARTING_POS, pacMan.getPosition());
        pacMan.setDirection(Direction.RIGHT);
        pacMan.updateState(ELAPSED);
        assertEquals(STARTING_POS, pacMan.getPosition());
        pacMan.setDirection(Direction.DOWN);
        pacMan.updateState(ELAPSED);
        assertEquals(STARTING_POS, pacMan.getPosition());
        pacMan.setDirection(Direction.LEFT);
        pacMan.updateState(ELAPSED);
        assertEquals(STARTING_POS, pacMan.getPosition());
        pacMan.setDirection(Direction.UP);
        pacMan.updateState(ELAPSED);
        assertEquals(STARTING_POS, pacMan.getPosition());
    }

    /**
     * This method tests pacman's respawn and exception throwing in case of respawn
     * in a wall.
     */
    @Test
    void testRespawn() {
        final GameObject wall1 = FACTORY.createGameObject(POS_2, Type.WALL);
        final GameObject wall2 = FACTORY.createGameObject(POS_3, Type.WALL);
        final GameObject wall3 = FACTORY.createGameObject(POS_4, Type.WALL);
        final GameObject wall4 = FACTORY.createGameObject(POS_5, Type.WALL);
        final GamePacMan pacMan = new PacManWalls(decorated, List.of(wall1, wall2, wall3, wall4));
        pacMan.respawn(RESPAWN_CORRECT);
        pacMan.updateState(ELAPSED);
        assertEquals(RESPAWN_CORRECT, pacMan.getPosition());
        assertThrows(IllegalArgumentException.class, () -> pacMan.respawn(RESPAWN_WALL));
    }
}
