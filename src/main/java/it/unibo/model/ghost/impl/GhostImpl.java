package it.unibo.model.ghost.impl;

import java.awt.Dimension;
import java.awt.Point;
import java.net.URL;
import java.util.Objects;

import it.unibo.model.ghost.api.Ghost;
import it.unibo.model.ghost.api.GhostGraphics;
import it.unibo.model.ghost.api.GhostState;
import it.unibo.model.impl.CharacterImpl;

/**
 * Implementation of the ghost.
 * @see Ghost
 * @see CharacterImpl
 */
public class GhostImpl extends CharacterImpl implements Ghost {

    private final GhostGraphics imagePack;
    private GhostState state;
    /**
     * Creates a ghost.
     * @param initialPos the initial position of the ghost
     * @param dimension the dimension of the ghost
     * @param initialSpeed the initial speed of the ghost
     * @param imagePack the image pack of the ghost
     */
    public GhostImpl(final Point initialPos, final Dimension dimension, final double initialSpeed, final GhostGraphics imagePack) {
        super(initialPos, dimension, initialSpeed);
        this.imagePack =  Objects.requireNonNull(imagePack);
        state = GhostState.NORMAL;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public URL getImageUrl() {
        imagePack.setState(state);
        return imagePack.actualImageUrl(super.getDirection());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateState(final long elapsed) {
        super.updateState(elapsed);
        this.imagePack.update();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean increaseSpeed() {
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean decreaseSpeed() {
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setState(final GhostState state) {
        this.state = state;
    }
}
