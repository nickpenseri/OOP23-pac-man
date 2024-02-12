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

    private static final int MAX_SPEED_LEVEL = 1000;
    private static final int MIN_SPEED_LEVEL = -3;
    private static final double SPEED_MULTIPLIER = 0.10;
    private final double baseSpeed;
    private int speedLevel;

    private final GhostGraphics imagePack;
    private GhostState state;
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
        this.speedLevel = 0;
        this.baseSpeed = initialSpeed;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public URL getImageUrl() {
        return imagePack.actualImageUrl(super.getDirection());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateState(final long elapsed) {
        super.updateState(elapsed);
        this.imagePack.update(elapsed);
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
        if (this.speedLevel < MAX_SPEED_LEVEL) {
            this.speedLevel++;
            this.computeSpeed();
            return true;
        } else {
            return false;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean decreaseSpeed() {
        if (this.speedLevel > MIN_SPEED_LEVEL) {
            this.speedLevel--;
            this.computeSpeed();
            return true;
        } else {
            return false;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setState(final GhostState state) {
        this.state = state;
        imagePack.setState(state);
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public GhostState getState() {
        return this.state;
    }

    private void computeSpeed() {
        if (super.getDirection().isEmpty()) {
            super.setSpeed(0);
        } else {
            super.setSpeed(this.baseSpeed + this.baseSpeed * SPEED_MULTIPLIER * this.speedLevel);
        }
    }
}
