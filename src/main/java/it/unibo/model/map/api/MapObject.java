package it.unibo.model.map.api;

import java.awt.Point;
/**interface used for creating map-type objects. */
public interface MapObject {
    /**
     * get the coordinates.
     * @return the coordinates represented by the point class.
     */
    Point getCoordinate();
    /**
     * get type of the object.
     * @return return the type.
     */
    MapTypes getType();
}
