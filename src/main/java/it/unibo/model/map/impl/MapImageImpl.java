package it.unibo.model.map.impl;

import java.net.URL;

import it.unibo.model.impl.GameObjectImpl.Type;
import it.unibo.model.map.api.MapImage;

/** class that returns its URL based on the object. */
public class MapImageImpl implements MapImage {
    private static final String WALL_NAME = "/image/map/wall/Wall.png";
    private static final String PATH_PASSABLE = "/image/map/flor/Flor.png";
    private static final String GATE_NAME = "/image/map/gate/Gate.png";
    private final URL wallUrl = MapImageImpl.class.getResource(WALL_NAME);
    private final URL pathPassableUrl =  MapImageImpl.class.getResource(PATH_PASSABLE);
    private final URL gateUrl =  MapImageImpl.class.getResource(GATE_NAME);

    /**
     * {@inheritDoc}
     */
    @Override
    public URL getObjectUrl(final Type type) {
        switch (type) {
            case WALL:
                return wallUrl;
            case FLOR:
                return pathPassableUrl;
            case GATE:
                return gateUrl;
            default:
                return null;
        }
    }
}
