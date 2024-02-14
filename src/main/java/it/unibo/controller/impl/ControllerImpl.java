package it.unibo.controller.impl;

import java.util.Objects;
import it.unibo.controller.api.Controller;
import it.unibo.core.api.SceneMediator;
import it.unibo.model.api.Model;
import it.unibo.view.api.GameView;

/** The class is the implementation of the Controller Interface. */
public class ControllerImpl implements Controller {

    private final Model model;
    private final GameView view;
    private final SceneMediator sceneMediator;

    /**
     * When Controller is created, it needs a model and a view to manage.
     * 
     * @param model the model of the gameScene
     * @param view  the view of the gameScene
     */
    public ControllerImpl(SceneMediator sceneMediator,final Model model, final GameView view) {
        this.sceneMediator = Objects.requireNonNull(sceneMediator);    
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
        if (model.isSceneOver()){
            sceneMediator.sceneFinished();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateView() {
        view.updateView(model.getObjects());
    }

}
