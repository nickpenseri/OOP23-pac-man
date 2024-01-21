package it.unibo.model.pickable.impl;

import it.unibo.model.pacman.api.PacMan;
import it.unibo.model.pickable.api.EffectPickable;

/**
 * Is a basic Pickable with the bonus effect, give you a new life and 50 points.
 */
public class BonusLife extends PickableImpl implements EffectPickable {
    static final int POINTS = 50;

    /** Give you a new life. 
     * @param pacman the pacman that will be affected by the effect.
    */
    @Override
    public void doEffect(final PacMan pacman) {
        pacman.addLife();
    }

}
