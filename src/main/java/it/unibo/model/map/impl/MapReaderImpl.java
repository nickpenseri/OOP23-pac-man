package it.unibo.model.map.impl;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.stream.IntStream;

import it.unibo.model.map.api.MapReader;

/**
 * class that, given the path of a file, saves and returns the contents.
 */
public class MapReaderImpl implements MapReader{

    private int[][] map;
    
    @Override
    public int[][] getMap() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getMap'");
    }

    /**
     * given the path of the file it reads the contents and saves it.
     * @param file path.
     */
    @Override
    public void readFileMap(String pathFileName) {
        try(BufferedReader reader = new BufferedReader(new FileReader(pathFileName))) {
            String[] dimensions = reader.readLine().split(" ");
            int numRows = Integer.parseInt(dimensions[0]);
            int numColumns = Integer.parseInt(dimensions[1]);
            this.map = new int[numRows][numColumns];
            for (var row : range(0, numRows)) {
                String[] values = reader.readLine().split(" ");
                for (var column : range(0, numColumns)) {
                    this.map[row][column] = Integer.parseInt(values[column]); 
                }
            }
            reader.close();

        } catch (IOException e) {
            System.err.println("errore durante la lettura del file" + e.getMessage());
        } 
        
    }

    private Iterable<Integer> range(int x, int y) {

        return x < y ? ()->IntStream.rangeClosed(x,y).iterator() : ()->IntStream.rangeClosed(y, x).iterator();
    }

}
