package it.unibo.model.pacman.impl;

import java.awt.Point;
import java.awt.geom.Dimension2D;
import java.net.URL;
import java.util.Objects;
import java.util.Optional;

import it.unibo.model.api.Direction;
import it.unibo.model.pacman.api.PacMan;
import it.unibo.model.pacman.api.PacManDecorator;

/**
 * This class represents an implementation of PacManDecorator, with methods
 * respawn. updateState and correctPosition left to subclasses.
 */
public abstract class PacManDecoratorImpl implements PacManDecorator {

    private final PacMan decorated;

    /**
     * Creates an object of PacManDecoratorImpl whic decorates another pacman.
     * 
     * @param decorated the pacman to be decorated.
     */
    protected PacManDecoratorImpl(final PacMan decorated) {
        this.decorated = Objects.requireNonNull(decorated);
    }

    /** {@inheritDoc} */
    @Override
    public int getPoints() {
        return this.decorated.getPoints();
    }

    /** {@inheritDoc} */
    @Override
    public int getRemainingLives() {
        return this.decorated.getRemainingLives();
    }

    /** {@inheritDoc} */
    @Override
    public void addLife() {
        this.decorated.addLife();
    }

    /** {@inheritDoc} */
    @Override
    public void removeLife() {
        this.decorated.removeLife();
    }

    /** {@inheritDoc} */
    @Override
    public void addPoints(int points) {
        this.decorated.addPoints(points);
    }

    /** {@inheritDoc} */
    @Override
    public void removePoints(int points) {
        this.decorated.removePoints(points);
    }

    /** {@inheritDoc} */
    @Override
    public int getSpeedLevel() {
        return this.decorated.getSpeedLevel();
    }

    @Override
    public abstract void respawn(Point spawnPoint);

    /** {@inheritDoc} */
    @Override
    public void setDirection(Direction direction) {
        this.decorated.setDirection(direction);
    }

    /** {@inheritDoc} */
    @Override
    public void setPosition(Point position) {
        this.decorated.setPosition(position);
    }

    /** {@inheritDoc} */
    @Override
    public Optional<Direction> getDirection() {
        return this.decorated.getDirection();
    }

    /** {@inheritDoc} */
    @Override
    public void resetDirection() {
        this.decorated.resetDirection();
    }

    @Override
    public abstract void updateState(long elapsed);

    /** {@inheritDoc} */
    @Override
    public boolean increaseSpeed() {
        return this.decorated.increaseSpeed();
    }

    /** {@inheritDoc} */
    @Override
    public boolean decreaseSpeed() {
        return this.decorated.decreaseSpeed();
    }

    /** {@inheritDoc} */
    @Override
    public Point getPosition() {
        return this.decorated.getPosition();
    }

    /** {@inheritDoc} */
    @Override
    public URL getImageUrl() {
        return this.decorated.getImageUrl();
    }

    /** {@inheritDoc} */
    @Override
    public Dimension2D getDimension() {
        return this.decorated.getDimension();
    }

    @Override
    public abstract void correctPosition();

}
