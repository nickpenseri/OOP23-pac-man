package it.unibo.model.map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.awt.Point;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.model.api.GameObject;
import it.unibo.model.impl.GameObjectImpl;
import it.unibo.model.map.impl.MapBuilderImpl;

/**class that runs tests on MapBuilder. */
class TestMapBuilder {

    private static final int[][] SAMPLE_MAP = {
            { 5, 5, 5, 5, 5 },
            { 5, 0, 0, 0, 5 },
            { 5, 0, 2, 0, 5 },
            { 5, 3, 3, 3, 5 },
            { 5, 5, 5, 5, 5 }
    };

    private MapBuilderImpl mapBuilder;

    /**initialize mapbuilder. */
    @BeforeEach
    void setUp() {
        mapBuilder = new MapBuilderImpl(SAMPLE_MAP);
    }

    /**method that tests ghost spawning. */
    @Test
    void testGetSpawnGhost() {
        List<Point> spawnGhost = mapBuilder.getSpawnGhost();
        assertEquals(3, spawnGhost.size());
        assertEquals(new Point(3, 1), spawnGhost.get(0));
        assertEquals(new Point(3, 2), spawnGhost.get(1));
        assertEquals(new Point(3, 3), spawnGhost.get(2));
    }

    /**method that test pac-man spawn */
    @Test
    void testGetPacManSpawn() {
        Point pacManSpawn = mapBuilder.getPacManSpawn();
        assertEquals(new Point(2, 2), pacManSpawn);
    }

    /**method that tests the path of collectible objects */
    @Test
    void testGetSpawnCollectibleItems() {
        List<Point> spawnCollectibleItems = mapBuilder.getSpawnCollectibleItems();
        assertEquals(5, spawnCollectibleItems.size());
        assertTrue(spawnCollectibleItems.contains(new Point(1, 1)));
        assertTrue(spawnCollectibleItems.contains(new Point(1, 2)));
        assertTrue(spawnCollectibleItems.contains(new Point(1, 3)));
        assertTrue(spawnCollectibleItems.contains(new Point(2, 3)));
        assertTrue(spawnCollectibleItems.contains(new Point(2, 1)));
    }

    /**method that tests the path of the walls. */
    @Test
    void testGetWallsPath() {
        List<GameObject> wallsPath = mapBuilder.getWallsPath();
        assertEquals(16, wallsPath.size());
        assertTrue(wallsPath.get(0).getPosition().equals(new Point(0, 0)));
        assertTrue(wallsPath.get(1).getPosition().equals(new Point(0, 1)));
        assertTrue(wallsPath.get(2).getPosition().equals(new Point(0, 2)));
    }

    /**method that tests the map identified by gameobject. */
    @Test
    void testGetObjectsMap() {
        GameObjectImpl[][] objectsMap = mapBuilder.getObjectsMap();
        assertEquals(SAMPLE_MAP.length, objectsMap.length);
        assertEquals(SAMPLE_MAP[0].length, objectsMap[0].length);
    }
}
