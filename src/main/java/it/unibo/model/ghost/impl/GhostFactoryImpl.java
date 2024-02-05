package it.unibo.model.ghost.impl;

import java.awt.Dimension;
import java.awt.Point;

import it.unibo.model.ghost.api.Ghost;
import it.unibo.model.ghost.api.GhostColor;
import it.unibo.model.ghost.api.GhostFactory;

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
        return new GhostImpl(pos, dimension, initialSpeed, new GhostGraphicsImpl(GhostColor.RED));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Ghost createBlueGhost(final Point pos, final double initialSpeed) {
        return new GhostImpl(pos, dimension, initialSpeed, new GhostGraphicsImpl(GhostColor.BLUE));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Ghost createPinkGhost(final Point pos, final double initialSpeed) {
        return new GhostImpl(pos, dimension, initialSpeed, new GhostGraphicsImpl(GhostColor.PINK));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Ghost createOrangeGhost(final Point pos, final double initialSpeed) {
        return new GhostImpl(pos, dimension, initialSpeed, new GhostGraphicsImpl(GhostColor.ORANGE));
    }
}
