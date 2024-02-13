package it.unibo.model.impl;

import java.awt.Dimension;
import it.unibo.model.api.SceneBuilder;

/**
 * This class implements the {@link SceneBuilder} interface.
 */
public class SceneBuilderImpl implements SceneBuilder {
    private final Dimension gameWorldDimension;
    private final Dimension uiDimension;
    private final Dimension mapDimension;
    private final Dimension tileDimension;

    private static final float SCREEN_PROPORTION = 0.1f;

    /**
     * sets the size of panels based on screen window size.
     * 
     * @param screenWidth  screen window width
     * @param screenHeight screen window height
     */
    public SceneBuilderImpl(final int screenWidth, final int screenHeight, final int column, final int row) {
        uiDimension = new Dimension(screenWidth, (int) (screenHeight * SCREEN_PROPORTION));
        gameWorldDimension = new Dimension(screenWidth, screenHeight - uiDimension.height);
        final int minDimension = Math.min((int) gameWorldDimension.getWidth() / column, (int) gameWorldDimension.getHeight() / row);
        tileDimension = new Dimension(minDimension, minDimension);
        mapDimension = new Dimension(minDimension * row, minDimension * column);
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public Dimension getGameWorldDimension() {
        return  new Dimension(gameWorldDimension);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Dimension getUiDimension() {
        return new Dimension(uiDimension);
    }


    @Override
    public Dimension getMapDimension() {
        return new Dimension(mapDimension);
    }


    @Override
    public Dimension getTileDimension() {
        return new Dimension(tileDimension);
    }


    @Override
    public double offsetX() {
       return  (gameWorldDimension.getWidth() - mapDimension.getWidth()) / 2;
    }
}
