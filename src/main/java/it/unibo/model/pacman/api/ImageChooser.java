package it.unibo.model.pacman.api;

import java.awt.Image;
import it.unibo.model.api.Direction;

/**
 * This interface models an entity of ImageChooser, an object that is responsible of
 * updating and returning in evry moment the correct image related to an object of a 
 * pac-man character. The image should represent pac-man's direction and alternate 
 * moments in which pac-man's mouth is closed to momennts in which it is opened.
 */
public interface ImageChooser {
    /**
     * Updates the direction of the object, which represents pac-man's.
     * @param dir the new direction 
     */
    void setDirection(Direction dir);

    /**
     * Updates the image correctly based on its previous state.
     */
    void update();

    /**
     * It is called to obtain the image related with the actual state of pac-man.
     * @return the correct image related to pac-man
     */
    Image actualImage();
}
