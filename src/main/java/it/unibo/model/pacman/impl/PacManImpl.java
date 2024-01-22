package it.unibo.model.pacman.impl;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Point;
import java.awt.geom.Dimension2D;
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

    private int points;
    private int lives;
    private int speedLevel;
    private final double baseSpeed;
    private final Point position;
    private Optional<Direction> dir;
    private final Dimension dimension;
    private long lastUpdate;
    private double actualSpeed;

    /**
     * Create an instamce of the class PacManImpl.
     * @param startingLives the initial amount of lives that PacMan has
     * @param dimension the dimension of the character
     * @param baseSpeed the speed of pac-man when it is not under special effects
     * @param startingPos teh position where pac-man spawns
     */
    public PacManImpl(final int startingLives,
            final Dimension dimension,
            final double baseSpeed,
            final Point startingPos) {
        this.dimension = new Dimension(Objects.requireNonNull(dimension));
        this.baseSpeed = baseSpeed;
        this.position = Objects.requireNonNull(startingPos).getLocation();
        this.lives = startingLives;
        this.speedLevel = 0;
        this.points = 0;
        this.dir = Optional.empty();
        this.lastUpdate = System.currentTimeMillis();
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
    public void updateState() {
        final long now = System.currentTimeMillis();
        if (this.dir.isPresent()) {
            final int movement = this.computeMovement(now - this.lastUpdate);
            final Point translation = switch (this.dir.get()) {
                case UP -> new Point(movement, 0);
                case RIGHT -> new Point(0, movement);
                case DOWN -> new Point(-movement, 0);
                case LEFT -> new Point(0, -movement);
            };
            this.position.translate((int) translation.getX(), (int) translation.getY());
        }
        this.lastUpdate = now;
    }

    private int computeMovement(final long dt) {
        return (int) (this.actualSpeed * dt);
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
    public Image getImage() {
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
        this.lives--;
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
        this.addPoints(-points);
    }

}
