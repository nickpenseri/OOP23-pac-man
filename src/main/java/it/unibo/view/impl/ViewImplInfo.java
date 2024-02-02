package it.unibo.view.impl;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Swing Implementation of View Interface for life and score.
 */
public class ViewImplInfo extends JPanel implements Cloneable {
    static final long serialVersionUID = 1L;
    private static final int DIMENSION = 20;
    private final List<Integer> pacmanInfo = new ArrayList<>();
    private final transient Logger log = LoggerFactory.getLogger(ViewImpl.class);

    /**
     * Constructor of the ViewImpl.
     * 
     * @param width  the width of the view
     * @param height the height of the view
     */
    public ViewImplInfo(final int width, final int height) {

        if (width <= 0 || height <= 0) {
            throw new IllegalArgumentException("Width and Height must be positive");
        }

        setSize(width, height);
    }

    /**
     * {@inheritDoc}
     */
    public final void updateView(final List<Integer> pacmanInfo2) {
        this.pacmanInfo.clear();
        this.pacmanInfo.addAll(pacmanInfo2);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void paint(final Graphics g) {
        if (g instanceof Graphics2D) {
            final Graphics2D g2 = (Graphics2D) g;

            SetupGraphics2D.setupGraphics2DStatic(g2, this.getWidth(), this.getHeight());

            final var url = ClassLoader.getSystemResource("image/life/Life.png").getPath();
            Image imgLife = null;
            try {
                imgLife = ImageIO.read(new File(url)).getScaledInstance(DIMENSION, DIMENSION, Image.SCALE_DEFAULT);
            } catch (IOException e) {
                log.error("error during image reading" + e.getMessage());
            }

            for (int i = 0; i < this.pacmanInfo.get(0); i++) {
                g2.drawImage(imgLife, 0 + i * DIMENSION, DIMENSION, this);
            }
            g2.setColor(Color.WHITE);
            g2.drawString("Score: " + this.pacmanInfo.get(1), DIMENSION * this.pacmanInfo.get(0), DIMENSION);
        }
    }

    /**
     * Clonation metod for the ViewImplInfo.
     */
    @Override
    public ViewImplInfo clone() {
        try {
            return (ViewImplInfo) super.clone();
        } catch (CloneNotSupportedException e) {
            log.error("error during cloning" + e.getMessage());
            return null;
        }
    }
}
