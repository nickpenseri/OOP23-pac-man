package it.unibo.model.map.api;

import java.net.URL;

import it.unibo.model.impl.GameObjectImpl.Type;
/**this interface takes care of returning URLs of the objects representing the map.*/
public interface MapImage {

    /**
     * based on the type of the object it returns its URL.
     * @param type type of the object
     * @return url of the object
     */
    URL getObjectUrl(Type type);
}
