package it.unibo.controller.impl;

import java.util.Objects;

import it.unibo.controller.api.Controller;
import it.unibo.model.api.Model;
import it.unibo.view.api.GameView;

/** The class is the implementation of the Controller Interface. */
public class ControllerImplGame implements Controller {

    private final Model model;
    private final GameView gameView;
    /**
     * When Controller is created, it needs a model and a view to manage.
     * 
     * @param model        the model of the gameScene
     * @param gameView     the view of the gameScene
     */
    public ControllerImplGame(final Model model, final GameView gameView) {
        this.model = Objects.requireNonNull(model);
        this.gameView = Objects.requireNonNull(gameView);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void processInput() {
        model.processInput(gameView.getListCommands());
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
        gameView.updateView(model.getObjects());
    }

}
