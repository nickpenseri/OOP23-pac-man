package it.unibo.model.api;

import java.awt.Point;
import java.util.Optional;

/** A GameObject that can be moved. */
public interface Character extends GameObject {

    /**
     * Change the direction of the movement of the Character.
     * @param direction The new direction of the Character.
     */
    void setDirection(Direction direction);

    /**
     * Sets the position to a specified point.
     * @param position the new position
     */
    void setPosition(Point position);

    /**
     * Getter for the direction.
     * @return the actual direction
     */
    Optional<Direction> getDirection();

    /**
     * Resets the direction of the character, making it stop.
     */
    void resetDirection();


    /**
     * update the state of the Character.
     * @param elapsed the time passed from the last update.
     */
    void updateState(long elapsed);

    /**
     * Getter for the speed level.
     * @return the actual speed level of the character
     */
    int getSpeedLevel();

    /**
     * Increase the speed of the Character, unless it is already at maximum.
     * @return true if the speed is changed 
     */
    boolean increaseSpeed();

    /**
     * Decrease the speed of the Character, unless it is already at minimum.
     * @return true if the speed is changed 
     */
    boolean decreaseSpeed();

}
