package it.unibo.core.api;

import it.unibo.controller.api.Controller;

public interface SceneManager {

    /**
     * Get the controller of the game.
     * 
     * @return the controller of the game
     */
    Controller getController();


    boolean sceneIsChanged();
}

