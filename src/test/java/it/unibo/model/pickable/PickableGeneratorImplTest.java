package it.unibo.model.pickable;

import org.junit.jupiter.api.Test;
import it.unibo.model.pacman.api.PacMan;
import it.unibo.model.pacman.impl.PacManImpl;
import it.unibo.model.pickable.api.Pickable;
import it.unibo.model.pickable.impl.PickableGeneratorImpl;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import java.awt.Dimension;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

/**
 * This class is used to test the behaviour of the class PickableGeneratorImpl.
 */
class PickableGeneratorImplTest {

    private static final int POSITION = 5;
    private static final int HEIGHTH = 100;
    private static final int WIDTH = 100;
    private static final Dimension DIMENSION = new Dimension(WIDTH, HEIGHTH);
    private static final int STARTING_LIVES = 2;
    private static final Point STARTING_POS = new Point(0, 0);
    private static final double BASE_SPEED = 100.0;

    /**
     * This method tests the method getPickableList of the class
     * PickableGeneratorImpl.
     */
    @Test
    void testGetPickableList() {
        final PickableGeneratorImpl generator = new PickableGeneratorImpl();
        final List<Pickable> pickableList = new ArrayList<>(generator.getPickableList());
        assertNotNull(pickableList);
        assertTrue(pickableList.isEmpty());
    }

    /**
     * This method tests the method generateMap of the class PickableGeneratorImpl.
     */
    @Test
    void testTakePickable() {
        final PickableGeneratorImpl generator = new PickableGeneratorImpl();
        final Point point = new Point(POSITION, POSITION);
        final PacMan pacman = new PacManImpl(STARTING_LIVES, DIMENSION, BASE_SPEED, STARTING_POS);
        final ArrayList<Point> list = new ArrayList<>();
        list.add(point);
        generator.generateMap(list, DIMENSION);
        assertFalse(generator.getPickableList().isEmpty());
        generator.takePickable(point, pacman);
        assertTrue(generator.finishedPickable());
    }

    /**
     * This method tests the method finishedPickable of the class
     * PickableGeneratorImpl.
     */
    @Test
    void testFinishedPickable() {
        final PickableGeneratorImpl generator = new PickableGeneratorImpl();
        assertTrue(generator.finishedPickable());
        final Point point = new Point(POSITION, POSITION);
        final ArrayList<Point> list = new ArrayList<>();
        list.add(point);
        generator.generateMap(list, DIMENSION);
        assertFalse(generator.finishedPickable());
    }
}
