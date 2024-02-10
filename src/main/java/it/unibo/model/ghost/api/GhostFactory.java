package it.unibo.model.ghost.api;

import java.awt.Point;
import java.util.List;

import it.unibo.model.api.GameObject;
import it.unibo.model.physics.objectsmover.api.DirectionSelector;
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
     * @param behaviour the behaviour of the ghost
     * @return the new ghost
     */
    Ghost createRedGhost(Point pos, double initialSpeed,  DirectionSelector directionSelector, GameObject normalTarget, List<GameObject> deadTargets, List<GameObject> GameVertex);

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
     * @param behaviour the behaviour of the ghost
     * @return the new ghost
     */
    Ghost createBlueGhost(Point pos, double initialSpeed,  DirectionSelector directionSelector, GameObject normalTarget, List<GameObject> deadTargets, List<GameObject> GameVertex);

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
     * @param behaviour the behaviour of the ghost
     * @return the new ghost
     */
    Ghost createPinkGhost(Point pos, double initialSpeed,  DirectionSelector directionSelector, GameObject normalTarget, List<GameObject> deadTargets, List<GameObject> GameVertex);

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
     * @param behaviour the behaviour of the ghost
     * @return the new ghost
     */
    Ghost createOrangeGhost(Point pos, double initialSpeed,  DirectionSelector directionSelector, GameObject normalTarget, List<GameObject> deadTargets, List<GameObject> GameVertex);
}


