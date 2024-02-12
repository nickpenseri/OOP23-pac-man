package it.unibo.view.api;

import java.awt.Dimension;

/**
 * The View interface is used to obtain user commands and model object logic.
 * Once obtained, update the graphics and pass the command to the controller on
 * request.
 */
public interface View {

    /**
     * Get the dimension of the view.
     * 
     * @return the dimension of the view
     */
    Dimension getDimension();
}
