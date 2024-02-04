package it.unibo.view.api;

import java.awt.Dimension;
import java.util.List;

import it.unibo.input.api.Command;
import it.unibo.model.api.GameObject;

/** 
 * The View interface is used to obtain user commands and model object logic.
 * Once obtained, update the graphics and pass the command to the controller on request.
*/
public interface View {

    /** Update the game graphics. 
    * @param gameObjects the list of objects in the model
    */
     void updateView(List<GameObject> gameObjects);

    /**
     * 
     * @return return a list commands user.
     */
     List<Command> getListCommands(); 


     /** Get the dimension of the view.
      * @return the dimension of the view
      */
     Dimension getDimension();
}
