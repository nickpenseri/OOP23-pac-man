package it.unibo.model.map.impl;

import java.net.URL;

import it.unibo.model.impl.GameObjectImpl.Type;
import it.unibo.model.map.api.MapImage;

/** class that returns its URL based on the object. */
public class MapImageImpl implements MapImage {
    private static final String WALL_NAME = "image/map/wall/Wall.png";
    private static final String PATH_PASSABLE = "image/map/flor/Flor.png";
    private static final String GATE_NAME = "image/map/gate/Gate.png";
    private static final URL WALL_URL = ClassLoader.getSystemResource(WALL_NAME);
    private static final URL PATH_PASSABLE_URL = ClassLoader.getSystemResource(PATH_PASSABLE);
    private static final URL GATE_URL = ClassLoader.getSystemResource(GATE_NAME);

    /**
     * {@inheritDoc}
     */
    @Override
    public URL getObjectUrl(final Type type) {
        switch (type) {
            case WALL:
                return WALL_URL;
            case FLOR:
                return PATH_PASSABLE_URL;
            case GATE:
                return GATE_URL;
            default:
                return null;
        }
    }
}
