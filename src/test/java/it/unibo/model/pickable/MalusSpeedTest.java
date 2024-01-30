package it.unibo.model.pickable;

import org.junit.jupiter.api.Test;
import it.unibo.model.pacman.api.PacMan;
import it.unibo.model.pacman.impl.PacManImpl;
import it.unibo.model.pickable.impl.MalusSpeed;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.awt.Point;
import java.util.Timer;
import java.util.TimerTask;
import java.awt.Dimension;

/**
 * This class is used to test the behaviour of the class MalusSpeed.
 */
class MalusSpeedTest {

    private static final int POSITION = 5;
    private static final int HEIGHTH = 100;
    private static final int WIDTH = 100;
    private static final Dimension DIMENSION = new Dimension(WIDTH, HEIGHTH);
    private static final int STARTING_LIVES = 2;
    private static final Point STARTING_POS = new Point(0, 0);
    private static final double BASE_SPEED = 100.0;
    static final int DELAY = 11_000;

    /**
     * This method tests the constructor of the class MalusSpeed.
     */
    @Test
    void testConstructor() {
        final Point position = new Point(POSITION, POSITION);
        final MalusSpeed malusSpeed = new MalusSpeed(position, DIMENSION);

        assertEquals(position, malusSpeed.getPosition());
        assertEquals(DIMENSION, malusSpeed.getDimension());
    }

    /**
     * This method tests the method doEffect of the class MalusSpeed.
     * @throws InterruptedException 
     */
    @Test
    void testDoEffect() throws InterruptedException {
        final Point position = new Point(POSITION, POSITION);
        final MalusSpeed malusSpeed = new MalusSpeed(position, DIMENSION);
        final PacMan pacman = new PacManImpl(STARTING_LIVES, DIMENSION, BASE_SPEED, STARTING_POS);

        final int initialSpeed = pacman.getSpeedLevel();
        malusSpeed.doEffect(pacman);

        assertTrue(pacman.getSpeedLevel() < initialSpeed);

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
