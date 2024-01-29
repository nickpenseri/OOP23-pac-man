package it.unibo.model.impl;

import java.awt.Dimension;
import java.awt.Point;
import java.util.Objects;

import it.unibo.model.api.GameObject;
import it.unibo.model.api.GameObjectFactory;
import it.unibo.model.impl.GameObjectImpl.Type;

public class GameObjectFactoryImpl implements GameObjectFactory{

    @Override
    public GameObject createGameObjectWithEmptyGraphics(final Point position,final Dimension dimension,final Type type) {
        return new GameObjectImpl( Objects.requireNonNull(position), ClassLoader.getSystemResource("image/map/flor/Flor.png"), Objects.requireNonNull(dimension), Objects.requireNonNull(type));
    }
    
}
