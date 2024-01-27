package it.unibo.controller.impl;

import java.util.Objects;
import it.unibo.controller.api.Controller;
import it.unibo.model.api.Model;
import it.unibo.view.api.View;

/** The class is the implementation of the Controller Interface. */
public class ControllerImpl implements Controller {

    private final  Model model;
    private final View view;

    /**
     * When Controller is created, it needs a model and a view to manage.
     * @param model the model of the gameScene
     * @param view the view of the gameScene
     */
    public ControllerImpl(final Model model, final View view) {
        this.model = Objects.requireNonNull(model);
        this.view = Objects.requireNonNull(view);
    }
     /**
     * {@inheritDoc}
     */
    @Override
    public void processInput() {
        model.processInput(view.getListCommands());
    }

     /**
     * {@inheritDoc}
     */
    @Override
    public void updateState(final long elapsed) {
       model.updateState(elapsed);
    }

     /**
     * {@inheritDoc}
     */
    @Override
    public void updateView() {
        view.updateView(model.getObjects());
    }

}
