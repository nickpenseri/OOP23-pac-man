package it.unibo.model.pickable.impl;

import java.awt.Dimension;
import java.awt.Point;
import java.util.Timer;
import java.util.TimerTask;
import java.util.List;
import java.util.Optional;

import it.unibo.model.pacman.api.PacMan;
import it.unibo.model.ghost.api.Ghost;

/**
 * Is a basic Pickable with the bonus effect, give to pacman more speed for 10
 * seconds and 50 points.
 */
public class BonusSpeed extends EffectPickableImpl {
    private static final int DELAY = 10_000;
    private static final String EFFECT_STRING = "You have more speed for 10 seconds!";

    /**
     * Constructor of the BonusSpeed.
     * 
     * @param position  the position of the pickable.
     * @param dimension the dimension of the pickable.
     */
    public BonusSpeed(final Point position, final Dimension dimension) {
        super(position, dimension);
    }

    /**
     * Increse pacman speed for 10 seconds.
     * 
     * @param pacman the pacman that will be affected by the effect.
     * @param ghosts the list of ghosts that will be affected by the effect.
     */
    @Override
    public void doEffect(final PacMan pacman, final List<Ghost> ghosts) {
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

    /**
     * Get the effect text.
     * 
     * @return the effect text.
     */
    @Override
    public Optional<String> getEffectText() {
        return Optional.of(EFFECT_STRING);
    }
}
