package it.unibo.core.impl;

import java.awt.Dimension;
import java.util.Objects;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
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
import it.unibo.view.impl.MenuView;

/**
 * The class is the implementation of the SceneManager Interface.
 */
public class SceneManagerImpl implements SceneManager, SceneMediator {
    private final Window window;
    private Controller controller;
    private int actualSceneIndex;
    private boolean sceneChanged;

    /**
     * Constructor of the SceneManager.
     * @param window the window of the game
     */
     @SuppressFBWarnings(value = {
            "EI_EXPOSE_REP2"
    }, justification = "I need the window reference because i have to change its panel")
    public SceneManagerImpl(final Window window) {
        this.window = Objects.requireNonNull(window);
        this.actualSceneIndex = 0;
        this.controller = selectScene();
    }


    /**
     * @{inheritDoc}
     */
    @Override
    public Controller getController() {
        return this.controller;
    }


    /**
     * @{inheritDoc}
     */
    @Override
    public void sceneFinished() {
        this.sceneChanged = true;
        this.controller = selectScene();


    }

    /**
     * @{inheritDoc}
     */
    @Override
    public boolean sceneIsChanged() {
        if (this.sceneChanged) {
            this.sceneChanged = false;
            return true;
        } else {
            return false;
        }
    }

    private Controller selectScene() {
        final GameView gameView;
        final Model gameScene;
        final Dimension gamedim;
        switch (actualSceneIndex) {

            case 0:
                this.actualSceneIndex = 1;
                gameView = new GamePanel();
                var menuView = new MenuView();
                window.setPanelScene(gameView);
                gamedim = this.window.getGamePanelDimension();
                gameScene = new GameScene((int) gamedim.getWidth(), (int) gamedim.getHeight());
                return new ControllerImpl(this, gameScene, gameView);

            case 1:
                this.actualSceneIndex = 2;
                gameView = new GamePanel();
                window.setPanelScene(gameView);
                gamedim = this.window.getGamePanelDimension();
                gameScene = new GameScene((int) gamedim.getWidth(), (int) gamedim.getHeight());
                return new ControllerImpl(this, gameScene, gameView);

            case 2:
                this.actualSceneIndex = 0;
                gameView = new GamePanel();
                window.setPanelScene(gameView);
                gamedim = this.window.getGamePanelDimension();
                gameScene = new GameScene((int) gamedim.getWidth(), (int) gamedim.getHeight());
                return new ControllerImpl(this, gameScene, gameView);

            default:
                return null;
        }
    }

}
