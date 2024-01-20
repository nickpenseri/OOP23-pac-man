package it.unibo.model.map.impl;

import java.awt.Point;
import java.util.ArrayList;
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
    private List<Point> fenceGhosts;
    private Point spawnPacMan;
    private List<Point> spawnCollectibleItems;

    private MapDefault() {
        this.spawnPacMan = new Point();
        this.fenceGhosts = new ArrayList<>();
        this.spawnCollectibleItems = new ArrayList<>();
        for(int row = 0 ; row < this.map.length ; row ++ ) {

            for(int column = 0 ; column < this.map[row].length ; column ++) {

                if(this.map[row][column] == 3) {

                    this.fenceGhosts.add(new Point(row, column));
                }
                else if(this.map[row][column] == 2) {

                    this.spawnPacMan.setLocation(row, column);
                }
                else if(this.map[row][column] == 0) {

                    this.spawnCollectibleItems.add(new Point(row, column));
                }
            }
        }
    }

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
        final List<Point> copyFenceGhost = new ArrayList<>();
        for (Point point : this.fenceGhosts) {
            
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

}
