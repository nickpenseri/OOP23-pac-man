package it.unibo.model.physics.collisions.impl;

import it.unibo.model.api.GameObject;
import it.unibo.model.physics.collisions.api.CollisionChecker;
import it.unibo.model.physics.collisions.api.CollisionCheckerFactory;
import it.unibo.model.pickable.api.Pickable;

import java.awt.Dimension;
import java.awt.Point;
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
        final Dimension dim1 = o1 instanceof Pickable ? new Dimension(((Pickable) o1).getCollisionDimension())
                : new Dimension((int) o1.getDimension().getWidth(), (int) o1.getDimension().getHeight());
        final Dimension dim2 = o2 instanceof Pickable ? new Dimension(((Pickable) o2).getCollisionDimension())
                : new Dimension((int) o2.getDimension().getWidth(), (int) o2.getDimension().getHeight());
        final Point pos1 = o1 instanceof Pickable ? ((Pickable) o1).getCollisionPoint() : o1.getPosition();
        final Point pos2 = o2 instanceof Pickable ? ((Pickable) o2).getCollisionPoint() : o2.getPosition();
        final Rectangle rect1 = new Rectangle(pos1, dim1);
        final Rectangle rect2 = new Rectangle(pos2, dim2);
        return rect1.intersects(rect2);
    }
}
