package it.unibo.model.pickable;

import org.junit.jupiter.api.Test;
import it.unibo.model.pacman.api.PacMan;
import it.unibo.model.pacman.impl.PacManImpl;
import it.unibo.model.pickable.impl.BonusSpeed;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.awt.Point;
import java.util.Timer;
import java.util.TimerTask;
import java.awt.Dimension;

/**
 * This class is used to test the behaviour of the class BonusSpeed.
 */
class BonusSpeedTest {

    private static final int POSITION = 5;
    private static final int HEIGHTH = 100;
    private static final int WIDTH = 100;
    private static final Dimension DIMENSION = new Dimension(WIDTH, HEIGHTH);
    private static final int STARTING_LIVES = 2;
    private static final Point STARTING_POS = new Point(0, 0);
    private static final double BASE_SPEED = 100.0;
    static final int DELAY = 11_000;

    /**
     * This method tests the constructor of the class BonusSpeed.
     */
    @Test
    void testConstructor() {
        final Point position = new Point(POSITION, POSITION);
        final BonusSpeed bonusSpeed = new BonusSpeed(position, DIMENSION);

        assertEquals(position, bonusSpeed.getPosition());
        assertEquals(DIMENSION, bonusSpeed.getDimension());
    }

    /**
     * This method tests the method doEffect of the class BonusSpeed.
     */
    @Test
    void testDoEffect() {
        final Point position = new Point(POSITION, POSITION);
        final BonusSpeed bonusSpeed = new BonusSpeed(position, DIMENSION);
        final PacMan pacman = new PacManImpl(STARTING_LIVES, DIMENSION, BASE_SPEED, STARTING_POS);

        final int initialSpeed = pacman.getSpeedLevel();
        bonusSpeed.doEffect(pacman, null);

        assertTrue(pacman.getSpeedLevel() > initialSpeed);

        final TimerTask task = new TimerTask() {
            @Override
            public void run() {
                assertEquals(initialSpeed, pacman.getSpeedLevel());
            }
        };

        /*
         * Create new Timer and Schedule the task to decrease the speed after 11 seconds
         */
        new Timer().schedule(task, DELAY);
    }
}
