package it.unibo.view.impl;

import java.awt.event.KeyEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import it.unibo.input.api.Command;

/** View of the actual Game. */
public class GameView extends ViewImpl {

     private final Logger log = LoggerFactory.getLogger(GameView.class);
    private static final long serialVersionUID = 1L;
    /**
     * Constructor of the GameView.
     * @param width of the view
     * @param height of the view
     */
    public GameView(final int width, final int height) {
        super(width, height);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void keyTyped(final KeyEvent e) {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void keyPressed(final KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            log.info("UP");
            addCommand(Command.SET_DIR_UP);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void keyReleased(final KeyEvent e) {
    }

}
