package it.unibo.view.api;

import java.util.List;

/** 
 * The View interface is used to obtain user commands and model object logic.
 * Once obtained, update the graphics and pass the command to the controller on request.
*/
public interface View {

    /**
     * Update the game graphics
     */
    public void updateView();

    /**
     * 
     * @return return a list commands user
     */
    public List<Command> getListCommands(); 
}
