package it.unibo.model.ghost.api;

import java.awt.Point;
import it.unibo.model.ghost.api.ghostbehaviour.GhostBehaviours;
import it.unibo.model.ghost.api.ghostbehaviour.GhostCoordinates;
/**
 * This interface models an entity of GhostFactory, an object that is responsible of
 * creating ghosts.
 */
public interface GhostFactory {
    /**
     * Create a new red ghost.
     * @param pos the position of the ghost
     * @param initialSpeed the initial speed of the ghost
     * @return the new ghost
     */
    Ghost createRedGhost(Point pos, double initialSpeed);

    /**
     * Create a new red ghost with a specific behaviour.
     * @param pos the position of the ghost
     * @param initialSpeed the initial speed of the ghost
     * @param mapCoordinates the coordinates of the map for the ghost
     * @param behaviour the behaviour of the ghost
     * @return the new ghost
     */
    Ghost createRedGhost(Point pos, double initialSpeed, GhostCoordinates mapCoordinates, GhostBehaviours behaviour);

    /**
     * Create a new blue ghost.
     * @param pos the position of the ghost
     * @param initialSpeed the initial speed of the ghost
     * @return the new ghost
     */
    Ghost createBlueGhost(Point pos, double initialSpeed);

    /**
     * Create a new blue ghost with a specific behaviour.
     * @param pos the position of the ghost
     * @param initialSpeed the initial speed of the ghost
     * @param mapCoordinates the coordinates of the map for the ghost
     * @param behaviour the behaviour of the ghost
     * @return the new ghost
     */
    Ghost createBlueGhost(Point pos, double initialSpeed, GhostCoordinates mapCoordinates, GhostBehaviours behaviour);

    /**
     * Create a new pink ghost.
     * @param pos the position of the ghost
     * @param initialSpeed the initial speed of the ghost
     * @return the new ghost
     */
    Ghost createPinkGhost(Point pos, double initialSpeed);

    /**
     * Create a new pink ghost with a specific behaviour.
     * @param pos the position of the ghost
     * @param initialSpeed the initial speed of the ghost
     * @param mapCoordinates the coordinates of the map for the ghost
     * @param behaviour the behaviour of the ghost
     * @return the new ghost
     */
    Ghost createPinkGhost(Point pos, double initialSpeed, GhostCoordinates mapCoordinates, GhostBehaviours behaviour);

    /**
     * Create a new orange ghost.
     * @param pos the position of the ghost
     * @param initialSpeed the initial speed of the ghost
     * @return the new ghost
     */
    Ghost createOrangeGhost(Point pos, double initialSpeed);

    /**
     * Create a new orange ghost with a specific behaviour.
     * @param pos the position of the ghost
     * @param initialSpeed the initial speed of the ghost
     * @param mapCoordinates the coordinates of the map for the ghost
     * @param behaviour the behaviour of the ghost
     * @return the new ghost
     */
    Ghost createOrangeGhost(Point pos, double initialSpeed, GhostCoordinates mapCoordinates, GhostBehaviours behaviour);
}


