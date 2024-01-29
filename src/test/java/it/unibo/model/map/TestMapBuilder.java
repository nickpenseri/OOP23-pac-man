package it.unibo.model.map;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.awt.Point;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.model.api.GameObject;
import it.unibo.model.impl.GameObjectImpl;
import it.unibo.model.map.impl.MapBuilderImpl;

/** class that runs tests on MapBuilder. */
class TestMapBuilder {
    private static final int W = 5;
    private static final int[][] SAMPLE_MAP = {
            { W, W, W, W, W },
            { W, 0, 0, 0, W },
            { W, 0, 2, 0, W },
            { W, 3, 3, 3, W },
            { W, W, W, W, W }
    };

    private MapBuilderImpl mapBuilder;

    /** initialize mapbuilder. */
    @BeforeEach
    void setUp() {
        mapBuilder = new MapBuilderImpl(SAMPLE_MAP);
    }

    /** method that tests ghost spawning. */
    @Test
    void testGetSpawnGhost() {
        final List<Point> spawnGhost = mapBuilder.getSpawnGhost();
        assertEquals(3, spawnGhost.size());
        // assertEquals(new Point(3, 1), spawnGhost.get(0));
        // assertEquals(new Point(3, 2), spawnGhost.get(1));
        // assertEquals(new Point(3, 3), spawnGhost.get(2));
        assertEquals(List.of(new Point(3, 1), new Point(3, 2), new Point(3, 3)), spawnGhost);
    }

    /** method that test pac-man spawn. */
    @Test
    void testGetPacManSpawn() {
        final Point pacManSpawn = mapBuilder.getPacManSpawn();
        assertEquals(new Point(2, 2), pacManSpawn);
    }

    /** method that tests the path of collectible objects. */
    @Test
    void testGetSpawnCollectibleItems() {
        final List<Point> spawnCollectibleItems = mapBuilder.getSpawnCollectibleItems();
        assertEquals(W, spawnCollectibleItems.size());
        final List<Point> expectedPoints = List.of(new Point(1, 1), new Point(1, 2), new Point(1, 3), new Point(2, 1),
                new Point(2, 3));
        assertEquals(expectedPoints, spawnCollectibleItems);
    }

    /** method that tests the path of the walls. */
    @Test
    void testGetWallsPath() {
        final List<GameObject> wallsPath = mapBuilder.getWallsPath();
        assertEquals(16, wallsPath.size());
        assertEquals(wallsPath.get(0).getPosition(), new Point(0, 0));
        assertEquals(wallsPath.get(1).getPosition(), new Point(0, 1));
        assertEquals(wallsPath.get(2).getPosition(), new Point(0, 2));
    }

    /** method that tests the map identified by gameobject. */
    @Test
    void testGetObjectsMap() {
        final GameObjectImpl[][] objectsMap = mapBuilder.getObjectsMap();
        assertEquals(SAMPLE_MAP.length, objectsMap.length);
        assertEquals(SAMPLE_MAP[0].length, objectsMap[0].length);
    }
}
