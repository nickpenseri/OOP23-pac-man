package it.unibo.model.pickable.impl;

import java.awt.Dimension;
import java.awt.Point;
import java.util.List;
import java.util.Optional;

import it.unibo.model.pacman.api.PacMan;
import it.unibo.model.ghost.api.Ghost;

/**
 * Is a basic Pickable with the bonus effect, give you a new life and 50 points.
 */
public class BonusLife extends EffectPickableImpl {
    private static final String EFFECT_STRING = "You have a new life!";

    /**
     * Constructor of the BonusLife.
     * 
     * @param position  the position of the pickable.
     * @param dimension the dimension of the pickable.
     */
    public BonusLife(final Point position, final Dimension dimension) {
        super(position, dimension);
    }

    /**
     * Give you a new life.
     * 
     * @param pacman the pacman that will be affected by the effect.
     * @param ghosts the list of ghosts that will be affected by the effect.
     */
    @Override
    public void doEffect(final PacMan pacman, final List<Ghost> ghosts) {
        pacman.addLife();
    }

    /**
     * Get the effect text.
     * @return the effect text.
     */
    @Override
    public Optional<String> getEffectText() {
        return Optional.of(EFFECT_STRING);
    }

}
