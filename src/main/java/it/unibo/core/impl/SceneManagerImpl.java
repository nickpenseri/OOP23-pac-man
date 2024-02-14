package it.unibo.core.impl;

import java.awt.Dimension;
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


    private int actualSceneIndex;
    private boolean sceneChanged;

    public SceneManagerImpl(final Window window) {
        this.window = Objects.requireNonNull(window);
        this.actualSceneIndex = 0;
        this.controller = selectScene();
    }



    public Controller getController() {
        return this.controller;
    }



    @Override
    public void sceneFinished() {
        this.sceneChanged = true;
        this.controller = selectScene();


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


    private Controller selectScene(){
        final View gameView;
        final Model gameScene;
        final Dimension gamedim;
        switch (actualSceneIndex) {

            case 0:
                this.actualSceneIndex = 1;
                gameView = new GamePanel();
                window.setPanelScene(gameView);
                gamedim = this.window.getGamePanelDimension();
                gameScene = new GameScene((int) gamedim.getWidth(), (int) gamedim.getHeight());
                return new ControllerImpl(this, gameScene, (GameView) gameView);

            case 1:
                this.actualSceneIndex = 2;
                gameView = new GamePanel();
                window.setPanelScene(gameView);
                gamedim = this.window.getGamePanelDimension();
                gameScene = new GameScene((int) gamedim.getWidth(), (int) gamedim.getHeight());
                return new ControllerImpl(this, gameScene, (GameView) gameView);
               
        
            case 2:
                this.actualSceneIndex = 0;
                gameView = new GamePanel();
                window.setPanelScene(gameView);
                gamedim = this.window.getGamePanelDimension();
                gameScene = new GameScene((int) gamedim.getWidth(), (int) gamedim.getHeight());
                return new ControllerImpl(this, gameScene, (GameView) gameView);

            default:
                return null;
        }
    }

}
