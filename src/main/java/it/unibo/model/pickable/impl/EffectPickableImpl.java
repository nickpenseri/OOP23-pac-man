package it.unibo.model.pickable.impl;

import java.net.URL;
import java.awt.Point;
import java.awt.geom.Dimension2D;

import it.unibo.model.pacman.api.PacMan;
import it.unibo.model.pickable.api.EffectPickable;

/**
 * Is a basic Pickable with the bonus effect and give you 50 points.
 */
public abstract class EffectPickableImpl extends PickableImpl implements EffectPickable {
    static final URL IMAGE_URL = null;
    static final int POINTS = 50;

    /**
     * Constructor of the MalusLife.
     * 
     * @param position  the position of the pickable.
     * @param dimension the dimension of the pickable.
     */
    public EffectPickableImpl(final Point position, final Dimension2D dimension) {
        super(position, dimension);
    }

    /**
     * Do the effect of the pickable.
     * 
     * @param pacman the pacman that will be affected by the effect.
     */
    @Override
    public abstract void doEffect(PacMan pacman);

}
