package it.unibo.model.pickable.api;

import java.util.List;

import it.unibo.model.pacman.api.PacMan;
import it.unibo.model.ghost.api.Ghost;

/** Un puckabble item with some effect to do. */
public interface EffectPickable extends Pickable {
    /**
     * Do the effect of the pickable item.
     * 
     * @param pacman the pacman that will be affected by the effect.
     * @param ghosts the list of ghosts that will be affected by the effect.
     */
    void doEffect(PacMan pacman, List<Ghost> ghosts);
}
