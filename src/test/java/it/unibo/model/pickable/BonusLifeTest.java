package it.unibo.model.pickable;

import org.junit.jupiter.api.Test;
import it.unibo.model.pacman.api.PacMan;
import it.unibo.model.pacman.impl.PacManImpl;
import it.unibo.model.pickable.impl.BonusLife;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.awt.Point;
import java.awt.Dimension;

/**
 * This class is used to test the behaviour of the class BonusLife.
 */
class BonusLifeTest {

    private static final int POSITION = 5;
    private static final int HEIGHTH = 100;
    private static final int WIDTH = 100;
    private static final Dimension DIMENSION = new Dimension(WIDTH, HEIGHTH);
    private static final int STARTING_LIVES = 2;
    private static final Point STARTING_POS = new Point(0, 0);
    private static final double BASE_SPEED = 100.0;

    /**
     * This method tests the constructor of the class BonusLife.
     */
    @Test
    void testConstructor() {
        final Point position = new Point(POSITION, POSITION);
        final BonusLife bonusLife = new BonusLife(position, DIMENSION);
        assertEquals(position, bonusLife.getPosition());
        assertEquals(DIMENSION, bonusLife.getDimension());
    }

    /**
     * This method tests the method doEffect of the class BonusLife.
     */
    @Test
    void testDoEffect() {
        final Point position = new Point(POSITION, POSITION);
        final BonusLife bonusLife = new BonusLife(position, DIMENSION);
        final PacMan pacman = new PacManImpl(STARTING_LIVES, DIMENSION, BASE_SPEED, STARTING_POS);
        final int initialLives = pacman.getRemainingLives();
        bonusLife.doEffect(pacman, null);
        assertEquals(initialLives + 1, pacman.getRemainingLives());
    }
}
