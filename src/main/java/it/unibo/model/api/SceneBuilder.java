package it.unibo.model.api;

import java.awt.Dimension;


/**
 * This interface provides the methods for getting the game world and ui dimensions.
 
 */
public interface SceneBuilder {

    /**
     * @return the game world dimension
     */
    Dimension getGameWorldDimension();

    /**
     * @return the ui dimension
     */
    Dimension getUiDimension();
}
