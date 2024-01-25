package it.unibo.core.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import it.unibo.controller.api.Controller;
import it.unibo.core.api.Engine;


/** Implementation of a game engine. */
public class EngineImpl implements Engine {

    private final Logger log = LoggerFactory.getLogger(EngineImpl.class);
    private static final long PERIOD = 20; /* 20 ms = 50 frame for second */
    private Controller currentScene;

    
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
        currentScene.updateState();
    }
    private void render() { }

}
