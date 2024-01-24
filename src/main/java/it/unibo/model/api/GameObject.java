package it.unibo.model.api;

import java.net.URL;
import java.awt.Point;
import java.awt.geom.Dimension2D;

/** Basic entity of a game scene. */
public interface GameObject {

    /**
     * Get the position of the object.
     * @return Point 2D of the position.
     */
    Point getPosition();

    /**
     * Get the Url of the image of the game object.
     * @return Actual Url of the object.
     */
    URL getImageUrl();

    /**
     * get the actual dimension of the object in the scene.
     * @return actual dimension.
     */
    Dimension2D getDimension();
}
