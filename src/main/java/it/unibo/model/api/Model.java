package it.unibo.model.api;

import java.util.List;
import java.util.Optional;

import it.unibo.core.api.SoundEvent;
import it.unibo.input.api.Command;

/** Is the container and executor of the GameObjects. */
public interface Model {

    /**
     * Get the list of objects in the model.
     * 
     * @return the list of objects in the model
     */
    List<GameObject> getObjects();

    /**
     * Get the list of command imput passed and do it.
     * 
     * @param commands the command we need do
     */
    void processInput(List<Command> commands);

    /**
     * Update the state of the objects in the model.
     * 
     * @param elapsed is the time elapsed from the last update.
     */
    void updateState(long elapsed);

    /**
     * Check if the game is over.
     * 
     * @return true if the scene is over
     */
    boolean isSceneOver();

    /**
     * Return the PacMan lifes.
     * 
     * @return the PacMan lifes
     */
    int getPacManLifes();

    /**
     * Return the PacMan scores.
     * 
     * @return the PacMan scores
     */
    int getPacManScores();

    /**
     * Return the effect text.
     * 
     * @return the effect text
     */
    Optional<String> getEffectText();

    List<SoundEvent> getEvents();
}
