package it.unibo.model.pickable.api;

import java.awt.Point;
import java.awt.geom.Dimension2D;
import java.util.List;
import java.util.Map;

import it.unibo.model.pacman.api.PacMan;

/** Is the generator of pickable map and do the offect of this. */
public interface PickableGenerator {
    /**
     * Generate a Map of Pickable.
     * 
     * @param pickableSpawnPoints is a list of Point where the pickable can spawn.
     */
    void generateMap(List<Point> pickableSpawnPoints, Dimension2D dimension);

    /**
     * Is for get the Map of Pickable.
     * 
     * @return a Map of Pickable.
     */
    Map<Point, Pickable> getPickableMap();

    /**
     * Is for take a Pickable and do the effect of this.
     * 
     * @param point is the point where the pickable is.
     * @param pacman is the pacman that take the pickable.
     */
    void takePickable(Point point, PacMan pacman);

    /**
     * Is for know if the pickable is finished.
     * 
     * @return true if the pickable is finished.
     */
    boolean finishedPickable();
}
