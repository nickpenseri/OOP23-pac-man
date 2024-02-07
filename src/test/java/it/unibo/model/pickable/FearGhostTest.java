package it.unibo.model.pickable;

import java.awt.Dimension;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import org.junit.jupiter.api.Test;

import it.unibo.model.ghost.api.Ghost;
import it.unibo.model.ghost.api.GhostColor;
import it.unibo.model.ghost.api.GhostState;
import it.unibo.model.ghost.impl.GhostImpl;
import it.unibo.model.pacman.api.PacMan;
import it.unibo.model.pacman.impl.PacManImpl;
import it.unibo.model.pickable.impl.FearGhost;
import static org.junit.jupiter.api.Assertions.assertEquals;

class FearGhostTest {
    private static final int POSITION = 5;
    private static final int HEIGHTH = 100;
    private static final int WIDTH = 100;
    private static final Dimension DIMENSION = new Dimension(WIDTH, HEIGHTH);
    private static final int STARTING_LIVES = 2;
    private static final Point STARTING_POS = new Point(0, 0);
    private static final double BASE_SPEED = 100.0;
    static final int DELAY = 11_000;

    /**
     * This method tests the constructor of the class FearGhost.
     */
    @Test
    void testConstructor() {
        final Point position = new Point(POSITION, POSITION);
        final FearGhost fearGhost = new FearGhost(position, DIMENSION);

        assertEquals(position, fearGhost.getPosition());
        assertEquals(DIMENSION, fearGhost.getDimension());
    }

    /**
     * This method tests the method doEffect of the class FearGhost.
     */
    @Test
    void testDoEffect() {
        final Point position = new Point(POSITION, POSITION);
        final PacMan pacman = new PacManImpl(STARTING_LIVES, DIMENSION, BASE_SPEED, STARTING_POS);
        final Ghost ghost1 = new GhostImpl(position, DIMENSION, BASE_SPEED, GhostColor.RED);
        final Ghost ghost2 = new GhostImpl(position, DIMENSION, BASE_SPEED, GhostColor.BLUE);
        final List<Ghost> ghosts = new ArrayList<>();
        ghosts.add(ghost1);
        ghosts.add(ghost2);
        final FearGhost fearGhost = new FearGhost(position, DIMENSION);

        fearGhost.doEffect(pacman, ghosts);
        for (final Ghost ghost : ghosts) {
            assertEquals(ghost.getState(), GhostState.SCARED);
        }

        final TimerTask task = new TimerTask() {
            @Override
            public void run() {
                for (final Ghost ghost : ghosts) {
                    assertEquals(ghost.getState(), GhostState.NORMAL);
                }
            }
        };

        /*
         * Create new Timer and Schedule the task to check status after 11 seconds
         */
        new Timer().schedule(task, DELAY);
    }
}
