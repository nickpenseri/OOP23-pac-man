package it.unibo.model.impl;

import java.awt.Dimension;
import java.awt.Point;
import java.util.List;
import java.util.stream.Collectors;

import it.unibo.model.api.GameObject;
import it.unibo.model.api.GameObjectFactory;
import it.unibo.model.api.SceneBuilder;
import it.unibo.model.ghost.api.Ghost;
import it.unibo.model.ghost.api.GhostColor;
import it.unibo.model.ghost.api.GhostFactory;
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
    private final double offsetX;
    private final double baseSpeed;
    private static final double PACMAN_SIZE_MULTIPLIER = 0.75;
    private static final int CELLS_PER_SECOND = 4;
    private static final double GHOST_SPEED_MULTIPLIER = 0.5;

    /**
     * sets the size of objects based on map size and screen window size.
     * 
     * @param sceneBuilder scene builder with window and map size
     */
    public GameObjectFactoryImpl(final SceneBuilder sceneBuilder) {
        this.dimension = sceneBuilder.getTileDimension();
        this.mapDimension = sceneBuilder.getMapDimension();
        ghostFactory = new GhostFactoryImpl((int) dimension.getWidth(), (int) dimension.getHeight());
        this.baseSpeed = CELLS_PER_SECOND * dimension.getWidth();
        this.offsetX = sceneBuilder.offsetX();
    }

    /**
     * sets the size of objects based on map size and screen window size.
     * 
     * @param objectWidth  object width
     * @param objectHeigth object height
     * @param mapWidth     map width
     * @param mapHeight    map height
     */
    public GameObjectFactoryImpl(final int objectWidth, final int objectHeigth, final int mapWidth,
            final int mapHeight) {
        this.dimension = new Dimension(objectWidth, objectHeigth);
        this.mapDimension = new Dimension(mapWidth, mapHeight);
        ghostFactory = new GhostFactoryImpl(objectWidth, objectHeigth);
        this.baseSpeed = CELLS_PER_SECOND * objectWidth;
        this.offsetX = 0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GameObjectImpl createGameObject(final Point position, final Type type) {
        return new GameObjectImpl(traslatePosition(position), this.mapImage.getObjectUrl(type), dimension, type);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Ghost createGhost(final Point position, final GhostColor color) {
        final double ghostSpeed = this.baseSpeed * GHOST_SPEED_MULTIPLIER;
        switch (color) {
            case RED:
                return ghostFactory.createRedGhost(traslatePosition(position), ghostSpeed);
            case PINK:
                return ghostFactory.createPinkGhost(traslatePosition(position), ghostSpeed);
            case BLUE:
                return ghostFactory.createBlueGhost(traslatePosition(position), ghostSpeed);
            case ORANGE:
                return ghostFactory.createOrangeGhost(traslatePosition(position), ghostSpeed);
            default:
                return ghostFactory.createRedGhost(traslatePosition(position), ghostSpeed);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Ghost createGhost(final Point position, final GhostColor color,
            final GhostCoordinates mapCoordinates,
            final GhostBehaviours behaviour) {
        final double ghostSpeed = this.baseSpeed * GHOST_SPEED_MULTIPLIER;
        switch (color) {
            case RED:
                return ghostFactory.createRedGhost(traslatePosition(position), ghostSpeed, mapCoordinates, behaviour);
            case PINK:
                return ghostFactory.createPinkGhost(traslatePosition(position), ghostSpeed, mapCoordinates, behaviour);
            case BLUE:
                return ghostFactory.createBlueGhost(traslatePosition(position), ghostSpeed, mapCoordinates, behaviour);
            case ORANGE:
                return ghostFactory.createOrangeGhost(traslatePosition(position), ghostSpeed, mapCoordinates,
                        behaviour);
            default:
                return ghostFactory.createRedGhost(traslatePosition(position), ghostSpeed, mapCoordinates, behaviour);
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
                        new PacManImpl(startingLives, dimension, this.baseSpeed, traslatePosition(position)),
                        (int) mapDimension.getHeight(), (int) (mapDimension.getWidth() + offsetX), 0, (int) offsetX),
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
        final var translatedList = positions.stream().map(this::traslatePosition).collect(Collectors.toList());
        pickableGenerator.generateMap(translatedList, dimension);
        return pickableGenerator;
    }

    private Point traslatePosition(final Point position) {
        return new Point((int) (position.getX()), (int) (position.getY()));
    }

}
