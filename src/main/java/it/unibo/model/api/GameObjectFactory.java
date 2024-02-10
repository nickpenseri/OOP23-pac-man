package it.unibo.model.api;

import java.awt.Dimension;
import java.util.List;
import java.awt.Point;

import it.unibo.model.ghost.api.FollowingGhost;
import it.unibo.model.ghost.api.Ghost;
import it.unibo.model.ghost.api.GhostCoordinates;
import it.unibo.model.ghost.api.GhostColor;
import it.unibo.model.impl.GameObjectImpl;
import it.unibo.model.impl.GameObjectImpl.Type;
import it.unibo.model.pacman.api.GamePacMan;
import it.unibo.model.pickable.api.PickableGenerator;

/**
 * This interface models an object that creates game objects.
 */
public interface GameObjectFactory {

    /**
     * creates a gameobject based on the required type.
     * 
     * @param position the position of the object
     * @param type     the type of the object
     * @return the created game object
     */
    GameObjectImpl createGameObject(Point position, Type type);

    /**
     * creates a ghost with the given parameters.
     * 
     * @param position the position of the ghost
     * @param color    the color of the ghost
     * @return the created ghost
     */
    Ghost createGhost(Point position, GhostColor color);

    /**
     * creates a ghost with the given parameters.
     * 
     * @param position the position of the ghost
     * @param color    the color of the ghost
     * @param coordinates the coordinates of the map for the ghost
     * @return the created ghost
     */
    FollowingGhost createGhost(Point position,  GhostColor color,  GhostCoordinates coordinates);


    /**
     * Creates a pacman object ready for the game.
     * 
     * @param position      the position of pacman
     * @param startingLives the initial number of lives
     * @param walls         the list of walls
     * @return the created pac-man
     */
    GamePacMan createPacMan(Point position, int startingLives, List<GameObject> walls);

    /**
     * Creates the pickable object with the given effect.
     * 
     * @param positions the position of the pickable
     * @return the created pickable
     */
    PickableGenerator createPickableGenerator(List<Point> positions);

    /**
     * Getter for the standard dimension of a gameObject.
     * 
     * @return the standard dimension of a gameObject
     */
    Dimension getStandardDimension();
}
