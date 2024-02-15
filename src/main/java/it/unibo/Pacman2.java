package it.unibo;

import it.unibo.core.api.Engine;
import it.unibo.core.impl.EngineImpl;


/** Main Class for start the game. */
public final class Pacman2 {

    private Pacman2() { }

    /**
     * Main method for start the game.
     * @param args the arguments
     */
    public static void main(final String[] args) {
        final Engine engine = new EngineImpl();
        engine.mainLoop();

        System.exit(0);
    }
}
