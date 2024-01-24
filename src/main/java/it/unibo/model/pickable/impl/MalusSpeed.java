package it.unibo.model.pickable.impl;

import java.awt.Point;
import java.awt.geom.Dimension2D;
import java.util.Timer;
import java.util.TimerTask;

import it.unibo.model.pacman.api.PacMan;

/**
 * Is a basic Pickable with the bonus effect, remove to pacman speed for 10
 * seconds and give 50 points.
 */
public class MalusSpeed extends EffectPickableImpl {
    static final int DELAY = 10_000;

    /**
     * Constructor of the MalusSpeed.
     * 
     * @param position  the position of the pickable.
     * @param dimension the dimension of the pickable.
     */
    public MalusSpeed(final Point position, final Dimension2D dimension) {
        super(position, dimension);
    }

    /**
     * Remove to pacman speed for 10 seconds.
     * 
     * @param pacman the pacman that will be affected by the effect.
     */
    @Override
    public void doEffect(final PacMan pacman) {
        // Initial action and i put the result in effectApplied
        final boolean effectApplied = pacman.decreaseSpeed();

        final TimerTask task = new TimerTask() {
            @Override
            public void run() {
                // Do the action to increase the speed
                pacman.increaseSpeed();
            }
        };

        /*
         * Create new Timer and Schedule the task to increase the speed after 10 seconds
         * if the effect was applied
         */
        if (effectApplied) {
            new Timer().schedule(task, DELAY);
        }
    }
}
