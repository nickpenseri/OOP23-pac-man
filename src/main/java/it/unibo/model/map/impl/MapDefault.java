package it.unibo.model.map.impl;

import java.awt.Point;
import java.util.List;

import it.unibo.model.map.api.Map;

public class MapDefault implements Map {
    //0 pickable 1 no-pickable 2 spawn-pac-man 3-spawn-ghost 4-gate-ghost 5 wall
    private int[][] map = {
        {5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5},
        {5,0,0,0,0,0,0,0,0,0,0,0,5,0,0,0,0,0,0,0,0,0,0,0,5},
        {5,0,5,5,5,5,0,5,5,5,5,0,5,0,5,5,5,5,0,5,5,5,5,0,5},
        {5,0,5,5,5,5,0,5,5,5,5,0,5,0,5,5,5,5,0,5,5,5,5,0,5},
        {5,0,5,5,5,5,0,5,5,5,5,0,5,0,5,5,5,5,0,5,5,5,5,0,5},
        {5,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,5},
        {5,0,5,5,5,5,0,5,0,5,5,5,5,5,5,5,0,5,0,5,5,5,5,0,5},
        {5,0,5,5,5,5,0,5,0,5,5,5,5,5,5,5,0,5,0,5,5,5,5,0,5},
        {5,0,0,0,0,0,0,5,0,0,0,0,5,0,0,0,0,5,0,0,0,0,0,0,5},
        {5,5,5,5,5,5,0,5,5,5,5,1,5,1,5,5,5,5,0,5,5,5,5,5,5},
        {5,5,5,5,5,5,0,5,1,1,1,1,1,1,1,1,1,5,0,5,5,5,5,5,5},
        {5,5,5,5,5,5,0,5,1,5,5,5,4,5,5,5,1,5,0,5,5,5,5,5,5},
        {0,0,0,0,0,0,0,1,1,5,5,3,3,3,5,5,1,1,0,0,0,0,0,0,0},
        {5,5,5,5,5,5,0,5,1,5,5,5,5,5,5,5,1,5,0,5,5,5,5,5,5},
        {5,5,5,5,5,5,0,5,1,1,1,1,1,1,1,1,1,5,0,5,5,5,5,5,5},
        {5,5,5,5,5,5,0,5,1,5,5,5,5,5,5,5,1,5,0,5,5,5,5,5,5},
        {5,0,0,0,0,0,0,0,0,0,0,0,5,0,0,0,0,0,0,0,0,0,0,0,5},
        {5,0,5,5,5,5,0,5,5,5,5,0,5,0,5,5,5,5,0,5,5,5,5,0,5},
        {5,0,5,5,5,5,0,5,5,5,5,0,5,0,5,5,5,5,0,5,5,5,5,0,5},
        {5,0,0,0,5,5,0,0,0,0,0,0,2,0,0,0,0,0,0,5,5,0,0,0,5},
        {5,5,5,0,5,5,0,5,5,0,5,5,5,5,5,0,5,5,0,5,5,0,5,5,5},
        {5,5,5,0,5,5,0,5,5,0,5,5,5,5,5,0,5,5,0,5,5,0,5,5,5},
        {5,0,0,0,0,0,0,5,5,0,0,0,5,0,0,0,5,5,0,0,0,0,0,0,5},
        {5,0,5,5,5,5,5,5,5,5,5,0,5,0,5,5,5,5,5,5,5,5,5,0,5},
        {5,0,5,5,5,5,5,5,5,5,5,0,5,0,5,5,5,5,5,5,5,5,5,0,5},
        {5,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,5},
        {5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5}
    };

    @Override
    public int[][] getMap() {

        final int[][] copy = new int[this.map.length][];
        for (int i = 0; i < this.map.length; i++) {
            copy[i] = this.map[i].clone();
        }
        return copy;
    }

    @Override
    public List<Point> getFenceGhost() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getFenceGhost'");
    }

    @Override
    public Point getPacManSpawn() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getPacManSpawn'");
    }

    @Override
    public List<Point> getSpawnCollectibleItems() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getSpawnCollectibleItems'");
    }

}
