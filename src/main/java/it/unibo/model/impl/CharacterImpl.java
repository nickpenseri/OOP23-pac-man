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
     * @param initialSpeed the initial speed of the character
     * @throws NullPointerException if null parameters are passed
     */
    protected CharacterImpl(final Point initialPos, final Dimension dimension, final double initialSpeed) {
        this.position = new Point(Objects.requireNonNull(initialPos));
        this.dimension = new Dimension(Objects.requireNonNull(dimension));
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

    /**{@inheritDoc} */
    @Override
    public void setPosition(final Point position) {
        this.position.setLocation(position);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Direction> getDirection() {
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
        return new Dimension(this.dimension);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Point getPosition() {
        return new Point(this.position);
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
