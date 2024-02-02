package it.unibo.core.impl;

import java.awt.Dimension;
import java.awt.Toolkit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import it.unibo.controller.api.Controller;
import it.unibo.controller.impl.ControllerImplGame;
import it.unibo.core.api.Engine;
import it.unibo.core.api.Window;
import it.unibo.model.api.Model;
import it.unibo.model.impl.GameScene;
import it.unibo.view.api.View;
import it.unibo.view.impl.GameView;
import it.unibo.view.impl.ViewImplInfo;

/** Implementation of a game engine. */
public class EngineImpl implements Engine {

    private final Logger log = LoggerFactory.getLogger(EngineImpl.class);
    private static final long PERIOD = 20; /* 20 ms = 50 frame for second */
    private final Window window;
    private final Controller controller;
    private static final int PROPORTION = 2;

    /** Constructor of the Engine, here is created the window of the game. */
    public EngineImpl() {
        final Toolkit toolkit = Toolkit.getDefaultToolkit();
        final Dimension screenSize = toolkit.getScreenSize();
        final int height = screenSize.height / PROPORTION;
        final int width = screenSize.width / PROPORTION;

        final int infoWidth = width / 10;
        final int infoHeight = height / 10;
        final ViewImplInfo gameViewInfo = new ViewImplInfo(infoWidth, infoHeight);
        final View gameView = new GameView(width - infoWidth, height - infoWidth);
        final Model gameScene = new GameScene(width - infoWidth, height - infoWidth);
        this.controller = new ControllerImplGame(gameScene, gameView, gameViewInfo);
        this.window = new WindowImpl(gameView, gameViewInfo, "Pacman", width, height);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void mainLoop() {
        long lastTime = System.currentTimeMillis();
        while (true) {
            final long current = System.currentTimeMillis();
            final int elapsed = (int) (current - lastTime);
            processInput();
            updateGame(elapsed);
            render();
            waitForNextFrame(current);
            lastTime = current;
        }
    }

    private void waitForNextFrame(final long current) {
        final long dt = System.currentTimeMillis() - current;
        if (dt < PERIOD) {
            try {
                Thread.sleep(PERIOD - dt);
            } catch (InterruptedException ex) {
                log.error("Error in sleep", ex);
            }
        }
    }

    private void processInput() {
        controller.processInput();
    }

    private void updateGame(final int elapsed) {
        controller.updateState(elapsed);
    }

    private void render() {
        controller.updateView();
        window.render();
    }

}
