package it.unibo.model.pacman.impl;

import java.awt.Point;
import java.awt.geom.Dimension2D;
import java.net.URL;

import it.unibo.model.pacman.api.Points;

/**
 * Is the implementation of the Points, in PointsImpl there is only the
 * value points of the pickable.
 */
public class PointsImpl implements Points {
    private int points;
    private final Dimension2D dimension;

    /**
     * Constructor of the PointsImpl.
     * 
     * @param points    the points of the pickable.
     * @param dimension the dimension of the pickable.
     */
    public PointsImpl(final int points, final Dimension2D dimension) {
        this.points = points;
        this.dimension = dimension;
    }

    /**
     * Return the position of the pickable.
     * 
     * @return the position of the pickable.
     */
    @Override
    public Point getPosition() {
        return null;
    }

    /**
     * Return the Url of the image of the pickable.
     * 
     * @return the Url of the image of the pickable.
     */
    @Override
    public URL getImageUrl() {
        return null;
    }

    /**
     * Return the dimension of the pickable.
     * 
     * @return the dimension of the pickable.
     */
    @Override
    public Dimension2D getDimension() {
        return dimension;
    }

    /**
     * Remove the points of the item.
     * 
     * @param points remove the points.
     */
    @Override
    public void removePoints(final int points) {
        if (points < this.points) {
            this.points -= points;
        } else {
            this.points = 0;
        }
    }

    /**
     * Add the points of the item.
     * 
     * @param points add the points.
     */
    @Override
    public void addPoints(final int points) {
        this.points += points;
    }

    /**
     * Return the current points.
     * 
     * @return the current points.
     */
    @Override
    public int getPoints() {
        return this.points;
    }

}
