package it.unibo.model.pickable.impl;

import java.awt.Image;
import java.awt.Point;
import java.awt.geom.Dimension2D;

import it.unibo.model.pacman.api.PacMan;
import it.unibo.model.pickable.api.EffectPickable;

/**
 * Is a basic Pickable with the bonus effect, give you (basic_points * 100)
 * points.
 */
public class BonusPoints extends PickableImpl implements EffectPickable {
    final Image image;
    
    public BonusPoints(Point position, Dimension2D dimension) {
        super(position, dimension);
    }

    /** Give you (basic_points * 100) points.
     * @param pacman the pacman that will be affected by the effect.
    */
    @Override
    public void doEffect(final PacMan pacman) {
        pacman.addPoints(POINTS * 100);
    }

}
