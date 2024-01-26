package it.unibo.model.map.impl;

import java.net.URL;

import it.unibo.model.impl.GameObjectImpl.Type;
import it.unibo.model.map.api.MapImage;
/**class that returns its URL based on the object. */
public class MapImageImpl implements MapImage {
    private static final String WALL_NAME = "";
    private static final String PATH_PASSABLE = "";
    private static final URL WALL_URL = ClassLoader.getSystemResource(WALL_NAME);
    private static final URL PATH_PASSABLE_URL = ClassLoader.getSystemResource(PATH_PASSABLE);

    /**
     * {@inheritDoc}
     */
    @Override
    public URL getObjectUrl(final Type type) {
        switch (type) {
            case WALL:
                return WALL_URL;
            case PASSABLE:
            return PATH_PASSABLE_URL;
            default:
                return null;
        }
    }
}
