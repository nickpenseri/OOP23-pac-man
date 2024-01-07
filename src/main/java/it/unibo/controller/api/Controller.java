package it.unibo.controller.api;


import it.unibo.model.api.Model;
import it.unibo.view.api.View;

/**
 * This interface models an entity controller in the architectural pattern MVC.
 * Each controller has a view that displays the scene to the user and takes input given from 
 * keybord and a model that is the logical domain of the application.
 * The controller coordinates the work of model and view and gives them instructions.
 */
public interface Controller {
    
    /**
     * Attachs a view to the controller
     * @param view the view to be attached
     */
    void setView(View view);

    /**
     * Attachs a model to the controller
     * @param model the model to be attached
     */
    void setModel(Model model);

    /**
     * Takes the commands from the view and gives them to the model 
     */
    void processInput();

    /**
     * Update the state of the model attached to the controller
     */
    void updateState();

    /**
     * Update the view attached to the controller and displays it
     */
    void updateView();
}
