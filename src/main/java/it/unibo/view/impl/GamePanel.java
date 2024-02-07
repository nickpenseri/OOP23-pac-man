package it.unibo.view.impl;

import java.awt.event.KeyEvent;

import it.unibo.input.api.Command;

/**
 * Swing Implementation of GameView Interface.
 */
public class GamePanel extends GameViewImpl {
    private static final long serialVersionUID = 1L;

    @Override
    public void keyTyped(final KeyEvent e) {
    }

    /**
     * KeyPressed interpreter.
     */
    @Override
    public void keyPressed(final KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            addCommand(Command.SET_DIR_UP);
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            addCommand(Command.SET_DIR_DOWN);
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            addCommand(Command.SET_DIR_LEFT);
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            addCommand(Command.SET_DIR_RIGHT);
        }
    }

    @Override
    public void keyReleased(final KeyEvent e) {
    }

}
