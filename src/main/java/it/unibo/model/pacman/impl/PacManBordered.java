package it.unibo.model.pacman.impl;

import java.awt.Point;

import it.unibo.model.pacman.api.PacMan;

/**
 * This class represents a character pac-man that moves in a bounded space. If
 * this PacMan exceeds a border, it reappears at the opposite border.
 * It is composed of a PacMan, which is decorated, and every method delegates to
 * the decorated object, correcting the position in updateState.
 * 
 * @see PacMan
 */
public class PacManBordered extends PacManDecoratorImpl {

    private final int borderUp;
    private final int borderRight;
    private final int borderLeft;
    private final int borderDown;

    /**
     * Creates an object of this class which decorates the PacMan passed as a
     * parameter and which moves in a space with borderUp and borderRight passed as
     * parameters and with 0 as borderLeft and borderDown.
     * 
     * @param decorated   the PacMan to be decorated
     * @param borderUp    the borderUp of the bordered space
     * @param borderRight the borderRight of the bordered space
     * @throws NullPointerException     if the PacMan to be decorated is null.
     * @throws IllegalArgumentException if decorated is outside borders.
     */
    public PacManBordered(final PacMan decorated, final int borderUp, final int borderRight) {
        this(decorated, borderUp, borderRight, 0, 0);
    }

    /**
     * Creates an object of this class which decorates the PacMan passed as a
     * parameter and which moves in a space with borderUp,borderRight, borderDown
     * and borderLeft passed as parameters.
     * 
     * @param decorated   the PacMan to be decorated
     * @param borderUp    the borderUp of the bordered space
     * @param borderRight the borderRight of the bordered space
     * @param borderDown  the borderDown of the bordered space
     * @param borderLeft  the borderLeft of the bordered space
     * @throws NullPointerException     if the PacMan to be decorated is null.
     * @throws IllegalArgumentException if borderRight or borderUp are less or equal
     *                                  to zero or if decorated is outside borders
     */
    public PacManBordered(final PacMan decorated, final int borderUp, final int borderRight, final int borderDown,
            final int borderLeft) {
        super(decorated);
        this.borderUp = borderUp;
        this.borderRight = borderRight;
        this.borderDown = borderDown;
        this.borderLeft = borderLeft;
        if (!isInBorders(decorated.getPosition())) {
            throw new IllegalArgumentException("Should not spawn outside the borders");
        }
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
        super.respawn(spawnPoint);
    }

    private boolean isInBorders(final Point position) {
        return position.getX() >= borderLeft && position.getX() < borderRight && position.getY() >= borderDown
                && position.getY() < borderUp;
    }

    /**
     * If pacman exceeds a border, it reappears at the opposite border.
     */
    @Override
    public void correctPosition() {
        this.setPosition(new Point(this.correctX(), this.correctY()));
    }

    private int correctX() {
        final int actualX = (int) this.getPosition().getX();
        if (actualX >= borderRight) {
            return actualX - borderRight + borderLeft;
        } else if (actualX < borderLeft) {
            return borderRight + (actualX - borderLeft);
        } else {
            return actualX;
        }
    }

    private int correctY() {
        final int actualY = (int) this.getPosition().getY();
        if (actualY >= borderUp) {
            return actualY - borderUp + borderDown;
        } else if (actualY < 0) {
            return borderUp + (actualY - borderDown);
        } else {
            return actualY;
        }
    }

}
