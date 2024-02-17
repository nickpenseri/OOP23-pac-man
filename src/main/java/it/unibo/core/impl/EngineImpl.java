package it.unibo.core.impl;

import java.awt.Dimension;
import java.awt.Toolkit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import it.unibo.controller.api.Controller;
import it.unibo.core.api.Engine;
import it.unibo.core.api.SceneManager;
import it.unibo.core.api.Window;


/** Implementation of a game engine. */
public class EngineImpl implements Engine {

    private final Logger log = LoggerFactory.getLogger(EngineImpl.class);
    private static final long PERIOD = 20; /* 20 ms = 50 frame for second */
    private final Window window;
    private Controller controller;
    private final SceneManager sceneManager;
    private static final int PROPORTION = 2;

    /** Constructor of the Engine, here is created the window of the game. */
    public EngineImpl() {
        final Toolkit toolkit = Toolkit.getDefaultToolkit();
        final Dimension screenSize = toolkit.getScreenSize();
        final int height = screenSize.width / PROPORTION;
        final int width = screenSize.width / PROPORTION;
        this.window = new WindowImpl("Pacman", width, height);
        sceneManager = new SceneManagerImpl(window);
        this.controller = sceneManager.getController();
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
        if (sceneManager.isSceneChanged()) {
            this.controller = sceneManager.getController();
        }
    }

    private void render() {
        controller.updateView();
        window.render();
    }

}
