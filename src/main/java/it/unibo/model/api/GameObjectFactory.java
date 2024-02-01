package it.unibo.model.api;

import java.awt.Dimension;
import java.awt.Point;

import it.unibo.model.impl.GameObjectImpl;
import it.unibo.model.impl.GameObjectImpl.Type;

/**
 * This interface models an object that creates game objects.
 */
public interface GameObjectFactory {
    /**
     * Creates a game object with no graphics and the given parameters.
     * @param position the position of the object
     * @param dimension the dimension of the object
     * @return the created game object
     */
    GameObject createGameObjectWithEmptyGraphics(Point position, Dimension dimension);
    /**
     * creates a gameobject based on the required type.
     * @param position the position of the object
     * @param type the type of the object
     * @return the created game object
     */
    GameObjectImpl createGameObject(Point position, Type type);

}
