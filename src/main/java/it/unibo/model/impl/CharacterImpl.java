package it.unibo.model.impl;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.geom.Dimension2D;
import java.net.URL;
import java.util.Objects;
import java.util.Optional;

import it.unibo.model.api.Character;
import it.unibo.model.api.Direction;

public abstract class CharacterImpl implements Character{

    private static final int SECOND_TO_MILLIS = 1000;

    private final Point position;
    private final Dimension dimension;
    private Optional<Direction> direction;
    private double speed;

    protected CharacterImpl(final Point initialPos, final Dimension dimension, final double initialSpeed) {
        this.position = Objects.requireNonNull(initialPos);
        this.dimension = Objects.requireNonNull(dimension);
        this.direction = Optional.empty();
        this.speed = initialSpeed;
    }

    protected void setSpeed(final double speed) {
        this.speed = speed;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateState(final long elapsed) {
        if (this.direction.isPresent()) {
            final Point translation = computeTranslation(elapsed);
            this.position.translate((int) translation.getX(), (int) translation.getY());
        }
    }

    private Point computeTranslation(final long elapsed) {
        final int movement = this.computeMovement(elapsed);
        return switch (this.direction.get()) {
            case UP -> new Point(0, movement);
            case RIGHT -> new Point(movement, 0);
            case DOWN -> new Point(0, -movement);
            case LEFT -> new Point(-movement, 0);
            default -> new Point(0, 0);
        };
    }

    private int computeMovement(final long dt) {
        return (int) (this.speed * ((double) dt / SECOND_TO_MILLIS));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public abstract URL getImageUrl();

    /**
     * {@inheritDoc}
     */
    @Override
    public Dimension2D getDimension() {
        return this.dimension.getSize();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setDirection(Direction direction) {
        this.direction = Optional.of(Objects.requireNonNull(direction));
    }

    /**
     * {@inheritDoc}
     */
    public void resetDirection() {
        this.direction = Optional.empty();
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public abstract boolean increaseSpeed();

    /**
     * {@inheritDoc}
     */
    @Override
    public abstract boolean decreaseSpeed();
    
}
