package it.unibo.view.impl;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import it.unibo.view.api.InfoView;

/**
 * Swing Implementation of View Interface for life and score.
 */
public class GameInfoView extends ViewImpl implements InfoView, Cloneable {
    static final long serialVersionUID = 1L;
    private int singleDimension;
    private final List<Integer> pacmanInfo = new ArrayList<>();
    private final transient Logger log = LoggerFactory.getLogger(ViewImpl.class);

    /**
     * Constructor of the GameInfoView.
     */
    public GameInfoView() {
        this.setOpaque(true);
    }

    /**
     * Set the dimension of the image.
     * @param dim the dimension of image.
     */
    public void setImageDim(final Dimension dim) {
        final Dimension dimension = new Dimension(dim);
        this.singleDimension = (int) dimension.getHeight() / 2;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void updateView(final List<Integer> info) {
        this.pacmanInfo.clear();
        this.pacmanInfo.addAll(info);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void paint(final Graphics g) {
        if (g instanceof Graphics2D && !this.pacmanInfo.isEmpty() && singleDimension != 0) {
            final Graphics2D g2 = (Graphics2D) g;

            SetupGraphics2D.setupGraphics2DStatic(g2, this.getWidth(), this.getHeight());

            final var url = GameInfoView.class.getResource("/image/life/Life.png");
            Image imgLife = null;
            try {
                imgLife = ImageIO.read(url).getScaledInstance(singleDimension, singleDimension,
                        Image.SCALE_DEFAULT);
            } catch (IOException e) {
                log.error("error during image reading" + e.getMessage());
            }
            final int lifenum = this.pacmanInfo.get(0);
            for (int i = 0; i < lifenum; i++) {
                g2.drawImage(imgLife, 0 + i * singleDimension, singleDimension / 2, this);
            }
            g2.setColor(Color.WHITE);
            g2.drawString("Score: " + this.pacmanInfo.get(1), singleDimension * lifenum,
                    singleDimension);
        }
    }

    /**
     * Clonation metod for the GameInfoView.
     */
    @Override
    public GameInfoView clone() {
        try {
            return (GameInfoView) super.clone();
        } catch (CloneNotSupportedException e) {
            log.error("error during cloning" + e.getMessage());
            return null;
        }
    }
}
