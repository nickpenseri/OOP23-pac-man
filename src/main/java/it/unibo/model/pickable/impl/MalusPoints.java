package it.unibo.model.pickable.impl;

import it.unibo.model.pacman.api.PacMan;
import it.unibo.model.pickable.api.EffectPickable;

/** Is a basic Pickable with the bonus effect, remove you (basic_points * 100) points. */
public class MalusPoints extends PickableImpl implements EffectPickable {

    /** Remove you (basic_points * 100) points.
     * @param pacman the pacman that will be affected by the effect.
    */
    @Override
    public void doEffect(final PacMan pacman) {
        pacman.removePoints(POINTS * 100);
    }
}
