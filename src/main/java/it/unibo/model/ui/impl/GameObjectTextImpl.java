package it.unibo.model.ui.impl;

import java.awt.Point;
import java.awt.geom.Dimension2D;
import java.awt.Dimension;
import java.net.URL;

import it.unibo.model.ui.api.GameObjectText;

/**
 * This class implements the GameObject interface and represents the text of the
 * game.
 */
public class GameObjectTextImpl implements GameObjectText {
    private final Point position;
    private final URL imageUrl = GameObjectTextImpl.class.getResource("/image/map/flor/Flor.png");
    private final Dimension dimension;
    private String text;

    /**
     * Constructor of the GameObjectText.
     * 
     * @param position  the position of the text.
     * @param dimension the dimension of the text.
     * @param text      the text of the game.
     */
    public GameObjectTextImpl(final Point position, final Dimension dimension, final String text) {
        this.position = new Point(position);
        this.dimension = new Dimension(dimension);
        this.text = text;
    }

    /**
     * Return the text of the game.
     * 
     * @return the text of the game.
     */
    @Override
    public String getText() {
        return text;
    }

    /**
     * Set the text of the game.
     * 
     * @param text the text of the game.
     */
    @Override
    public void setText(final String text) {
        this.text = text;
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
