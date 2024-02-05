package it.unibo.model.ghost.api;

import it.unibo.model.api.ImageChooser;

public interface GhostGraphics extends ImageChooser{
    
    /**
     * Sets the right png for the ghost.
     * @param state the state to set
     */
    void setState(GhostState state);
}
