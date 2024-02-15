package it.unibo.controller.impl;

import java.util.Objects;
import java.util.Optional;

import it.unibo.controller.api.Controller;
import it.unibo.core.api.SceneMediator;
import it.unibo.view.api.MenuView;

/**
 * ControllerMenu is the controller of the MenuScene.
 * It manages the input from the view and talk with the SceneMediator to change
 * the scene.
 */
public class ControllerMenu implements Controller {

    private final MenuView view;
    private final SceneMediator sceneMediator;

    /**
     * When Controller is created, it needs a model and a view to manage.
     * 
     * @param sceneMediator the sceneMediator that manages the scenes off the game
     * @param view          the view of the gameScene
     */
    public ControllerMenu(final SceneMediator sceneMediator, final MenuView view) {
        this.sceneMediator = Objects.requireNonNull(sceneMediator);
        this.view = Objects.requireNonNull(view);
    }

    /**
     * @{inheritDoc}
     */
    @Override
    public void processInput() {
        view.getListCommands().forEach(c -> {
            switch (c) {
                case START:
                    sceneMediator.sceneFinished(Optional.empty());
                    break;
                default:
                    break;
            }
        });
    }

    @Override
    public void updateState(final long elapsed) {

    }

    @Override
    public void updateView() {

    }

}
