package it.unibo.model.pickable.impl;

import java.util.Timer;
import java.util.TimerTask;

import it.unibo.model.pacman.api.PacMan;
import it.unibo.model.pickable.api.EffectPickable;

/**
 * Is a basic Pickable with the bonus effect, remove to pacman speed for 10
 * seconds and give 50 points.
 */
public class MalusSpeed extends PickableImpl implements EffectPickable {
    static final int POINTS = 50;
    static final int DELAY = 10_000;

    /** Remove to pacman speed for 10 seconds.
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
