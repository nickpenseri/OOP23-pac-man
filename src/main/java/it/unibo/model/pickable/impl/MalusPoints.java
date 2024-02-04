package it.unibo.model.pickable.impl;

import java.awt.Dimension;
import java.awt.Point;

import it.unibo.model.pacman.api.PacMan;

/**
 * Is a basic Pickable with the bonus effect, remove you (basic_points * 100)
 * points.
 */
public class MalusPoints extends EffectPickableImpl {

    /**
     * Constructor of the MalusPoints.
     * 
     * @param position  the position of the pickable.
     * @param dimension the dimension of the pickable.
     */
    public MalusPoints(final Point position, final Dimension dimension) {
        super(position, dimension);
    }

    /**
     * Remove you (basic_points * 100) points.
     * 
     * @param pacman the pacman that will be affected by the effect.
     */
    @Override
    public void doEffect(final PacMan pacman) {
        pacman.removePoints(POINTS * 100);
    }
}
