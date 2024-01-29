package it.unibo.model.map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import it.unibo.model.map.impl.MapReaderImpl;
/**
 * this class is used to test the MapReader.java.
 */
class TestMapReader {
    private final int[][] actualMap;
    private static final int W = 5;
    private static final int O = 0;

    /**method that creates a MapReader object for testing. */
    TestMapReader() {
        final String filePath = "src/main/resources/map1.txt";
        final MapReaderImpl mapReader = new MapReaderImpl(filePath);
        this.actualMap = mapReader.getMap();
    }

    /**method that checks whether the map has been read correctly. */
    @Test
    void testGetMap() {
        assertNotNull(this.actualMap, "actualMap was not initialized");
        final int[][] expectedMap = {
                { W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W },
                { W, O, O, O, O, O, O, O, O, O, O, O, W, O, O, O, O, O, O, O, O, O, O, O, W },
                { W, O, W, W, W, W, O, W, W, W, W, O, W, O, W, W, W, W, O, W, W, W, W, O, W }
        };
        assertEquals(expectedMap[0].length, this.actualMap[0].length);
        for (int i = O; i < expectedMap.length; i++) {
            assertEquals(expectedMap[i].length, this.actualMap[i].length);
            for (int j = O; j < expectedMap[i].length; j++) {
                assertEquals(expectedMap[i][j], this.actualMap[i][j]);
            }
        }
    }
}
