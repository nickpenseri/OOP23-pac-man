package it.unibo.model.map.impl;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import it.unibo.model.map.api.MapBuilder;
/**
 * class which, given a map, sets the coordinates of the various objects.
 */
public class MapBuilderImpl implements MapBuilder {
    // 0 pickable 1 no-pickable 2 spawn-pac-man 3-spawn-ghost 4-gate-ghost 5 wall
    final private List<Point> spawnGhosts;
    final private Point spawnPacMan;
    final private List<Point> spawnCollectibleItems;
    final private List<Point> spawnWalls;

    /**
     * constructor that given the map reads it and saves the coordinates in the lists.
     * @param map the game map.
     */
    public MapBuilderImpl(final int[][] map) {
        this.spawnPacMan = new Point();
        this.spawnGhosts = new ArrayList<>();
        this.spawnCollectibleItems = new ArrayList<>();
        this.spawnWalls = new ArrayList<>();
        for (final var x : range(0, map.length)) {
            for (final var y : range(0, map[x].length)) {
                final int ris = map[x][y];
                if(ris == 5) {
                    this.spawnWalls.add(new Point(x, y));
                }
                else if (ris == 0) {
                    this.spawnCollectibleItems.add(new Point(x, y));
                }
                else if (ris == 2) {
                    this.spawnPacMan.setLocation(new Point(x, y));
                }
                else if (ris == 3) {
                    this.spawnGhosts.add(new Point(x, y));
                }
            }
        }
    }

    /**
     * returns the list of coordinates (x,y) of the ghost spawn.
     * @return returns the list of coordinate (x,y).
     */
    @Override
    public List<Point> getSpawnGhost() {

        return new ArrayList<>(this.spawnGhosts);
    }

    /**
     * 
     * @return
     */
    @Override
    public Point getPacManSpawn() {

        return this.spawnPacMan.getLocation();
    }

    /**
     * returns the list of coordinates (x,y) of the collectible item spawn.
     * @return returns the list of coordinate (x,y).
     */
    @Override
    public List<Point> getSpawnCollectibleItems() {

        return new ArrayList<>(this.spawnCollectibleItems);
    }

    /**
     * returns the list of coordinates (x,y) of the walls spawn.
     * @return returns the list of coordinate (x,y).
     */
    @Override
    public List<Point> getWallsPath() {

        return new ArrayList<>(this.spawnWalls);
    }

    private Iterable<Integer> range(final int x, final int y) {

        return x < y ? () -> IntStream.rangeClosed(x, y).iterator() : () -> IntStream.rangeClosed(y, x).iterator();
    }

}
