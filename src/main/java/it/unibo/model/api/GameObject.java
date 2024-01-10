package it.unibo.model.api;

import java.awt.Image;
import java.awt.geom.Point2D;
import java.awt.geom.Dimension2D;

/** Basic entity of a game scene */
public interface GameObject {

    /**
     * Get the position of the object
     * @return
     */

    Point2D.Double getPosition();

    /**
     * Set the position of the object in the scene
     * @param position a 2D point
     */

    void setPosition(Point2D.Double position);

    /**
     * Get the image of the actual state of the game object
     * @return Actual Image of the object
     */
    Image getImage();

    /**
     * get the actual dimension of the object in the scene
     * @return actual dimension.
     */

    Dimension2D getDimension();
}
