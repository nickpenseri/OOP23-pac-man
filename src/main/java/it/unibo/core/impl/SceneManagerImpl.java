package it.unibo.core.impl;

import java.awt.Dimension;
import java.util.Objects;
import java.util.Optional;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.controller.api.Controller;
import it.unibo.controller.impl.GameController;
import it.unibo.controller.impl.MenuController;
import it.unibo.core.api.SceneManager;
import it.unibo.core.api.SceneMediator;
import it.unibo.core.api.Window;
import it.unibo.model.api.GameModel;
import it.unibo.model.impl.GameScene;
import it.unibo.view.api.GameView;
import it.unibo.view.api.MenuView;
import it.unibo.view.impl.EndGameViewImpl;
import it.unibo.view.impl.GamePanel;
import it.unibo.view.impl.MenuViewImpl;

/**
 * The class is the implementation of the SceneManager Interface.
 */
public class SceneManagerImpl implements SceneManager, SceneMediator {
    private final Window window;
    private Controller controller;
    private int actualSceneIndex;
    private boolean sceneChanged;
    private int score;

    /**
     * Constructor of the SceneManager.
     * 
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
    public void sceneFinished(final Optional<Integer> score) {
        score.ifPresent(s -> this.score = s);
        this.sceneChanged = true;
        this.controller = selectScene();
    }

    /**
     * @{inheritDoc}
     */
    @Override
    public boolean isSceneChanged() {
        if (this.sceneChanged) {
            this.sceneChanged = false;
            return true;
        } else {
            return false;
        }
    }

    private Controller selectScene() {
        final GameView gameView;
        final GameModel gameScene;
        final Dimension gamedim;
        switch (actualSceneIndex) {

            case 0:
                this.actualSceneIndex = 1;
                final MenuView menuView = new MenuViewImpl();
                window.setScenePanel(menuView);
                return new MenuController(this, menuView);
            case 1:
                this.actualSceneIndex = 2;
                gameView = new GamePanel();
                window.setScenePanel(gameView);
                gamedim = this.window.getActualPanelDimension();
                gameScene = new GameScene((int) gamedim.getWidth(), (int) gamedim.getHeight());
                return new GameController(this, gameScene, gameView);
            case 2:
                this.actualSceneIndex = 1;
                final MenuView endGaMenuView = new EndGameViewImpl(this.score);
                window.setScenePanel(endGaMenuView);
                return new MenuController(this, endGaMenuView);
            default:
                return null;
        }
    }

}
