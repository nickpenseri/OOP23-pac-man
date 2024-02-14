package it.unibo.core.api;

import it.unibo.controller.api.Controller;

/**
 * Interface that manages the scenes of the game.
 */
public interface SceneManager {

    /**
     * Get the controller of the actual scene..
     * @return the controller of the actual scene
     */
    Controller getController();


    /**
     * Check if the scene is changed.
     * @return true if the scene is changed, false otherwise
     */
    boolean sceneIsChanged();
}

