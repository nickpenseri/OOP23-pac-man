package it.unibo.model.pickable.impl;

import java.awt.Dimension;
import java.awt.Point;

import it.unibo.model.pacman.api.PacMan;

/**
 * Is a basic Pickable with the bonus effect, give you a new life and 50 points.
 */
public class BonusLife extends EffectPickableImpl {

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
     */
    @Override
    public void doEffect(final PacMan pacman) {
        pacman.addLife();
    }

}
