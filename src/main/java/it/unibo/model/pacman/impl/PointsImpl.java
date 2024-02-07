package it.unibo.model.pacman.impl;

import java.awt.Point;
import java.awt.geom.Dimension2D;
import java.net.URL;

import it.unibo.model.pacman.api.Points;

public class PointsImpl implements Points{
    int points;
    final Dimension2D dimension;

    public PointsImpl(final int points, final Dimension2D dimension) {
        this.points = points;
        this.dimension = dimension;
    }

    @Override
    public Point getPosition() {
        return null;
    }

    @Override
    public URL getImageUrl() {
        return null;
    }

    @Override
    public Dimension2D getDimension() {
        return null;
    }

    @Override
    public void removePoints(int points) {
        if (points < this.points) {
            this.points -= points;
        }else{
            this.points = 0;
        }
    }

    @Override
    public void addPoints(int points) {
        this.points += points;
    }

    @Override
    public int getPoints() {
        return this.points;
    }
    
}
