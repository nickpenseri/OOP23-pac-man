package it.unibo.model.api;

import java.util.List;
import it.unibo.input.api.Command;

/** Is the container and executor of the GameObjects */
public interface Model {

    /** Get the list of objects in the model 
     * @return the list of objects in the model
    */
    public List<GameObject> getObjects();

    /** Get the list of command imput passed and do it 
     * @param commands the command we need do
    */
    public void processInput(List<Command> commands);

    /** Update the state of the objects in the model */
    public void updateState();

    /** Initialize the game */
    public void initGame();
}
