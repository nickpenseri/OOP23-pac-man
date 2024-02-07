package it.unibo.view.api;

import java.util.List;

/**
 * The InfoView interface is used to obtain the Info and update the graphics.
 */
public interface InfoView {
    /**
     * Update the view with the new Info.
     * 
     * @param info the new Info
     */
    void updateView(List<Integer> info);
}
