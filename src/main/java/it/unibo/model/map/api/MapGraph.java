package it.unibo.model.map.api;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;

import it.unibo.model.api.GameObject;
/**interface which, given a two-dimensional map, constructs the graph representing the practicable route. */
public interface MapGraph {
    /**
     * returns the graph of the game map representing the path of the ghosts.
     * @return the graph
     */
    Graph<GameObject, DefaultEdge> getGraph();
}
