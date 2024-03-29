package it.unibo.model.impl;

import java.awt.Point;
import java.awt.geom.Dimension2D;
import java.awt.Dimension;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Objects;

import it.unibo.model.api.GameObject;

/** Basic Implementation of a GameObject. */
public class GameObjectImpl implements GameObject {
    /** type of the GameObject. */
    public enum Type { WALL, FLOR, GATE }
    private final Type type;
    private final Point position;
    private final URL imageUrl;
    private final Dimension dimension;

    /**
     * Constructor of a GameObject.
     * 
     * @param position  the position of the object
     * @param imageUrl  the url of the image of the object
     * @param dimension the dimension of the object
     * @param type      the type of the object
     */
    public GameObjectImpl(final Point position, final URL imageUrl, final Dimension dimension, final Type type) {
        this.position = new Point(Objects.requireNonNull(position));
        this.dimension = new Dimension(Objects.requireNonNull(dimension));
        this.type = type;

        URL tempUrl;
        try {
            tempUrl = new URL(Objects.requireNonNull(imageUrl).toString());
        } catch (MalformedURLException e) {
            tempUrl = null;
        }

        this.imageUrl = tempUrl;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Point getPosition() {
        return new Point(position);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public URL getImageUrl() {

        URL tempUrl;
        try {
            tempUrl = new URL(this.imageUrl.toString());
        } catch (MalformedURLException e) {
            tempUrl = null;
        }

        return tempUrl;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Dimension2D getDimension() {
        return new Dimension(this.dimension);
    }

    /**
     * return type of this Gameobject.
     * 
     * @return return type of this Gameobject.
     */
    public Type getType() {
        return this.type;
    }

    /**
     * Set the position of the GameObject.
     * 
     * @param position the position to set
     */
    protected final void setPosition(final Point position) {
        this.position.setLocation(Objects.requireNonNull(position));
    }
}
