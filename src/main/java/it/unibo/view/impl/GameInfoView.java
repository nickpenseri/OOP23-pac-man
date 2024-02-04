package it.unibo.view.impl;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import it.unibo.model.api.GameObject;

/**
 * Swing Implementation of View Interface for life and score.
 */
public class GameInfoView extends ViewImpl {
    static final long serialVersionUID = 1L;
    private static final int DIMENSION = 20;
    private List<GameObject> pacmanInfo;
    private final transient Logger log = LoggerFactory.getLogger(ViewImpl.class);

    /**
     * Constructor of the ViewImpl.
     * 
     * @param width  the width of the view
     * @param height the height of the view
     */
    public GameInfoView(final int width, final int height) {
        super(width, height);
        pacmanInfo = new ArrayList<>(); 
    }

    /**
     * {@inheritDoc}
     */
    public final void updateView(final List<GameObject> pacmanInfo) {
        this.pacmanInfo = new ArrayList<>(Objects.requireNonNull(pacmanInfo));
      
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

            /* 
            for (int i = 0; i < this.pacmanInfo.get(0); i++) {
                g2.drawImage(imgLife, 0 + i * DIMENSION, DIMENSION, this);
            }
            g2.setColor(Color.WHITE);
            g2.drawString("Score: " + this.pacmanInfo.get(1), DIMENSION * this.pacmanInfo.get(0), DIMENSION);
            */
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

   
}
