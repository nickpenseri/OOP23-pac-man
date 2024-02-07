package it.unibo.model.map.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.stream.IntStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import it.unibo.model.map.api.MapReader;

/**
 * class that, given the path of a file, saves and returns the contents.
 */
public class MapReaderImpl implements MapReader {

    private int[][] map;

    /**
     * creates a map given its file path.
     * 
     * @param pathFileName file path.
     */
    public MapReaderImpl(final String pathFileName) {
        final Logger log = LoggerFactory.getLogger(MapReaderImpl.class);
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(
                        MapReaderImpl.class.getResourceAsStream(pathFileName),
                        StandardCharsets.UTF_8))) {
            final String dimensionsLine = reader.readLine();
            if (dimensionsLine != null) {
                final String[] dimensions = dimensionsLine.split(" ");
                final int numRows = Integer.parseInt(dimensions[0]);
                final int numColumns = Integer.parseInt(dimensions[1]);
                this.map = new int[numRows][numColumns];
                for (final var row : range(0, numRows - 1)) {
                    final String valuesLine = reader.readLine();
                    if (valuesLine != null) {
                        final String[] values = valuesLine.split(" ");
                        for (final var column : range(0, numColumns - 1)) {
                            this.map[row][column] = Integer.parseInt(values[column]);
                        }
                    } else {
                        log.error("il file termina inaspettatamente");
                        break;
                    }
                }
            } else {
                log.error("il file è vuoto");
            }

        } catch (IOException e) {
            log.error("errore durante la lettura del file" + e.getMessage());
        }
    }

    /**
     * returns the last map read through a protective copy.
     */
    @Override
    public int[][] getMap() {
        final int[][] copy = new int[this.map.length][];
        for (int i = 0; i < this.map.length; i++) {
            copy[i] = this.map[i].clone();
        }
        return copy;
    }

    private Iterable<Integer> range(final int x, final int y) {

        return x < y ? () -> IntStream.rangeClosed(x, y).iterator() : () -> IntStream.rangeClosed(y, x).iterator();
    }

}
