package it.unibo.model.pacman.impl;

import java.awt.Dimension;
import java.awt.Point;
import java.net.URL;
import it.unibo.model.api.Direction;
import it.unibo.model.impl.CharacterImpl;
import it.unibo.model.pacman.api.ImageChooser;
import it.unibo.model.pacman.api.PacMan;

/**
 * This class models an entity of a pac-man character that moves in a free space.
 * @see PacMan
 */
public class PacManImpl extends CharacterImpl implements PacMan {

    private static final int MAX_SPEED_LEVEL = 3;
    private static final int MIN_SPEED_LEVEL = -3;
    private static final double SPEED_MULTIPLIER = 0.10;

    private int points;
    private int lives;
    private int speedLevel;
    private final double baseSpeed;
    private final ImageChooser imageManager;

    /**
     * Create an instamce of the class PacManImpl.
     * @param startingLives the initial amount of lives that PacMan has
     * @param dimension the dimension of the character
     * @param baseSpeed the speed of pac-man when it is not under special effects
     * @param startingPos the position where pac-man spawns
     * @throws IllegalArgumentException if starting lives are less or equal to zero
     * @throws IllegalArgumentException if speed is less or equal to zero
     * @throws NullPointerException if null objects have been passed
     */
    public PacManImpl(final int startingLives,
            final Dimension dimension,
            final double baseSpeed,
            final Point startingPos) {
        super(startingPos, dimension, baseSpeed);
        if (startingLives <= 0) {
            throw new IllegalArgumentException("Cannot instantiate an object with negative lives");
        }
        if (baseSpeed <= 0) {
            throw new IllegalArgumentException("Cannot create an obkìject with negative speed");
        }
        this.lives = startingLives;
        this.speedLevel = 0;
        this.points = 0;
        this.baseSpeed = baseSpeed;
        this.computeSpeed();
        this.imageManager = new ImageChooserImpl();
    }

    private void computeSpeed() {
        if (super.getDirection().isEmpty()) {
            super.setSpeed(0);
        } else {
            super.setSpeed(this.baseSpeed + this.baseSpeed * SPEED_MULTIPLIER * this.speedLevel);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateState(final long elapsed) {
        super.updateState(elapsed);
        this.imageManager.update();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setDirection(final Direction direction) {
        super.setDirection(direction);
        this.computeSpeed();
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
    public URL getImageUrl() {
        return this.imageManager.actualImageUrl(this.getDirection());
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
        if (points < 0) {
            throw new IllegalArgumentException("Cannot add negative points");
        }
        this.points += points;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void removePoints(final int points) {
        if (points < 0) {
            throw new IllegalArgumentException("Cannot remove negative points");
        }
        if (this.getPoints() < points) {
            this.points = 0;
        } else {
            this.points -= points;
        }
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
    public void respawn(final Point spawnPoint) {
        super.setPosition(spawnPoint);
        super.resetDirection();
    }

}
