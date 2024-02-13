package it.unibo.model.ui;

import java.awt.Point;
import java.awt.geom.Dimension2D;
import java.awt.Dimension;
import java.net.URL;

import it.unibo.model.api.GameObject;

public class GameObjectText implements GameObject{
    private final Point position;
    private final URL imageUrl = GameObjectText.class.getResource("/image/map/flor/Flor.png");
    private final Dimension dimension;
    private String text;
    
    public GameObjectText(final Point position, final Dimension dimension, final String text) {
        this.position = new Point(position);
        this.dimension = new Dimension(dimension);
        this.text = new String(text);
    }

    public String getText() {
        return new String(text);
    }

    public void setText(final String text) {
        this.text = new String(text);
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
