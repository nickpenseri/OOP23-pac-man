package it.unibo.model.pickable.impl;

import java.net.URL;
import java.awt.Dimension;
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
    private final Dimension dimension;
    static final URL IMAGE_URL = ClassLoader.getSystemResource("image/pickable/Pickable.png");
    static final int POINTS = 10;

    /**
     * Constructor of the PickableImpl.
     * 
     * @param position  the position of the pickable.
     * @param dimension the dimension of the pickable.
     */
    public PickableImpl(final Point position, final Dimension dimension) {
        this.position = new Point(position);
        this.dimension = new Dimension(dimension);
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
     * Return the Url of the image of the pickable.
     * 
     * @return the Url of the image of the pickable.
     */
    @Override
    public URL getImageUrl() {
        return IMAGE_URL;
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
