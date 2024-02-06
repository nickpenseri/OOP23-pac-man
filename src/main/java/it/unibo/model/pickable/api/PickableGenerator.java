package it.unibo.model.pickable.api;

import java.awt.Dimension;
import java.awt.Point;
import java.util.List;

import it.unibo.model.ghost.api.Ghost;
import it.unibo.model.pacman.api.PacMan;

/** Is the generator of pickable map and do the offect of this. */
public interface PickableGenerator {
    /**
     * Generate a Map of Pickable.
     * 
     * @param pickableSpawnPoints is a list of Point where the pickable can spawn.
     * @param dimension           is the dimension of the map.
     */
    void generateMap(List<Point> pickableSpawnPoints, Dimension dimension);

    /**
     * Is for get the List of Pickable.
     * 
     * @return a List of Pickable.
     */
    List<Pickable> getPickableList();

    /**
     * Is for take a Pickable and do the effect of this.
     * 
     * @param point  is the point where the pickable is.
     * @param pacman is the pacman that take the pickable.
     * @param ghosts is the list of ghosts that take the pickable.
     */
    void takePickable(Point point, PacMan pacman, List<Ghost> ghosts);

    /**
     * Is for know if the pickable is finished.
     * 
     * @return true if the pickable is finished.
     */
    boolean finishedPickable();
}
