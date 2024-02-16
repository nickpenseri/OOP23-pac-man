package it.unibo.controller.impl;

import java.util.Objects;
import java.util.Optional;

import it.unibo.controller.api.Controller;
import it.unibo.core.api.SceneMediator;
import it.unibo.model.api.GameModel;
import it.unibo.view.api.GameView;

/** The class is the implementation of the Controller Interface. */
public class GameController implements Controller {

    private final GameModel model;
    private final GameView view;
    private final SceneMediator sceneMediator;

    /**
     * When Controller is created, it needs a model and a view to manage.
     * @param sceneMediator the sceneMediator that manages the scenes off the game
     * @param model the model of the gameScene
     * @param view  the view of the gameScene
     */
    public GameController(final SceneMediator sceneMediator, final GameModel model, final GameView view) {
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
        if (model.isSceneOver()) {
            sceneMediator.sceneFinished(Optional.of(model.getGameScore()));
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateView() {
        view.updateView(model.getObjects());
        view.playSounds(model.getSoundEvents());
    }

}
