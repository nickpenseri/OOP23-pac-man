package it.unibo.model.map.impl;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import it.unibo.model.map.api.MapBuilder;

public class MapBuilderImpl implements MapBuilder {
    // 0 pickable 1 no-pickable 2 spawn-pac-man 3-spawn-ghost 4-gate-ghost 5 wall
    final private List<Point> spawnGhosts;
    final private Point spawnPacMan;
    final private List<Point> spawnCollectibleItems;
    final private List<Point> spawnWalls;

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

    @Override
    public List<Point> getSpawnGhost() {
        final List<Point> copyFenceGhost = new ArrayList<>();
        for (Point point : this.spawnGhosts) {

            copyFenceGhost.add(point);
        }
        return copyFenceGhost;
    }

    @Override
    public Point getPacManSpawn() {

        return this.spawnPacMan.getLocation();
    }

    @Override
    public List<Point> getSpawnCollectibleItems() {

        final List<Point> copySpawnCollectibleItems = new ArrayList<>();
        for (Point point : this.spawnCollectibleItems) {

            copySpawnCollectibleItems.add(point);
        }
        return copySpawnCollectibleItems;
    }

    @Override
    public List<Point> getWallsPath() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getWallsPath'");
    }

    private Iterable<Integer> range(final int x, final int y) {

        return x < y ? () -> IntStream.rangeClosed(x, y).iterator() : () -> IntStream.rangeClosed(y, x).iterator();
    }

}
