package it.unibo.model.ui;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.geom.Dimension2D;
import java.net.URL;

import it.unibo.model.api.GameObject;

public class GameObjectLife implements GameObject{
    private final Point position;
    private final URL imageUrl = GameObjectLife.class.getResource("/image/life/Life.png");
    private final Dimension dimension;

    public GameObjectLife(final Point position, final Dimension dimension) {
        this.position = new Point(position);
        this.dimension = new Dimension(dimension);
    }
    
    @Override
    public Point getPosition() {
        return new Point(position);
    }

    @Override
    public URL getImageUrl() {
        return imageUrl;
    }

    @Override
    public Dimension2D getDimension() {
        return new Dimension(dimension);
    }
    
}
