package it.unibo.model.impl;

import java.awt.Dimension;
import java.awt.Point;
import java.util.Objects;

import it.unibo.model.api.GameObject;
import it.unibo.model.api.GameObjectFactory;
import it.unibo.model.impl.GameObjectImpl.Type;

/**
 * This class implements the {@link GameObjectFactory} interface.
 */
public class GameObjectFactoryImpl implements GameObjectFactory {
    /**
     * {@inheritDoc}
     */
    @Override
    public GameObject createGameObjectWithEmptyGraphics(final Point position, final Dimension dimension, final Type type) {
        final var pos = Objects.requireNonNull(position);
        final var dim = Objects.requireNonNull(dimension);
        final var t = Objects.requireNonNull(type);
        final var img = ClassLoader.getSystemResource("image/map/flor/Flor.png");
        return new GameObjectImpl(pos, img, dim, t);
    }

}
