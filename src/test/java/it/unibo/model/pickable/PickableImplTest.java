package it.unibo.model.pickable;

import org.junit.jupiter.api.Test;
import it.unibo.model.pickable.impl.PickableImpl;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.awt.Point;
import java.awt.Dimension;

/**
 * This class is used to test the behaviour of the class PickableImpl.
 */
class PickableImplTest {

    private static final int POSITION = 5;
    private static final int HEIGHTH = 100;
    private static final int WIDTH = 100;
    private static final Dimension DIMENSION = new Dimension(WIDTH, HEIGHTH);

    /**
     * This method tests the constructor of the class PickableImpl.
     */
    @Test
    void testConstructor() {
        final Point position = new Point(POSITION, POSITION);
        final PickableImpl pickable = new PickableImpl(position, DIMENSION);
        assertEquals(position, pickable.getPosition());
        assertEquals(DIMENSION, pickable.getDimension());
    }

    /**
     * This method tests the method position of the class PickableImpl.
     */
    @Test
    void testPosition() {
        final Point position = new Point(POSITION, POSITION);
        final PickableImpl pickable = new PickableImpl(position, DIMENSION);
        assertEquals(position, pickable.getPosition());
    }

    /**
     * This method tests the method dimension of the class PickableImpl.
     */
    @Test
    void testDimension() {
        final Point position = new Point(POSITION, POSITION);
        final PickableImpl pickable = new PickableImpl(position, DIMENSION);
        assertEquals(DIMENSION, pickable.getDimension());
    }
}
