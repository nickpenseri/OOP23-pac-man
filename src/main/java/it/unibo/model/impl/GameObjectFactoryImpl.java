package it.unibo.model.impl;

import java.awt.Dimension;
import java.awt.Point;
import java.util.Objects;

import it.unibo.model.api.GameObject;
import it.unibo.model.api.GameObjectFactory;
import it.unibo.model.ghost.api.GhostColor;
import it.unibo.model.ghost.api.GhostFactory;
import it.unibo.model.ghost.impl.GhostFactoryImpl;
import it.unibo.model.impl.GameObjectImpl.Type;
import it.unibo.model.map.impl.MapImageImpl;

/**
 * This class implements the {@link GameObjectFactory} interface.
 */
public class GameObjectFactoryImpl implements GameObjectFactory {
    private final Dimension dimension;
    private final MapImageImpl mapImage = new MapImageImpl();
    private final GhostFactory ghostFactory;
    /**
     * sets the size of objects based on map size and screen window size.
     * @param height screen window height
     * @param width screen window depth
     * @param sizeX row map size
     * @param sizeY column map size
     */
    public GameObjectFactoryImpl(final int height, final int width, final int sizeX, final int sizeY) {
        this.dimension = new Dimension(width / sizeY, height / sizeX);
        ghostFactory = new GhostFactoryImpl((int) dimension.getWidth(), (int) dimension.getHeight());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GameObject createGameObjectWithEmptyGraphics(final Point position, final Dimension dimension) {
        final var pos = Objects.requireNonNull(position);
        final var dim = Objects.requireNonNull(dimension);
        final var img = ClassLoader.getSystemResource("image/map/flor/Flor.png");
        return new GameObjectImpl(pos, img, dim, GameObjectImpl.Type.FLOR);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GameObjectImpl createGameObject(final Point position, final Type type) {
        return new GameObjectImpl(position, this.mapImage.getObjectUrl(type), dimension, type);
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public GameObject createGhost(final Point position, final int speed, final GhostColor color) {
        switch (color) {
            case RED:
                return ghostFactory.createRedGhost(position, speed);
            case PINK:
                return ghostFactory.createPinkGhost(position, speed);
            case BLUE:
                return ghostFactory.createBlueGhost(position,  speed);
            case ORANGE:
                return ghostFactory.createOrangeGhost(position, speed);
            default:
                return ghostFactory.createRedGhost(position, speed);
        }
    }

}
