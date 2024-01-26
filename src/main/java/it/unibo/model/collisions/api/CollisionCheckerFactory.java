package it.unibo.model.collisions.api;

import it.unibo.model.api.GameObject;

/**
 * This interface models a factory of CollisionChecker.
 * @see CollisionChecker
 */
public interface CollisionCheckerFactory {
    /**
     * @return a CollisionChecker for GameObject, which are seen as rectangles.
     */
    CollisionChecker<GameObject> gameObjectChecker();
}
