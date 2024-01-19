package it.unibo.model.pickable.impl;

import java.awt.Image;
import java.awt.Point;
import java.awt.geom.Dimension2D;
import java.awt.geom.Point2D.Double;

import it.unibo.model.pacman.api.PacMan;
import it.unibo.model.pickable.api.Pickable;

/**
 * Is the implementation of the Pickable, in PickableImpl there is only the
 * value points of the pickable.
 */
public class PickableImpl implements Pickable {
    final Point position;
    final Image image = null;
    final Dimension2D dimension;
    static final int POINTS = 10;

    /**
     * Constructor of the PickableImpl.
     * 
     * @param position  the position of the pickable.
     * @param image     the image of the pickable.
     * @param dimension the dimension of the pickable.
     */
    public PickableImpl(final Point position, final Dimension2D dimension) {
        this.position = position;
        this.dimension = dimension;
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

    @Override
    public Point getPosition() {
        return position;
    }

    @Override
    public Image getImage() {
        return image;
    }

    @Override
    public Dimension2D getDimension() {
        return dimension;
    }
}
