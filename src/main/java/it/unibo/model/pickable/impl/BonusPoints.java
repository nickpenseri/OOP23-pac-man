package it.unibo.model.pickable.impl;

import java.awt.Dimension;
import java.awt.Point;
import java.util.List;

import it.unibo.model.pacman.api.PacMan;
import it.unibo.model.ghost.api.Ghost;

/**
 * Is a basic Pickable with the bonus effect, give you (basic_points * 100)
 * points.
 */
public class BonusPoints extends EffectPickableImpl {

    /**
     * Constructor of the BonusPoints.
     * 
     * @param position  the position of the pickable.
     * @param dimension the dimension of the pickable.
     */
    public BonusPoints(final Point position, final Dimension dimension) {
        super(position, dimension);
    }

    /**
     * Give you (basic_points * 100) points.
     * 
     * @param pacman the pacman that will be affected by the effect.
     * @param ghosts the list of ghosts that will be affected by the effect.
     */
    @Override
    public void doEffect(final PacMan pacman, final List<Ghost> ghosts) {
        pacman.addPoints(POINTS * 100);
    }

}
