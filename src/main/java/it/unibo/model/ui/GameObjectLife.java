package it.unibo.model.ui;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.geom.Dimension2D;
import java.net.URL;

import it.unibo.model.api.GameObject;

/**
 * This class implements the GameObject interface and represents the life of the
 * pacman.
 */
public class GameObjectLife implements GameObject {
    private final Point position;
    private final URL imageUrl = GameObjectLife.class.getResource("/image/life/Life.png");
    private final Dimension dimension;

    /**
     * Constructor of the GameObjectLife.
     * 
     * @param position  the position of the life.
     * @param dimension the dimension of the life.
     */
    public GameObjectLife(final Point position, final Dimension dimension) {
        this.position = new Point(position);
        this.dimension = new Dimension(dimension);
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
        return imageUrl;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Dimension2D getDimension() {
        return new Dimension(dimension);
    }

}
