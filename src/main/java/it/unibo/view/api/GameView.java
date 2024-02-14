package it.unibo.view.api;

import java.util.List;

import it.unibo.input.api.Command;
import it.unibo.model.api.GameObject;

/**
 * The GameView interface is used to obtain user commands and model object logic.
 * Once obtained, update the graphics and pass the command to the controller on
 * request.
 */
public interface GameView extends View{
    /**
     * Update the view with the new gameObjects.
     * 
     * @param gameObjects the new gameObjects
     */
    void updateView(List<GameObject> gameObjects);

    /**
     * Get the list of commands readed from the user.
     * 
     * @return the list of commands readed from the user
     */
    List<Command> getListCommands();
}
