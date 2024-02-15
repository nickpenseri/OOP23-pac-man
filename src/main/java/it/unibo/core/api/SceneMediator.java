package it.unibo.core.api;

import java.util.Optional;
/**
 * Interface for the controllers.
 */
public interface SceneMediator {
    /**
     * Method is called when the scene is finished.
     * 
     * @param score the score of the game if present
     */
    void sceneFinished(Optional<Integer> score);
}
