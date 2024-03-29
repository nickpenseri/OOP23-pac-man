package it.unibo.model.api;

import java.net.URL;
import java.util.Optional;

/**
 * This interface models an entity of ImageChooser, an object that is responsible of
 * updating and returning in evry moment the correct image related to an object of a 
 * pac-man character. The image should represent pac-man's direction and alternate 
 * moments in which pac-man's mouth is closed to moments in which it is opened.
 */
public interface ImageChooser {

    /**
     * Updates the image correctly based on its previous state.
     * @param elapsedTime the time passed from the last update
     */
    void update(long elapsedTime);

    /**
     * It is called to obtain the image related with the actual state of pac-man.
     * @param dir the direction of pac-man
     * @return the URL correct image related to pac-man
     */
    URL actualImageUrl(Optional<Direction> dir);
}
