package it.unibo.model.pickable.impl;

import java.awt.Dimension;
import java.awt.Point;

import it.unibo.model.pacman.api.PacMan;

/**
 * Is a basic Pickable with the bonus effect, remove to you a life and give you
 * 50
 * points.
 */
public class MalusLife extends EffectPickableImpl {

    /**
     * Constructor of the MalusLife.
     * 
     * @param position  the position of the pickable.
     * @param dimension the dimension of the pickable.
     */
    public MalusLife(final Point position, final Dimension dimension) {
        super(position, dimension);
    }

    /**
     * Remove to you a new life.
     * 
     * @param pacman the pacman that will be affected by the effect.
     */
    @Override
    public void doEffect(final PacMan pacman) {
        pacman.removeLife();
    }
}
