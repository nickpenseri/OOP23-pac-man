package it.unibo.model.map.impl;

import java.awt.Dimension;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import it.unibo.model.api.SceneBuilder;
import it.unibo.model.map.api.MapBuilder;
import it.unibo.model.map.api.MapObject;
import it.unibo.model.map.api.MapTypes;

/**
 * class which, given a map, sets the coordinates of the various objects.
 */
public class MapBuilderImpl implements MapBuilder {
    // 0 pickable 1 no-pickable 2 spawn-pac-man 3-spawn-ghost 4-gate-ghost 5 wall
    private final List<MapObject> spawnGhosts;
    private final Point spawnPacMan;
    private final List<Point> spawnCollectibleItems;
    private final List<MapObject> spawnWalls;
    private final List<MapObject> paintMap;
    private final MapObject[][] objectsMap;
    private final int offsetX;

    /**
     * constructor that given the map reads it and saves the coordinates in the
     * lists.
     * 
     * @param map         the game map.
     * @param gameFactory factory for creating game object based on window size and
     *                    map size.
     */
    public MapBuilderImpl(final int[][] map, final SceneBuilder sceneBuilder) {
        this.paintMap = new ArrayList<>();
        this.spawnPacMan = new Point();
        this.spawnGhosts = new ArrayList<>();
        this.spawnCollectibleItems = new ArrayList<>();
        this.spawnWalls = new ArrayList<>();
        this.objectsMap = new MapObjectImpl[map.length][map[0].length];
        final var dimension = sceneBuilder.getTileDimension();
        this.offsetX = sceneBuilder.offsetX();
        for (final var x : range(0, map.length - 1)) {
            for (final var y : range(0, map[x].length - 1)) {
                final int ris = map[x][y];
                final MapTypes maptype = MapTypes.values()[ris];
                switch (maptype) {
                    case PICKABLE:
                        this.spawnCollectibleItems.add(
                                new Point(getCordinateY(dimension, y), getCordinateX(dimension, x)));
                        final var object1 = new MapObjectImpl(getCordinateY(dimension, y), getCordinateX(dimension, x),
                                MapTypes.FLOOR);
                        this.paintMap.add(object1);
                        this.objectsMap[x][y] = object1;
                        break;
                    case SPAWN_PAC_MAN:
                        this.spawnPacMan.setLocation(
                                new Point(getCordinateY(dimension, y), getCordinateX(dimension, x)));
                        final var object2 = new MapObjectImpl(getCordinateY(dimension, y), getCordinateX(dimension, x),
                                MapTypes.FLOOR);
                        this.paintMap.add(object2);
                        this.objectsMap[x][y] = object2;
                        break;
                    case SPAWN_GHOST:
                        final var object3 = new MapObjectImpl(getCordinateY(dimension, y), getCordinateX(dimension, x),
                                MapTypes.FLOOR);
                        this.spawnGhosts.add(object3);
                        this.paintMap.add(object3);
                        this.objectsMap[x][y] = object3;
                        break;
                    case WALL:
                        final var object4 = new MapObjectImpl(getCordinateY(dimension, y), getCordinateX(dimension, y),
                                MapTypes.WALL);
                        this.spawnWalls.add(object4);
                        this.paintMap.add(object4);
                        this.objectsMap[x][y] = object4;
                        break;
                    case GATE_GHOST:
                        this.objectsMap[x][y] = new MapObjectImpl(getCordinateY(dimension, y),
                                getCordinateX(dimension, y), MapTypes.FLOOR);
                        final var object5 = new MapObjectImpl(getCordinateY(dimension, y), getCordinateX(dimension, y),
                                MapTypes.GATE_GHOST);
                        this.spawnWalls.add(object5);
                        this.paintMap.add(object5);
                        break;
                    case NO_PICKABLE:
                        final var object6 = new MapObjectImpl(getCordinateY(dimension, y), getCordinateX(dimension, y),
                                MapTypes.FLOOR);
                        this.paintMap.add(object6);
                        this.objectsMap[x][y] = object6;
                        break;
                    case BEHIND_WALL:
                        final var object7 = new MapObjectImpl(getCordinateY(dimension, y), getCordinateX(dimension, y),
                                MapTypes.FLOOR);
                        this.paintMap.add(object7);
                        this.objectsMap[x][y] = new MapObjectImpl(getCordinateY(dimension, y),
                                getCordinateX(dimension, y), MapTypes.WALL);
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
    public List<MapObject> getSpawnGhost() {

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
    public List<MapObject> getWallsPath() {

        return new ArrayList<>(this.spawnWalls);
    }

    private Iterable<Integer> range(final int x, final int y) {

        return x < y ? () -> IntStream.rangeClosed(x, y).iterator() : () -> IntStream.rangeClosed(y, x).iterator();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public MapObject[][] getObjectsMap() {
        final MapObject[][] copy = new MapObject[this.objectsMap.length][];
        for (int i = 0; i < this.objectsMap.length; i++) {
            copy[i] = this.objectsMap[i].clone();
        }
        return copy;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<MapObject> getPaintMap() {
        return new ArrayList<>(this.paintMap);
    }

    private int getCordinateY(final Dimension dimension, final int y) {
        return y * dimension.height;
    }

    private int getCordinateX(final Dimension dimension, final int x) {
        final int heightCell = this.objectsMap.length - 1 - x;
        return (heightCell * dimension.width) + this.offsetX;
    }

}
