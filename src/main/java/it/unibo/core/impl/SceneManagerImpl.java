package it.unibo.core.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import it.unibo.controller.api.Controller;
import it.unibo.controller.impl.ControllerImpl;
import it.unibo.core.api.SceneManager;
import it.unibo.core.api.SceneMediator;
import it.unibo.core.api.Window;
import it.unibo.model.api.Model;
import it.unibo.model.impl.GameScene;
import it.unibo.view.api.GameView;
import it.unibo.view.api.View;
import it.unibo.view.impl.GamePanel;

public class SceneManagerImpl implements SceneManager, SceneMediator {
    
    private final Window window;
    private Controller controller;

    private final List<Controller> scene;
    
    private int actualSceneIndex;
    private boolean sceneChanged;

    public SceneManagerImpl(final Window window) {
        this.window = Objects.requireNonNull(window);
        final View gameView = new GamePanel();
        
        this.scene = new ArrayList<>();
        this.actualSceneIndex = 0;

        window.setPanelScene(gameView);
        var gamedim = this.window.getGamePanelDimension();
        Model gameScene = new GameScene((int) gamedim.getWidth(), (int) gamedim.getHeight());
        var controller = new ControllerImpl(this, gameScene, (GameView) gameView);
        this.scene.add(controller);

        gamedim = this.window.getGamePanelDimension();
        gameScene = new GameScene((int) gamedim.getWidth(), (int) gamedim.getHeight());
        controller = new ControllerImpl(this, gameScene, (GameView) gameView);
        this.scene.add(controller);

        this.controller = this.scene.get(this.actualSceneIndex);
    }



    public Controller getController() {
        return this.controller;
    }



    @Override
    public void sceneFinished() {
        this.controller = this.scene.get(++this.actualSceneIndex);
        this.sceneChanged = true;

    }



    @Override
    public boolean sceneIsChanged() {
        if (this.sceneChanged) {
            this.sceneChanged = false;
            return true;
        } else {
            return false;
        }
    }

}
