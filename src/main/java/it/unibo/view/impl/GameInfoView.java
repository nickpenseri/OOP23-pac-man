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

import javax.imageio.ImageIO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import it.unibo.model.api.GameObject;
import it.unibo.model.pacman.api.Life;
import it.unibo.model.pacman.api.Points;

/**
 * Swing Implementation of View Interface for life and score.
 */
public class GameInfoView extends ViewImpl {
    static final long serialVersionUID = 1L;
    private final transient Logger log = LoggerFactory.getLogger(ViewImpl.class);
    private static final int DIMENSION = 30;
    private final List<GameObject> pacmanInfo;
    private int numLife = 0;
    private int points = 0;

    /**
     * Constructor of the ViewImpl.
     * 
     */
    public GameInfoView() {
        this.setOpaque(true);
        this.pacmanInfo = new ArrayList<>();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void updateView(final List<GameObject> pacmanInfo) {
        this.pacmanInfo.clear();
        this.pacmanInfo.addAll(pacmanInfo);
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
            var pacmanInfo0 = this.pacmanInfo.get(0);
            if (pacmanInfo0 instanceof Life) {
                numLife = ((Life) pacmanInfo0).getNumLife();
                for (int i = 0; i < numLife; i++) {
                    g2.drawImage(imgLife, 0 + i * (int) pacmanInfo0.getDimension().getHeight(),
                            (int) pacmanInfo0.getDimension().getWidth(), this);
                }
            } else if (pacmanInfo0 instanceof Points) {
                points = ((Points) pacmanInfo0).getPoints();
                g2.setColor(Color.WHITE);
                g2.drawString("Score: " + points, (int) pacmanInfo0.getDimension().getHeight() * numLife,
                        (int) pacmanInfo0.getDimension().getWidth());
            }

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
