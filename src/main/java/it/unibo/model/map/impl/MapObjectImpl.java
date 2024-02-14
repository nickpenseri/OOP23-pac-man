package it.unibo.model.map.impl;

import java.awt.Point;

import it.unibo.model.map.api.MapObject;
import it.unibo.model.map.api.MapTypes;

/** class that creates a map object with its type and coordinate. */
public class MapObjectImpl implements MapObject {
    private final Point coordinate;
    private final MapTypes type;

    /**
     * constructor that initializes the coordinate and type of the object.
     * 
     * @param type        type of map.
     * @param coordinateX coordinate in x.
     * @param coordinateY coordinate in y.
     */
    public MapObjectImpl(final int coordinateX, final int coordinateY, final MapTypes type) {
        this.coordinate = new Point(coordinateY, coordinateX);
        this.type = type;
    }

    /**
     * get the coordinates of the object.
     * 
     * @return the coordinate
     */
    @Override
    public Point getCoordinate() {
        return new Point((int) this.coordinate.getY(), (int) this.coordinate.getX());
    }

    /**
     * get the type of the map object.
     * 
     * @return type of the object.
     */
    @Override
    public MapTypes getType() {
        return this.type;
    }

}
