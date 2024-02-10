package it.unibo.model.ghost.impl;

import java.awt.Dimension;
import java.awt.Point;
import java.util.List;

import it.unibo.model.api.GameObject;
import it.unibo.model.ghost.api.FollowingGhost;
import it.unibo.model.ghost.api.Ghost;
import it.unibo.model.ghost.api.GhostColor;
import it.unibo.model.ghost.api.GhostFactory;
import it.unibo.model.ghost.impl.GhostBehaviour.FollowingGhostImpl;
import it.unibo.model.ghost.impl.GhostBehaviour.NormalGhost;
import it.unibo.model.physics.objectsmover.api.DirectionSelector;

/**
 * This class represents an implementation of {@link GhostFactory}.
 */
public class GhostFactoryImpl implements GhostFactory {
    private final Dimension dimension;

    /**
     * Creates an object of this class.
     * @param width the width of the ghost
     * @param height the height of the ghost
     */
    public GhostFactoryImpl(final int width, final int height) {
        if (width <= 0 || height <= 0) {
            throw new IllegalArgumentException("Width and Height must be positive");
        } 
       dimension = new Dimension(width, height);
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public Ghost createRedGhost(final Point pos, final double initialSpeed) {
        return new GhostImpl(pos, dimension, initialSpeed, GhostColor.RED);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public FollowingGhost createRedGhost(final Point pos, final double initialSpeed, final DirectionSelector directionSelector, final GameObject normalTarget, final List<GameObject> deadTargets, final  List<GameObject> GameVertex) {
        final Ghost ghost = createRedGhost(pos, initialSpeed);
        return new NormalGhost(ghost, directionSelector, normalTarget, deadTargets, GameVertex);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Ghost createBlueGhost(final Point pos, final double initialSpeed) {
        return new GhostImpl(pos, dimension, initialSpeed, GhostColor.BLUE);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public FollowingGhost createBlueGhost(final Point pos, final double initialSpeed, final DirectionSelector directionSelector, final GameObject normalTarget, final List<GameObject> deadTargets, final  List<GameObject> GameVertex) {
        final Ghost ghost = createBlueGhost(pos, initialSpeed);
        return new NormalGhost(ghost, directionSelector, normalTarget, deadTargets, GameVertex);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Ghost createPinkGhost(final Point pos, final double initialSpeed) {
        return new GhostImpl(pos, dimension, initialSpeed, GhostColor.PINK);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public FollowingGhost createPinkGhost(final Point pos, final double initialSpeed,final DirectionSelector directionSelector, final GameObject normalTarget, final List<GameObject> deadTargets, final  List<GameObject> GameVertex) {
        final Ghost ghost = createPinkGhost(pos, initialSpeed);
        return new NormalGhost(ghost, directionSelector, normalTarget, deadTargets, GameVertex);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Ghost createOrangeGhost(final Point pos, final double initialSpeed) {
        return new GhostImpl(pos, dimension, initialSpeed, GhostColor.ORANGE);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public FollowingGhost createOrangeGhost(final Point pos, final double initialSpeed,final DirectionSelector directionSelector, final GameObject normalTarget, final List<GameObject> deadTargets, final  List<GameObject> GameVertex) {
        final Ghost ghost = createOrangeGhost(pos, initialSpeed);
        return new NormalGhost(ghost, directionSelector, normalTarget, deadTargets, GameVertex);
    }

}
