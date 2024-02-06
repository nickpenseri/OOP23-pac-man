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
import it.unibo.model.pickable.impl.MalusSpeedGhost;

import static org.junit.jupiter.api.Assertions.*;

class MalusSpeedGhostTest {
    private static final int POSITION = 5;
    private static final int HEIGHTH = 100;
    private static final int WIDTH = 100;
    private static final Dimension DIMENSION = new Dimension(WIDTH, HEIGHTH);
    private static final int STARTING_LIVES = 2;
    private static final Point STARTING_POS = new Point(0, 0);
    private static final double BASE_SPEED = 100.0;
    static final int DELAY = 11_000;

    @Test
    void testDoEffect() {
        final Point position = new Point(POSITION, POSITION);
        final PacMan pacman = new PacManImpl(STARTING_LIVES, DIMENSION, BASE_SPEED, STARTING_POS);
        final Ghost ghost1 = new GhostImpl(position, DIMENSION, BASE_SPEED, GhostColor.RED);
        final Ghost ghost2 = new GhostImpl(position, DIMENSION, BASE_SPEED, GhostColor.BLUE);
        final List<Ghost> ghosts = new ArrayList<>();
        ghosts.add(ghost1);
        ghosts.add(ghost2);
        MalusSpeedGhost malusSpeedGhost = new MalusSpeedGhost(position, DIMENSION);

        final List<Integer> initialSpeed = new ArrayList<>();
        for (Ghost ghost : ghosts) {
            initialSpeed.add(ghost.getSpeedLevel());
        }
        
        malusSpeedGhost.doEffect(pacman, ghosts);
        for (Ghost ghost : ghosts) {
            assertTrue(ghost.getSpeedLevel() < initialSpeed.get(ghosts.indexOf(ghost)));
        }

        final TimerTask task = new TimerTask() {
            @Override
            public void run() {
                for (Ghost ghost : ghosts) {
                    assertEquals(ghost.getSpeedLevel(), initialSpeed.get(ghosts.indexOf(ghost)));
                }
            }
        };

        /*
         * Create new Timer and Schedule the task to check status after 11 seconds
         */
        new Timer().schedule(task, DELAY);
    }
}