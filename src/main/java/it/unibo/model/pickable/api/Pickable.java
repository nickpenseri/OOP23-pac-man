package it.unibo.model.pickable.api;

import it.unibo.model.pacman.api.PacMan;

/** Pickable item for get points. */
public interface Pickable {
    /**
     * Add the points of the item.
     * @param pacman the pacman that eat the item
     */
    void addPointsPickable(PacMan pacman);
}
