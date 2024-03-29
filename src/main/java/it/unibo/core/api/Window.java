package it.unibo.core.api;

import java.awt.Dimension;

import it.unibo.view.api.View;

/** System windows that will render the scene panels. */
public interface Window {
    /** Render the scene panels. */
    void render();

    /** Set the scene panel to render.
     * @param panelScene the scene panel
     */
    void setScenePanel(View panelScene);

    /** Get the dimension of the window.
     * @return the dimension of the window
     */
    Dimension getDimension();

     /** Get the dimension og the game panel.
     * @return the dimension of the window
     */
    Dimension getActualPanelDimension();

}
