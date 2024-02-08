package it.unibo.model.map;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.awt.Dimension;
import java.awt.Point;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.model.api.GameObject;
import it.unibo.model.impl.GameObjectFactoryImpl;
import it.unibo.model.impl.GameObjectImpl;
import it.unibo.model.map.impl.MapBuilderImpl;

/** class that runs tests on MapBuilder. */
class TestMapBuilder {
    private static final int W = 5;
    private static final int O = 0;
    private static final int P = 2;
    private static final int G = 3;
    private static final int[][] SAMPLE_MAP = {
            { W, W, W, W, W },
            { W, O, O, O, W },
            { W, O, P, O, W },
            { W, G, G, G, W },
            { W, W, W, W, W }
    };

    private MapBuilderImpl mapBuilder;
    private Dimension dimension;

    private static final int Y = 5;
    private static final int X_FIRST_SPAWN = 5;
    private static final int X_SECOND_SPAWN = 10;
    private static final int X_THIRD_SPAWN = 15;

    /** initialize mapbuilder. */
    @BeforeEach
    void setUp() {
        mapBuilder = new MapBuilderImpl(SAMPLE_MAP, new GameObjectFactoryImpl(10, 10, 2, 2));
        this.dimension = new GameObjectFactoryImpl(10, 10, 2, 2).getStandardDimension();
    }

    /** method that tests ghost spawning. */
    @Test
    void testGetSpawnGhost() {
        final List<Point> spawnGhost = mapBuilder.getSpawnGhost();
        assertEquals(3, spawnGhost.size());
        assertEquals(List.of(new Point(X_FIRST_SPAWN, Y), new Point(X_SECOND_SPAWN, Y), new Point(X_THIRD_SPAWN, Y)),
                spawnGhost);
    }

    /** method that test pac-man spawn. */
    @Test
    void testGetPacManSpawn() {
        final Point pacManSpawn = mapBuilder.getPacManSpawn();
        assertEquals(new Point(10, 10), pacManSpawn);
    }

    /** method that tests the path of collectible objects. */
    @Test
    void testGetSpawnCollectibleItems() {
        final List<Point> spawnCollectibleItems = mapBuilder.getSpawnCollectibleItems();
        assertEquals(W, spawnCollectibleItems.size());
        final List<Point> expectedPoints = List.of(new Point(getCordinateY(dimension, 1), getCordinateX(dimension, 1)),
                new Point(getCordinateY(dimension, 2), getCordinateX(dimension, 1)),
                new Point(getCordinateY(dimension, 3), getCordinateX(dimension, 1)),
                new Point(getCordinateY(dimension, 1), getCordinateX(dimension, 2)),
                new Point(getCordinateY(dimension, 3), getCordinateX(dimension, 2)));
        assertEquals(expectedPoints, spawnCollectibleItems);
    }

    /** method that tests the path of the walls. */
    @Test
    void testGetWallsPath() {
        final List<GameObject> wallsPath = mapBuilder.getWallsPath();
        assertEquals(16, wallsPath.size());
        assertEquals(wallsPath.get(0).getPosition(),
                new Point(getCordinateY(dimension, 0), getCordinateX(dimension, 0)));
        assertEquals(wallsPath.get(1).getPosition(),
                new Point(getCordinateY(dimension, 1), getCordinateX(dimension, 0)));
        assertEquals(wallsPath.get(2).getPosition(),
                new Point(getCordinateY(dimension, 2), getCordinateX(dimension, 0)));
    }

    /** method that tests the map identified by gameobject. */
    @Test
    void testGetObjectsMap() {
        final GameObjectImpl[][] objectsMap = mapBuilder.getObjectsMap();
        assertEquals(SAMPLE_MAP.length, objectsMap.length);
        assertEquals(SAMPLE_MAP[0].length, objectsMap[0].length);
    }

    private int getCordinateY(final Dimension dimension, final int y) {
        return y * dimension.height;
    }

    private int getCordinateX(final Dimension dimension, final int x) {
        final int heightCell = TestMapBuilder.SAMPLE_MAP.length - 1 - x;
        return heightCell * dimension.width;
    }
}
