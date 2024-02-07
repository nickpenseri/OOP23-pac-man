package it.unibo.model.pacman.impl;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.geom.Dimension2D;
import java.net.URL;

import it.unibo.model.pacman.api.Life;

public class LifeImpl implements Life{
    private int numLife;
    private final Dimension dimension;
    static final URL IMAGE_URL = ClassLoader.getSystemResource("image/life/Life.png");

    public LifeImpl( int numLife, final Dimension dimension) {
        this.numLife = numLife;
        this.dimension = new Dimension(dimension);
    }

    public void decreaseLife() {
        this.numLife--;
    }

    public void increaseLife() {
        this.numLife++;
    }

    public int getNumLife() {
        return this.numLife;
    }

    @Override
    public Point getPosition() {
        return null;
    }

    @Override
    public URL getImageUrl() {
        return IMAGE_URL;
    }

    @Override
    public Dimension2D getDimension() {
        return dimension;
    }
    
}
