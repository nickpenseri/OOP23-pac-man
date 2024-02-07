package it.unibo.model.impl;

import java.awt.Dimension;
import java.awt.Point;
import java.util.List;

import it.unibo.model.api.GameObject;
import it.unibo.model.api.GameObjectFactory;
import it.unibo.model.ghost.api.Ghost;
import it.unibo.model.ghost.api.GhostColor;
import it.unibo.model.ghost.api.GhostFactory;
import it.unibo.model.ghost.impl.GhostFactoryImpl;
import it.unibo.model.impl.GameObjectImpl.Type;
import it.unibo.model.map.impl.MapImageImpl;
import it.unibo.model.pacman.api.PacMan;
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
    private final MapImageImpl mapImage = new MapImageImpl();
    private final GhostFactory ghostFactory;
    private final int mapWidth;
    private final int mapHeigth;
    private static final double PACMAN_SIZE_OFFSET = 0.9;

    /**
     * sets the size of objects based on map size and screen window size.
     * 
     * @param height screen window height
     * @param width  screen window depth
     * @param sizeX  row map size
     * @param sizeY  column map size
     */
    public GameObjectFactoryImpl(final int height, final int width, final int sizeX, final int sizeY) {
        final int minDimension = Math.min(width / sizeY, height / sizeX);
        this.dimension = new Dimension(minDimension, minDimension);
        ghostFactory = new GhostFactoryImpl((int) dimension.getWidth(), (int) dimension.getHeight());
        this.mapWidth = (int) (sizeY * dimension.getWidth());
        this.mapHeigth = (int) (sizeX * dimension.getHeight());
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
    public Ghost createGhost(final Point position, final double speed, final GhostColor color) {
        switch (color) {
            case RED:
                return ghostFactory.createRedGhost(position, speed);
            case PINK:
                return ghostFactory.createPinkGhost(position, speed);
            case BLUE:
                return ghostFactory.createBlueGhost(position, speed);
            case ORANGE:
                return ghostFactory.createOrangeGhost(position, speed);
            default:
                return ghostFactory.createRedGhost(position, speed);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PacMan createPacMan(final Point position, final double speed, final int startingLives,
            final List<GameObject> walls) {
        final Dimension dimension = new Dimension((int) (this.dimension.getWidth() * PACMAN_SIZE_OFFSET), 
                                    (int) (this.dimension.getHeight() * PACMAN_SIZE_OFFSET));
        return new PacManWalls(
                new PacManBordered(
                        new PacManImpl(3, dimension, speed, position),
                        mapHeigth, mapWidth),
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
