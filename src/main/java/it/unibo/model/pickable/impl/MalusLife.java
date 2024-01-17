package it.unibo.model.pickable.impl;

import it.unibo.model.pacman.api.PacMan;
import it.unibo.model.pickable.api.EffectPickable;

/**
 * Is a basic Pickable with the bonus effect, remove to you a life and give you 50
 * points.
 */
public class MalusLife extends PickableImpl implements EffectPickable {
    static final int POINTS = 50;

    /** Remove to you a new life. 
     * @param pacman the pacman that will be affected by the effect.
    */
    @Override
    public void doEffect(final PacMan pacman) {
        pacman.removeLife();
    }
}
