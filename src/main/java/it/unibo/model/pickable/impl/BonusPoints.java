package it.unibo.model.pickable.impl;

import it.unibo.model.pacman.api.PacMan;
import it.unibo.model.pickable.api.EffectPickable;

/**
 * Is a basic Pickable with the bonus effect, give you (basic_points * 100)
 * points.
 */
public class BonusPoints extends PickableImpl implements EffectPickable {

    /** Give you (basic_points * 100) points.
     * @param pacman the pacman that will be affected by the effect.
    */
    @Override
    public void doEffect(final PacMan pacman) {
        pacman.addPoints(POINTS * 100);
    }

}
