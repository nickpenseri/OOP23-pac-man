package it.unibo.controller.impl;

import java.util.Objects;

import it.unibo.controller.api.Controller;
import it.unibo.core.api.SceneMediator;
import it.unibo.view.api.MenuView;

public class ControllerMenu implements Controller{

    private final MenuView view;
    private final SceneMediator sceneMediator;

    /**
     * When Controller is created, it needs a model and a view to manage.
     * @param sceneMediator the sceneMediator that manages the scenes off the game
     * @param model the model of the gameScene
     * @param view  the view of the gameScene
     */
    public ControllerMenu(final SceneMediator sceneMediator, final MenuView view) {
        this.sceneMediator = Objects.requireNonNull(sceneMediator);
        this.view = Objects.requireNonNull(view);
    }


    @Override
    public void processInput() {
        view.getListCommands().forEach(c -> {
            switch (c) {
                case START:
                    sceneMediator.sceneFinished();
                    break;
                case QUIT:
                    System.exit(0);
                    break;
                default:
                    break;
            }
        });
    }

    @Override
    public void updateState(long elapsed) {
        
    }

    @Override
    public void updateView() {
    
    }
    
}
