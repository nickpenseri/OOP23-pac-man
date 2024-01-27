package it.unibo.core.impl;


import it.unibo.core.api.Engine;


/** Implementation of a game engine. */
public class EngineImpl implements Engine {
    //private Controller currentScene = new ControllerImpl(null, null);
    /**
     * {@inheritDoc}
     */
    @Override
    public void mainLoop() {
        processInput();
        updateGame();
        render();
    }


    private void processInput() { }
    private void updateGame() { }
    private void render() { }

}
