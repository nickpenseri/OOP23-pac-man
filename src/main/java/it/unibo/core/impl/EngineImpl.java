package it.unibo.core.impl;

import java.awt.Dimension;
import java.awt.Toolkit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import it.unibo.core.api.Engine;
import it.unibo.core.api.Window;
import it.unibo.model.api.Model;
import it.unibo.model.impl.GameScene;
import it.unibo.view.impl.GameView;
import it.unibo.view.impl.ViewImpl;


/** Implementation of a game engine. */
public class EngineImpl implements Engine {

    private final Logger log = LoggerFactory.getLogger(EngineImpl.class);
    private static final long PERIOD = 20; /* 20 ms = 50 frame for second */
    private final ViewImpl view;
    private final Model gameScene;
    private final Window window;
    private static final int  PROPORTION = 2;

    /** Constructor of the Engine, here is created the window of the game. */
    public EngineImpl() {
        final Toolkit toolkit = Toolkit.getDefaultToolkit();
        final Dimension screenSize = toolkit.getScreenSize();
        final int width = screenSize.width / PROPORTION;
        final int height = screenSize.height / PROPORTION; 
        this.view = new GameView(width, height);
        this.gameScene = new GameScene();
        this.window = new WindowImpl(view, "Pacman", width, height);
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
    private void processInput() { }
    private void updateGame(final int elapsed) {
        gameScene.updateState(elapsed);
    }
    private void render() { 
        view.updateView(gameScene.getObjects());
        window.render();
    }

}
