package it.unibo.model.map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.model.map.impl.MapReaderImpl;
/**
 * this class is used to test the MapReader.java.
 */
class TestMapReader {
    private int[][] actualMap;
    private static final int W = 5;
    private static final int G = 4;
    private static final int P = 2;
    private static final int O = 0;
    private static final int N = 1;
    private static final int S = 3;

    /**method that creates a MapReader object for testing. */
    @BeforeEach
    void setUp() {
        final String filePath = "src/main/resources/map1.txt";
        final MapReaderImpl mapReader = new MapReaderImpl(filePath);
        this.actualMap = mapReader.getMap();
    }

    /**method that checks whether the map has been read correctly. */
    @Test
    void testGetMap() {
        assertNotNull(this.actualMap, "actualMap non è stata inizializzata");
        final int[][] expectedMap = {
                { W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W },
                { W, O, O, O, O, O, O, O, O, O, O, O, W, O, O, O, O, O, O, O, O, O, O, O, W },
                { W, O, W, W, W, W, O, W, W, W, W, O, W, O, W, W, W, W, O, W, W, W, W, O, W },
                { W, O, W, W, W, W, O, W, W, W, W, O, W, O, W, W, W, W, O, W, W, W, W, O, W },
                { W, O, W, W, W, W, O, W, W, W, W, O, W, O, W, W, W, W, O, W, W, W, W, O, W },
                { W, O, O, O, O, O, O, O, O, O, O, O, O, O, O, O, O, O, O, O, O, O, O, O, W },
                { W, O, W, W, W, W, O, W, O, W, W, W, W, W, W, W, O, W, O, W, W, W, W, O, W },
                { W, O, W, W, W, W, O, W, O, W, W, W, W, W, W, W, O, W, O, W, W, W, W, O, W },
                { W, O, O, O, O, O, O, W, O, O, O, O, W, O, O, O, O, W, O, O, O, O, O, O, W },
                { W, W, W, W, W, W, O, W, W, W, W, N, W, N, W, W, W, W, O, W, W, W, W, W, W },
                { W, W, W, W, W, W, O, W, N, N, N, N, N, N, N, N, N, W, O, W, W, W, W, W, W },
                { W, W, W, W, W, W, O, W, N, W, W, W, G, W, W, W, N, W, O, W, W, W, W, W, W },
                { O, O, O, O, O, O, O, N, N, W, W, S, S, S, W, W, N, N, O, O, O, O, O, O, O },
                { W, W, W, W, W, W, O, W, N, W, W, W, W, W, W, W, N, W, O, W, W, W, W, W, W },
                { W, W, W, W, W, W, O, W, N, N, N, N, N, N, N, N, N, W, O, W, W, W, W, W, W },
                { W, W, W, W, W, W, O, W, N, W, W, W, W, W, W, W, N, W, O, W, W, W, W, W, W },
                { W, O, O, O, O, O, O, O, O, O, O, O, W, O, O, O, O, O, O, O, O, O, O, O, W },
                { W, O, W, W, W, W, O, W, W, W, W, O, W, O, W, W, W, W, O, W, W, W, W, O, W },
                { W, O, W, W, W, W, O, W, W, W, W, O, W, O, W, W, W, W, O, W, W, W, W, O, W },
                { W, O, O, O, W, W, O, O, O, O, O, O, P, O, O, O, O, O, O, W, W, O, O, O, W },
                { W, W, W, O, W, W, O, W, W, O, W, W, W, W, W, O, W, W, O, W, W, O, W, W, W },
                { W, W, W, O, W, W, O, W, W, O, W, W, W, W, W, O, W, W, O, W, W, O, W, W, W },
                { W, O, O, O, O, O, O, W, W, O, O, O, W, O, O, O, W, W, O, O, O, O, O, O, W },
                { W, O, W, W, W, W, W, W, W, W, W, O, W, O, W, W, W, W, W, W, W, W, W, O, W },
                { W, O, W, W, W, W, W, W, W, W, W, O, W, O, W, W, W, W, W, W, W, W, W, O, W },
                { W, O, O, O, O, O, O, O, O, O, O, O, O, O, O, O, O, O, O, O, O, O, O, O, W },
                { W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W }
        };
        //final int[][] actualMap = this.mapReader.getMap();
        assertEquals(expectedMap.length, this.actualMap.length);
        for (int i = O; i < expectedMap.length; i++) {
            assertEquals(expectedMap[i].length, this.actualMap[i].length);
            for (int j = O; j < expectedMap[i].length; j++) {
                assertEquals(expectedMap[i][j], this.actualMap[i][j]);
            }
        }

    }
}
