package it.unibo.model.pickable.impl;

import java.awt.Image;
import java.awt.Point;
import java.awt.geom.Dimension2D;

import it.unibo.model.pacman.api.PacMan;
import it.unibo.model.pickable.api.Pickable;

/**
 * Is the implementation of the Pickable, in PickableImpl there is only the
 * value points of the pickable.
 */
public class PickableImpl implements Pickable {
    private final Point position;
    private final Dimension2D dimension;
    static final Image IMAGE = null;
    static final int POINTS = 10;

    /**
     * Constructor of the PickableImpl.
     * 
     * @param position  the position of the pickable.
     * @param dimension the dimension of the pickable.
     */
    public PickableImpl(final Point position, final Dimension2D dimension) {
        this.position = new Point(position);
        this.dimension = (Dimension2D) dimension.clone();
    }

    /**
     * Add the points of the item.
     * 
     * @param pacman the pacman that eat the item.
     */
    @Override
    public void addPointsPickable(final PacMan pacman) {
        pacman.addPoints(POINTS);
    }

    /**
     * Return the position of the pickable.
     * 
     * @return the position of the pickable.
     */
    @Override
    public Point getPosition() {
        return new Point(position);
    }

    /**
     * Return the image of the pickable.
     * 
     * @return the image of the pickable.
     */
    @Override
    public Image getImage() {
        return IMAGE;
    }

    /**
     * Return the dimension of the pickable.
     * 
     * @return the dimension of the pickable.
     */
    @Override
    public Dimension2D getDimension() {
        return (Dimension2D) dimension.clone();
    }
}
