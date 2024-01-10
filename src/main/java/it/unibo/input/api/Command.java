package it.unibo.input.api;
/**
 * This enum is used to manage commands received from the view and passed to the model
 * by the controller.
 */
public enum Command {
    /**
     * Command to change Pac-Man direction to up.
     */
    SET_DIR_UP,
    /**
     * Command to change Pac-Man direction to right.
     */
    SET_DIR_RIGHT,
    /**
     * Command to change Pac-Man direction to down.
     */
    SET_DIR_DOWN,
    /**
     * Command to change Pac-Man direction to left.
     */
    SET_DIR_LEFT;
}
