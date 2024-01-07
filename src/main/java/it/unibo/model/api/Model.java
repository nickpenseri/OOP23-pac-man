package it.unibo.model.api;
import java.util.List;

/** Is the container and executor of the GameObjects */
public interface Model {

    /** Get the list of objects in the model 
     * @return the list of objects in the model
    */
    public List<GameObject> getObjects();

    /** Get the command imput passed and do it 
     * @param command the command we need do
    */
    public void processInput(Command command);

    /** Update the state of the objects in the model */
    public void updateState();

    /** Initialize the game */
    public void initGame();
}
