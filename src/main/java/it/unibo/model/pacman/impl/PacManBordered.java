package it.unibo.model.pacman.impl;

import java.awt.Point;
import java.awt.geom.Dimension2D;
import java.net.URL;
import java.util.Objects;
import java.util.Optional;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.model.api.Direction;
import it.unibo.model.pacman.api.PacMan;

/**
 * This class represents a character pac-man that moves in a bounded space. If
 * this PacMan exceeds a border, it reappears at the opposite border.
 * It is composed of a PacMan, which is decorated, and every method delegates to
 * the decorated object, correcting the position in updateState.
 * 
 * @see PacMan
 */
public class PacManBordered implements PacMan {

    private final PacMan decorated;
    private final int borderUp;
    private final int borderRight;

    /**
     * Creates an object of this class which decorates the PacMan passed as a
     * parameter and which moves in a space with borderUp and borderRight passed as
     * parameters.
     * 
     * @param decorated   the PacMan to be decorated
     * @param borderUp    the borderUp of the bordered space
     * @param borderRight the borderRight of the bordered space
     * @throws NullPointerException     if the PacMan to be decorated is null.
     * @throws IllegalArgumentException if borderRight or borderUp are less or equal
     *                                  to zero.
     */
    @SuppressFBWarnings(value = {
            "EI_EXPOSE_REP2"
    }, justification = "Changings of the decorated object should also affect this object")
    public PacManBordered(final PacMan decorated, final int borderUp, final int borderRight) {
        this.decorated = Objects.requireNonNull(decorated);
        if (borderUp <= 0) {
            throw new IllegalArgumentException("Cannot instantiate an object with negative borderUp");
        }
        if (borderRight <= 0) {
            throw new IllegalArgumentException("Cannot instantiate an object with negative borderRight");
        }
        this.borderUp = borderUp;
        this.borderRight = borderRight;
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
    public void addPoints(final int points) {
        this.decorated.addPoints(points);
    }

    /** {@inheritDoc} */
    @Override
    public void removePoints(final int points) {
        this.decorated.removePoints(points);
    }

    /** {@inheritDoc} */
    @Override
    public int getSpeedLevel() {
        return this.decorated.getSpeedLevel();
    }

    /**
     * Changes PacMan's position to the given Point and resets the direction.
     * 
     * @param spawnPoint the new position of pacman
     * @throws IllegalArgumentException if spawnPoint is outside borders
     */
    @Override
    public void respawn(final Point spawnPoint) {
        if (!isInBorders(spawnPoint)) {
            throw new IllegalArgumentException("Cannot respawn outside the borders");
        }
        this.decorated.respawn(spawnPoint);
    }

    private boolean isInBorders(final Point position) {
        return position.getX() >= 0 && position.getX() < borderRight && position.getY() >= 0
                && position.getY() < borderUp;
    }

    /** {@inheritDoc} */
    @Override
    public URL getImageUrl() {
        return this.decorated.getImageUrl();
    }

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
    public void setDirection(final Direction direction) {
        this.decorated.setDirection(direction);
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

    /**
     * Updates the state of the Character. If pacman exceeds a border, it reappears
     * at the opposite border.
     */
    @Override
    public void updateState(final long elapsed) {
        this.decorated.updateState(elapsed);
        this.correctPosition();
    }

    private void correctPosition() {
        this.decorated.setPosition(new Point(this.correctX(), this.correctY()));
    }

    private int correctX() {
        final int actualX = (int) this.getPosition().getX();
        if (actualX >= borderRight) {
            return actualX - borderRight;
        } else if (actualX < 0) {
            return borderRight + actualX;
        } else {
            return actualX;
        }
    }

    private int correctY() {
        final int actualY = (int) this.getPosition().getY();
        if (actualY >= borderUp) {
            return actualY - borderUp;
        } else if (actualY < 0) {
            return borderUp + actualY;
        } else {
            return actualY;
        }
    }

    /** {@inheritDoc} */
    @Override
    public Point getPosition() {
        return this.decorated.getPosition();
    }

    /** {@inheritDoc} */
    @Override
    public Dimension2D getDimension() {
        return this.decorated.getDimension();
    }

    /** {@inheritDoc} */
    @Override
    public void setPosition(final Point position) {
        this.decorated.setPosition(position);
    }

}
