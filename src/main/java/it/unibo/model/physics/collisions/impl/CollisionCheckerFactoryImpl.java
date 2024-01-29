package it.unibo.model.physics.collisions.impl;

import it.unibo.model.api.GameObject;
import it.unibo.model.physics.collisions.api.CollisionChecker;
import it.unibo.model.physics.collisions.api.CollisionCheckerFactory;

import java.awt.Dimension;
import java.awt.Rectangle;

/**
 * Implementation of CollisionCheckerFactory.
 */
public class CollisionCheckerFactoryImpl implements CollisionCheckerFactory {

    /**
     * {@inheritDoc}
     */
    @Override
    public CollisionChecker<GameObject> gameObjectChecker() {
        return (o1, o2) -> areIntersecating(o1, o2);
    }

    private boolean areIntersecating(final GameObject o1, final GameObject o2) {
        final Dimension dim1 = new Dimension((int) o1.getDimension().getWidth(), (int) o1.getDimension().getHeight());
        final Dimension dim2 = new Dimension((int) o2.getDimension().getWidth(), (int) o2.getDimension().getHeight());
        final Rectangle rect1 = new Rectangle(o1.getPosition(), dim1);
        final Rectangle rect2 = new Rectangle(o2.getPosition(), dim2);
        return rect1.intersects(rect2);
    }
}
