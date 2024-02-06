package it.unibo.model.pickable.impl;

import java.awt.Dimension;
import java.awt.Point;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import it.unibo.model.ghost.api.Ghost;
import it.unibo.model.ghost.api.GhostState;
import it.unibo.model.pacman.api.PacMan;

public class FearGhost  extends EffectPickableImpl{

    private static final int DELAY = 10_000;

    /**
     * Constructor of the BonusLife.
     * 
     * @param position  the position of the pickable.
     * @param dimension the dimension of the pickable.
     */
    public FearGhost(final Point position, final Dimension dimension) {
        super(position, dimension);
    }

    /**
     * Fear Ghosts for 10 seconds.
     * 
     * @param pacman the pacman that will be affected by the effect.
     * @param ghosts the list of ghosts that will be affected by the effect.
     */
    @Override
    public void doEffect(final PacMan pacman, final List<Ghost> ghosts) {
        for (final Ghost ghost : ghosts) {
            ghost.setState(GhostState.SCARED);
        }

        final TimerTask task = new TimerTask() {
            @Override
            public void run() {
                // Do the action to reset the state
                for (final Ghost ghost : ghosts) {
                    ghost.setState(GhostState.NORMAL);
                }
            }
        };

        /*
         * Create new Timer and Schedule the task to reset the state after 10 seconds
         */
        new Timer().schedule(task, DELAY);

    }
    
}
