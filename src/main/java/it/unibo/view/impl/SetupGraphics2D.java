package it.unibo.view.impl;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

/**
 * Class to setup the Graphics2D.
 */
public final class SetupGraphics2D {

    /**
     * Private constructor.
     */
    private SetupGraphics2D() {
    }

    /**
     * Method to setup the Graphics2D.
     * @param g the Graphics2D
     * @param width the width
     * @param height the height
     */
    public static void setupGraphics2DStatic(final Graphics2D g, final int width, final int height) {

        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        g.setRenderingHint(RenderingHints.KEY_RENDERING,
                RenderingHints.VALUE_RENDER_QUALITY);
        g.clearRect(0, 0, width, height);
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, width, height);

    }
}
