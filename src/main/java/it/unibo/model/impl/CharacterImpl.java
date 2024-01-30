package it.unibo.model.impl;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.geom.Dimension2D;
import java.net.URL;
import java.util.Objects;
import java.util.Optional;

import it.unibo.model.api.Character;
import it.unibo.model.api.Direction;

/**
 * Generic implementation for a character, with also methods left to subclasses.
 * @see Character
 */
public abstract class CharacterImpl implements Character {

    private static final int SECOND_TO_MILLIS = 1000;

    private final Point position;
    private final Dimension dimension;
    private Optional<Direction> direction;
    private double speed;

    /**
     * Creates a character, only for subclasses.
     * @param initialPos the initial position of the character
     * @param dimension the dimension of the character
     * @param initialSpeed teh initial speed of the character
     */
    protected CharacterImpl(final Point initialPos, final Dimension dimension, final double initialSpeed) {
        this.position = Objects.requireNonNull(initialPos);
        this.dimension = Objects.requireNonNull(dimension);
        this.direction = Optional.empty();
        this.speed = initialSpeed;
    }

    /**
     * Sets the speed to a specified value.
     * @param speed the new speed
     */
    protected void setSpeed(final double speed) {
        this.speed = speed;
    }

    /**
     * Sets the position to a specified point.
     * @param position the new position
     */
    protected void setPosition(final Point position) {
        this.position.setLocation(position);
    }

    /**
     * Getter for the direction.
     * @return the actual direction
     */
    protected Optional<Direction> getDirection() {
        return this.direction;
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
    public Point getPosition() {
        return this.position.getLocation();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setDirection(final Direction direction) {
        this.direction = Optional.of(Objects.requireNonNull(direction));
    }

    /**
     * {@inheritDoc}
     */
    @Override
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
