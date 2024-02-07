package it.unibo.model.pacman.impl;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.geom.Dimension2D;
import java.net.URL;

import it.unibo.model.pacman.api.Life;

/**
 * This class models the life of the game.
 */
public class LifeImpl implements Life {
    private int numLife;
    private final Dimension dimension;
    static final URL IMAGE_URL = ClassLoader.getSystemResource("image/life/Life.png");

    /**
     * Constructor of the LifeImpl.
     * 
     * @param numLife   the number of life of the player.
     * @param dimension the dimension of the life.
     */
    public LifeImpl(final int numLife, final Dimension dimension) {
        final int numLifeExternal = numLife;
        this.numLife = numLifeExternal;
        this.dimension = new Dimension(dimension);
    }

    /**
     * Decrease the life of the player.
     */
    @Override
    public void decreaseLife() {
        this.numLife--;
    }

    /**
     * Increase the life of the player.
     */
    @Override
    public void increaseLife() {
        this.numLife++;
    }

    /**
     * Return the current life.
     * 
     * @return the current life.
     */
    @Override
    public int getNumLife() {
        return this.numLife;
    }

    /**
     * Return the position of the pickable.
     */
    @Override
    public Point getPosition() {
        return null;
    }

    /**
     * Return the Url of the image of the pickable.
     */
    @Override
    public URL getImageUrl() {
        return IMAGE_URL;
    }

    /**
     * Return the dimension of the pickable.
     */
    @Override
    public Dimension2D getDimension() {
        return new Dimension(dimension);
    }

}
