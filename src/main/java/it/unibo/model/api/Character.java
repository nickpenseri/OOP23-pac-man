package it.unibo.model.api;


/** A GameObject that can be moved. */
public interface Character extends GameObject {

    /**
     * Change the direction of the movement of the Character.
     * @param direction The new direction of the Character.
     */
    void setDirection(Direction direction);

    /**
     * update the state of the Character.
     * @param elapsed the time passed from the last update.
     */
    void updateState(long elapsed);

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
