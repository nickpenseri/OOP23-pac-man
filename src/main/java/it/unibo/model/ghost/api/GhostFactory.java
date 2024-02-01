package it.unibo.model.ghost.api;

import java.awt.Dimension;
import java.awt.Point;


/**
 * This interface models an entity of GhostFactory, an object that is responsible of
 * creating ghosts.
 */
public interface GhostFactory {
    /**
     * Create a new red ghost.
     * @param pos the position of the ghost
     * @param dimension the dimension of the ghost
     * @return the new ghost
     */
    Ghost createRedGhost(Point pos, Dimension dimension);

    /**
     * Create a new blue ghost.
     * @param pos the position of the ghost
     * @param dimension the dimension of the ghost
     * @return the new ghost
     */
    Ghost createBlueGhost(Point pos, Dimension dimension);

    /**
     * Create a new pink ghost.
     * @param pos the position of the ghost
     * @param dimension the dimension of the ghost
     * @return the new ghost
     */
    Ghost createPinkGhost(Point pos, Dimension dimension);

    /**
     * Create a new orange ghost.
     * @param pos the position of the ghost
     * @param dimension the dimension of the ghost
     * @return the new ghost
     */
    Ghost createOrangeGhost(Point pos, Dimension dimension);
}
