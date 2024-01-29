package it.unibo.model.physics.collisions.api;

/**
 * This interface models an object that can check if two objects of the same
 * type are colliding.
 * @param <T> the type of the two objects we want to check
 */
@FunctionalInterface
public interface CollisionChecker<T> {

    /**
     * Method to check if two objects are colliding.
     * @param firstObject the first of the two objects
     * @param secondObject the second of the two objects
     * @return true if the two objects are colliding, false otherwise
     */
    boolean areColliding(T firstObject, T secondObject);
}
