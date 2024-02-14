package it.unibo.model.ui.api;

import it.unibo.model.api.GameObject;

/**
 * This interface represents the text of the game.
 */
public interface GameObjectText extends GameObject {

    /**
     * Return the text of the game.
     * 
     * @return the text of the game.
     */
    String getText();

    /**
     * Set the text of the game.
     * 
     * @param text the text of the game.
     */
    void setText(String text);

}
