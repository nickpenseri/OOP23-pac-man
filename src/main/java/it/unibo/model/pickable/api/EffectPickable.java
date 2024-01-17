package it.unibo.model.pickable.api;

import it.unibo.model.pacman.api.PacMan;

/** Un puckabble item with some effect to do. */
public interface EffectPickable extends Pickable {
    /** Do the effect of the pickable item. 
     * @param pacman the pacman that will be affected by the effect.
    */
    void doEffect(PacMan pacman);
}
