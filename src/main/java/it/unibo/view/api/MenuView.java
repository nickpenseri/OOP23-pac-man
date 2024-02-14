package it.unibo.view.api;

import java.util.List;
import it.unibo.input.api.MenuCommand;

/**
 * the MenuView interface is used to obtain user commands and model object logic.
 */
public interface MenuView extends View{
    /**
     * Get the list of commands readed from the user.
     * 
     * @return the list of commands readed from the user
     */
    List<MenuCommand> getListCommands();
}
