package it.unibo.model.pickable.impl;

import java.awt.Dimension;
import java.awt.Point;
import java.util.List;
import java.util.Optional;

import it.unibo.model.ghost.api.Ghost;
import it.unibo.model.ghost.api.GhostState;
import it.unibo.model.pacman.api.PacMan;

/**
 * This class is used to create a pickable that will fear the ghosts for 10
 * seconds.
 */
public class FearGhost extends EffectPickableImpl {
    private static final String EFFECT_STRING = "The ghosts are scared for 10 seconds!";

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
            if (ghost.getState().equals(GhostState.NORMAL)) {
                ghost.setState(GhostState.SCARED);
            }
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
