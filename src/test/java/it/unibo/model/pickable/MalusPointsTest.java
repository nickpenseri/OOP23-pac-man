package it.unibo.model.pickable;

import org.junit.jupiter.api.Test;
import it.unibo.model.pacman.api.PacMan;
import it.unibo.model.pacman.impl.PacManImpl;
import it.unibo.model.pickable.impl.MalusPoints;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.awt.Point;
import java.awt.Dimension;

/**
 * This class is used to test the behaviour of the class MalusPoints.
 */
class MalusPointsTest {

    private static final int POSITION = 5;
    private static final int HEIGHTH = 100;
    private static final int WIDTH = 100;
    private static final Dimension DIMENSION = new Dimension(WIDTH, HEIGHTH);
    private static final int STARTING_LIVES = 2;
    private static final Point STARTING_POS = new Point(0, 0);
    private static final double BASE_SPEED = 100.0;
    private static final int DECREASE_POINTS = 5_000;


    /**
     * This method tests the constructor of the class MalusPoints.
     */
    @Test
    void testConstructor() {
        final Point position = new Point(POSITION, POSITION);
        final MalusPoints bonusPoints = new MalusPoints(position, DIMENSION);

        assertEquals(position, bonusPoints.getPosition());
        assertEquals(DIMENSION, bonusPoints.getDimension());
    }

    /**
     * This method tests the method doEffect of the class MalusPoints.
     */
    @Test
    void testDoEffect() {
        final Point position = new Point(POSITION, POSITION);
        final MalusPoints bonusPoints = new MalusPoints(position, DIMENSION);
        final PacMan pacman = new PacManImpl(STARTING_LIVES, DIMENSION, BASE_SPEED, STARTING_POS);

        final int initialPoints = pacman.getPoints();
        bonusPoints.doEffect(pacman, null);
        if (initialPoints < DECREASE_POINTS) {
            assertEquals(initialPoints, pacman.getPoints());
        } else {
            assertEquals(initialPoints - DECREASE_POINTS, pacman.getPoints());
        }
    }
}
