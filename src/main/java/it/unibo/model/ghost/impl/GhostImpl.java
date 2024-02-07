package it.unibo.model.ghost.impl;

import java.awt.Dimension;
import java.awt.Point;
import java.net.URL;
import it.unibo.model.ghost.api.Ghost;
import it.unibo.model.ghost.api.GhostColor;
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
    private int speedLevel;
    /**
     * Creates a ghost.
     * @param initialPos the initial position of the ghost
     * @param dimension the dimension of the ghost
     * @param initialSpeed the initial speed of the ghost
     * @param ghostColor the color of the ghost
     */
    public GhostImpl(final Point initialPos, final Dimension dimension, final double initialSpeed, final GhostColor ghostColor) {
        super(initialPos, dimension, initialSpeed);
        this.imagePack =  new GhostGraphicsImpl(ghostColor);
        state = GhostState.NORMAL;
        this.speedLevel = (int) initialSpeed;
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
    public int getSpeedLevel() {
        return this.speedLevel;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean increaseSpeed() {
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean decreaseSpeed() {
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setState(final GhostState state) {
        this.state = state;
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public GhostState getState() {
        return this.state;
    }
}
