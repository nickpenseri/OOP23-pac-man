package it.unibo.model.pickable.impl;

import java.awt.Image;
import java.awt.Point;
import java.awt.geom.Dimension2D;
import java.util.Timer;
import java.util.TimerTask;

import it.unibo.model.pacman.api.PacMan;
import it.unibo.model.pickable.api.EffectPickable;

/**
 * Is a basic Pickable with the bonus effect, give to pacman more speed for 10
 * seconds and 50 points.
 */
public class BonusSpeed extends PickableImpl implements EffectPickable {
    final Image image;
    static final int POINTS = 50;
    static final int DELAY = 10_000;

    public BonusSpeed(Point position, Dimension2D dimension) {
        super(position, dimension);
    }

    /** Increse pacman speed for 10 seconds.
     * @param pacman the pacman that will be affected by the effect.
    */
    @Override
    public void doEffect(final PacMan pacman) {
        // Initial action and i put the result in effectApplied
        final boolean effectApplied = pacman.increaseSpeed();

        final TimerTask task = new TimerTask() {
            @Override
            public void run() {
                // Do the action to decrease the speed
                pacman.decreaseSpeed();
            }
        };

        /*
         * Create new Timer and Schedule the task to decrease the speed after 10 seconds
         * if the effect was applied
         */
        if (effectApplied) {
            new Timer().schedule(task, DELAY);
        }
    }
}
