package it.unibo.core.api;

import it.unibo.view.api.View;

/** System windows that will render the scene panels. */
public interface Window {
    /** Render the scene panels. */
    void render();

    /** Set the scene panel to render.
     * @param scenepanel the scene panel
     */
    void setPanelScene(View scenepanel);
}
