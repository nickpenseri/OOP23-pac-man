package it.unibo.model.pickable.impl;

import java.awt.Dimension;
import java.awt.Point;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import it.unibo.model.ghost.api.Ghost;
import it.unibo.model.pacman.api.PacMan;

public class MalusSpeedGhost extends EffectPickableImpl{

    private static final int DELAY = 10_000;

    /**
     * Constructor of the BonusLife.
     * 
     * @param position  the position of the pickable.
     * @param dimension the dimension of the pickable.
     */
    public MalusSpeedGhost(final Point position, final Dimension dimension) {
        super(position, dimension);
    }

    /**
     * Decrease Ghosts speed for 10 seconds.
     * 
     * @param pacman the pacman that will be affected by the effect.
     * @param ghosts the list of ghosts that will be affected by the effect.
     */
    @Override
    public void doEffect(PacMan pacman, List<Ghost> ghosts) {
        for (Ghost ghost : ghosts) {
            ghost.decreaseSpeed();
        }

        final TimerTask task = new TimerTask() {
            @Override
            public void run() {
                // Do the action to increase the speed
                for (final Ghost ghost : ghosts) {
                    ghost.increaseSpeed();
                }
            }
        };

        /*
         * Create new Timer and Schedule the task to reset the state after 10 seconds
         */
        new Timer().schedule(task, DELAY);
    }
    
}
