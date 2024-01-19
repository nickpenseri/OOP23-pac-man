package it.unibo.model.pickable.impl;

import java.awt.Image;
import java.awt.geom.Dimension2D;
import java.awt.geom.Point2D.Double;

import it.unibo.model.pacman.api.PacMan;
import it.unibo.model.pickable.api.Pickable;

/**
 * Is the implementation of the Pickable, in PickableImpl there is only the
 * value points of the pickable.
 */
public class PickableImpl implements Pickable {
    static final int POINTS = 10;

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
    public Double getPosition() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getPosition'");
    }

    @Override
    public Image getImage() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getImage'");
    }

    @Override
    public Dimension2D getDimension() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getDimension'");
    }
}
