package it.unibo.model.map.impl;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import java.awt.Dimension;

import it.unibo.model.api.GameObject;
import it.unibo.model.impl.GameObjectImpl;
import it.unibo.model.impl.GameObjectImpl.Type;
import it.unibo.model.map.api.MapBuilder;
import it.unibo.model.map.api.MapTypes;

/**
 * class which, given a map, sets the coordinates of the various objects.
 */
public class MapBuilderImpl implements MapBuilder {
    // 0 pickable 1 no-pickable 2 spawn-pac-man 3-spawn-ghost 4-gate-ghost 5 wall
    private final List<Point> spawnGhosts;
    private final Point spawnPacMan;
    private final List<Point> spawnCollectibleItems;
    private final List<GameObject> spawnWalls;
    private final MapImageImpl mapImage = new MapImageImpl();
    private GameObjectImpl[][] objectsMap;

    /**
     * constructor that given the map reads it and saves the coordinates in the
     * lists.
     * 
     * @param map the game map.
     */
    public MapBuilderImpl(final int[][] map) {
        this.spawnPacMan = new Point();
        this.spawnGhosts = new ArrayList<>();
        this.spawnCollectibleItems = new ArrayList<>();
        this.spawnWalls = new ArrayList<>();
        this.objectsMap = new GameObjectImpl[map.length][map[0].length];
        for (final var x : range(0, map.length)) {
            for (final var y : range(0, map[x].length)) {
                final int ris = map[x][y];
                final MapTypes maptype = MapTypes.values()[ris];
                switch (maptype) {
                    case PICKABLE:
                        this.spawnCollectibleItems.add(new Point(x, y));
                        this.objectsMap[x][y] = new GameObjectImpl(
                                new Point(x, y), this.mapImage.getObjectUrl(Type.PASSABLE), new Dimension(),
                                Type.PASSABLE);
                        break;
                    case SPAWN_PAC_MAN:
                        this.spawnPacMan.setLocation(new Point(x, y));
                        this.objectsMap[x][y] = new GameObjectImpl(
                                new Point(x, y), this.mapImage.getObjectUrl(Type.PASSABLE), new Dimension(),
                                Type.PASSABLE);
                        break;
                    case SPAWN_GHOST:
                        this.spawnGhosts.add(new Point(x, y));
                        this.objectsMap[x][y] = new GameObjectImpl(
                                new Point(x, y), this.mapImage.getObjectUrl(Type.PASSABLE), new Dimension(),
                                Type.PASSABLE);
                        break;
                    case WALL:
                        this.spawnWalls.add(
                                new GameObjectImpl(
                                        new Point(x, y), this.mapImage.getObjectUrl(Type.WALL), new Dimension(),
                                        Type.WALL));
                        this.objectsMap[x][y] = new GameObjectImpl(
                                new Point(x, y), this.mapImage.getObjectUrl(Type.WALL), new Dimension(), Type.WALL);
                        break;
                    case GATE_GHOST, NO_PICKABLE:
                        this.objectsMap[x][y] = new GameObjectImpl(
                                new Point(x, y), this.mapImage.getObjectUrl(Type.PASSABLE), new Dimension(),
                                Type.PASSABLE);
                        break;
                    default:
                        break;
                }
            }
        }
    }

    /**
     * returns the list of coordinates (x,y) of the ghost spawn.
     * 
     * @return returns the list of coordinate (x,y).
     */
    @Override
    public List<Point> getSpawnGhost() {

        return new ArrayList<>(this.spawnGhosts);
    }

    /**
     * returns the (x,y) coordinate of pac-man.
     * 
     * @return returns a coordinate (x,y).
     */
    @Override
    public Point getPacManSpawn() {

        return this.spawnPacMan.getLocation();
    }

    /**
     * returns the list of coordinates (x,y) of the collectible item spawn.
     * 
     * @return returns the list of coordinate (x,y).
     */
    @Override
    public List<Point> getSpawnCollectibleItems() {

        return new ArrayList<>(this.spawnCollectibleItems);
    }

    /**
     * returns objects of type wall.
     * 
     * @return returns the list of the objects.
     */
    @Override
    public List<GameObject> getWallsPath() {

        return new ArrayList<>(this.spawnWalls);
    }

    private Iterable<Integer> range(final int x, final int y) {

        return x < y ? () -> IntStream.rangeClosed(x, y).iterator() : () -> IntStream.rangeClosed(y, x).iterator();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GameObjectImpl[][] getObjectsMap() {
        final GameObjectImpl[][] copy = new GameObjectImpl[this.objectsMap.length][];
        for (int i = 0; i < this.objectsMap.length; i++) {
            copy[i] = this.objectsMap[i].clone();
        }
        return copy;
    }

}
