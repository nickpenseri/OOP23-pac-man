package it.unibo.model.pacman.impl;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.geom.Dimension2D;
import java.net.URL;
import java.util.Objects;
import java.util.Optional;
import it.unibo.model.api.Direction;
import it.unibo.model.pacman.api.PacMan;

/**
 * This class models an entity of a pac-man character that moves in a free space.
 * @see PacMan
 */
public class PacManImpl implements PacMan {

    private static final int MAX_SPEED_LEVEL = 3;
    private static final int MIN_SPEED_LEVEL = -3;
    private static final double SPEED_MULTIPLIER = 0.10;
    private static final double SECOND_TO_MILLIS = 1000.0;

    private int points;
    private int lives;
    private int speedLevel;
    private final double baseSpeed;
    private final Point position;
    private Optional<Direction> dir;
    private final Dimension dimension;
    private double actualSpeed;

    /**
     * Create an instamce of the class PacManImpl.
     * @param startingLives the initial amount of lives that PacMan has
     * @param dimension the dimension of the character
     * @param baseSpeed the speed of pac-man when it is not under special effects
     * @param startingPos teh position where pac-man spawns
     * @throws IllegalArgumentException if starting lives are less or equal to zero
     * @throws NullPointerException if null objects have been passed
     */
    public PacManImpl(final int startingLives,
            final Dimension dimension,
            final double baseSpeed,
            final Point startingPos) {
        if (startingLives <= 0) {
            throw new IllegalArgumentException("Cannot instantiate an object with negative lives");
        }
        this.dimension = new Dimension(Objects.requireNonNull(dimension));
        this.baseSpeed = baseSpeed;
        this.position = Objects.requireNonNull(startingPos).getLocation();
        this.lives = startingLives;
        this.speedLevel = 0;
        this.points = 0;
        this.dir = Optional.empty();
        this.computeSpeed();
    }

    private void computeSpeed() {
        if (this.dir.isEmpty()) {
            this.actualSpeed = 0;
        } else {
            this.actualSpeed = this.baseSpeed + this.baseSpeed * SPEED_MULTIPLIER * this.speedLevel;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setDirection(final Direction direction) {
        this.dir = Optional.of(Objects.requireNonNull(direction));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateState(final long elapsed) {
        if (this.dir.isPresent()) {
            final Point translation = computeTranslation(elapsed);
            this.position.translate((int) translation.getX(), (int) translation.getY());
        }
    }

    private Point computeTranslation(final long elapsed) {
        final int movement = this.computeMovement(elapsed);
        return switch (this.dir.get()) {
            case UP -> new Point(0, movement);
            case RIGHT -> new Point(movement, 0);
            case DOWN -> new Point(0, -movement);
            case LEFT -> new Point(-movement, 0);
            default -> new Point(0, 0);
        };
    }

    private int computeMovement(final long dt) {
        return (int) (this.actualSpeed * (dt / SECOND_TO_MILLIS));
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
    public Point getPosition() {
        return this.position.getLocation();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public URL getImageUrl() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getImage'");
    }

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
    public int getPoints() {
        return this.points;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getRemainingLives() {
        return this.lives;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addLife() {
        this.lives++;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void removeLife() {
        if (this.lives == 0) {
            throw new IllegalStateException("Cannot remove life from an object with no lives");
        } else {
            this.lives--;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addPoints(final int points) {
        this.points += points;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void removePoints(final int points) {
        if (this.getPoints() < points) {
            this.points = 0;
        } else {
            this.points -= points;
        }
    }

}
