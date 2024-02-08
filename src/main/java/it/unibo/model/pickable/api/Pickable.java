package it.unibo.model.pickable.api;

import java.awt.Dimension;
import java.awt.Point;

import it.unibo.model.api.GameObject;
import it.unibo.model.pacman.api.PacMan;

/** Pickable item for get points. */
public interface Pickable extends GameObject {
    /**
     * Add the points of the item.
     * 
     * @param pacman the pacman that eat the item
     */
    void addPointsPickable(PacMan pacman);

    /**
     * Return the position of the pickable for collision.
     * 
     * @return the position of the pickable for collision
     */
    Dimension getCollisionDimension();

    /**
     * Return the position of the pickable for collision.
     * 
     * @return the position of the pickable for collision
     */
    Point getCollisionPoint();
}
