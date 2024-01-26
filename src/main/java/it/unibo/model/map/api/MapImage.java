package it.unibo.model.map.api;

import java.net.URL;
/**this interface takes care of returning URLs of the objects representing the map.*/
public interface MapImage {
    /**
     * return URL that represents wall.
     * @return wall URL
     */
    URL getWallUrl();
    /**
     * URL representing the traversable path.
     * @return traversable path URL
     */
    URL getPassableUrl();
}
