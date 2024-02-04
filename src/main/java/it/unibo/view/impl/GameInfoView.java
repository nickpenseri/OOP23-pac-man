package it.unibo.view.impl;


import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.util.List;


import it.unibo.model.api.GameObject;

/**
 * Swing Implementation of View Interface for life and score.
 */
public class GameInfoView extends ViewImpl {
    static final long serialVersionUID = 1L;

    /**
     * Constructor of the ViewImpl.
     * 
     */
    public GameInfoView() {
        this.setOpaque(true);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void updateView(final List<GameObject> pacmanInfo) {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void paint(final Graphics g) {
        if (g instanceof Graphics2D) {
            final Graphics2D g2 = (Graphics2D) g;
            SetupGraphics2D.setupGraphics2DStatic(g2, this.getWidth(), this.getHeight());
            g2.dispose();
        }
    }

    @Override
    public void keyTyped(final KeyEvent e) {
        
    }

    @Override
    public void keyPressed(final KeyEvent e) {
    
    }

    @Override
    public void keyReleased(final KeyEvent e) {
    }
}
