package it.unibo.model.impl;

import java.awt.Dimension;
import java.awt.Point;
import java.util.List;

import it.unibo.model.api.GameObject;
import it.unibo.model.api.GameObjectFactory;
import it.unibo.model.api.SceneBuilder;
import it.unibo.model.ghost.api.Ghost;
import it.unibo.model.ghost.api.GhostColor;
import it.unibo.model.ghost.api.GhostFactory;
import it.unibo.model.ghost.api.ghostbehaviour.FollowingGhost;
import it.unibo.model.ghost.api.ghostbehaviour.GhostBehaviours;
import it.unibo.model.ghost.api.ghostbehaviour.GhostCoordinates;
import it.unibo.model.ghost.impl.GhostFactoryImpl;
import it.unibo.model.impl.GameObjectImpl.Type;
import it.unibo.model.map.impl.MapImageImpl;
import it.unibo.model.pacman.api.GamePacMan;
import it.unibo.model.pacman.impl.PacManBordered;
import it.unibo.model.pacman.impl.PacManImpl;
import it.unibo.model.pacman.impl.PacManWalls;
import it.unibo.model.pickable.api.PickableGenerator;
import it.unibo.model.pickable.impl.PickableGeneratorImpl;

/**
 * This class implements the {@link GameObjectFactory} interface.
 */
public class GameObjectFactoryImpl implements GameObjectFactory {
    private final Dimension dimension;
    private final Dimension mapDimension;
    private final MapImageImpl mapImage = new MapImageImpl();
    private final GhostFactory ghostFactory;
   
    private final double baseSpeed;
    private static final double PACMAN_SIZE_MULTIPLIER = 0.75;
    private static final int CELLS_PER_SECOND = 4;
    private static final double GHOST_SPEED_MULTIPLIER = 0.5;

    /**
     * sets the size of objects based on map size and screen window size.
     * 
     * @param height screen window height
     * @param width  screen window depth
     * @param sizeX  row map size
     * @param sizeY  column map size
     */
    public GameObjectFactoryImpl(final SceneBuilder sceneBuilder) {
    
        this.dimension = sceneBuilder.getTileDimension();
        ghostFactory = new GhostFactoryImpl((int) dimension.getWidth(), (int) dimension.getHeight());
        this.mapDimension = sceneBuilder.getMapDimension();
        this.baseSpeed = CELLS_PER_SECOND * dimension.getWidth();
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
    public Ghost createGhost(final Point position, final GhostColor color) {
        final double ghostSpeed = this.baseSpeed * GHOST_SPEED_MULTIPLIER;
        switch (color) {
            case RED:
                return ghostFactory.createRedGhost(position, ghostSpeed);
            case PINK:
                return ghostFactory.createPinkGhost(position, ghostSpeed);
            case BLUE:
                return ghostFactory.createBlueGhost(position, ghostSpeed);
            case ORANGE:
                return ghostFactory.createOrangeGhost(position, ghostSpeed);
            default:
                return ghostFactory.createRedGhost(position, ghostSpeed);
        }
    }


     /**
     * {@inheritDoc}
     */
    @Override
    public FollowingGhost createGhost(final Point position, final GhostColor color, final GhostCoordinates mapCoordinates, 
            final GhostBehaviours behaviour) {
        final double ghostSpeed = this.baseSpeed * GHOST_SPEED_MULTIPLIER;
        switch (color) {
            case RED:
                return ghostFactory.createRedGhost(position, ghostSpeed, mapCoordinates, behaviour);
            case PINK:
                return ghostFactory.createPinkGhost(position, ghostSpeed, mapCoordinates, behaviour);
            case BLUE:
                return ghostFactory.createBlueGhost(position, ghostSpeed, mapCoordinates, behaviour);
            case ORANGE:
                return ghostFactory.createOrangeGhost(position, ghostSpeed, mapCoordinates, behaviour);
            default:
                return ghostFactory.createRedGhost(position, ghostSpeed, mapCoordinates, behaviour);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GamePacMan createPacMan(final Point position, final int startingLives,
            final List<GameObject> walls) {
        final Dimension dimension = new Dimension((int) (this.dimension.getWidth() * PACMAN_SIZE_MULTIPLIER), 
                                    (int) (this.dimension.getHeight() * PACMAN_SIZE_MULTIPLIER));
        return new PacManWalls(
                new PacManBordered(
                        new PacManImpl(startingLives, dimension, this.baseSpeed, position),
                       (int) mapDimension.getHeight(), (int) mapDimension.getWidth()) ,
                walls);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Dimension getStandardDimension() {
        return new Dimension(this.dimension);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PickableGenerator createPickableGenerator(final List<Point> positions) {
        final PickableGenerator pickableGenerator = new PickableGeneratorImpl();
        pickableGenerator.generateMap(positions, dimension);
        return pickableGenerator;
    }

}
