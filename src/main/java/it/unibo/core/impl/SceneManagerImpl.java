package it.unibo.core.impl;

import java.util.Objects;

import it.unibo.controller.api.Controller;
import it.unibo.controller.impl.ControllerImpl;
import it.unibo.core.api.Window;
import it.unibo.model.api.Model;
import it.unibo.model.impl.GameScene;
import it.unibo.view.api.GameView;
import it.unibo.view.api.View;
import it.unibo.view.impl.GamePanel;

public class SceneManagerImpl {
    
    private final Window window;
    private final Controller controller;

    public SceneManagerImpl(final Window window) {
        this.window = Objects.requireNonNull(window);
        final View gameView = new GamePanel();
        window.setPanelScene(gameView);

        final var gamedim = this.window.getGamePanelDimension();
        final Model gameScene = new GameScene((int) gamedim.getWidth(), (int) gamedim.getHeight());
        this.controller = new ControllerImpl(gameScene, (GameView) gameView);
    }



    public Controller getController() {
        return this.controller;
    }
}
