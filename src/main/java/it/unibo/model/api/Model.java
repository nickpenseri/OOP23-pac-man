package it.unibo.model.api;

import java.util.List;
import it.unibo.input.api.Command;

/** Is the container and executor of the GameObjects. */
public interface Model {

    /** Get the list of objects in the model. 
     * @return the list of objects in the model
    */
    List<GameObject> getObjects();

    /** Get the list of command imput passed and do it. 
     * @param commands the command we need do
    */
    void processInput(List<Command> commands);

    /** Update the state of the objects in the model. */
    void updateState();

    /** Initialize the game. */
    void initGame();
}
