package it.unibo.model.map.impl;

import java.util.Arrays;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.DefaultUndirectedGraph;

import it.unibo.model.api.GameObject;
import it.unibo.model.impl.GameObjectImpl;
import it.unibo.model.impl.GameObjectImpl.Type;
import it.unibo.model.map.api.MapGraph;

/**
 * Class that, given the game map, constructs the graph relating to the path of
 * the ghosts.
 */
public class MapGraphImpl implements MapGraph {
    private final GameObjectImpl[][] map;
    private final Graph<GameObject, DefaultEdge> graph;

    /**
     * Constructor that takes a map, makes a copy of it, and initializes the graph.
     *
     * @param map The game map.
     */
    public MapGraphImpl(final GameObjectImpl[][] map) {
        this.map = deepCopy(map);
        this.graph = new DefaultUndirectedGraph<>(DefaultEdge.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Graph<GameObject, DefaultEdge> getGraph() {
        addAllNodes();
        for (int i = 0; i < this.map.length; i++) {
            for (int j = 0; j < this.map[i].length; j++) {
                final var gameObject = this.map[i][j];
                addEdgeIfFlor(gameObject, i, j + 1);
                addEdgeIfFlor(gameObject, i, j - 1);
                addEdgeIfFlor(gameObject, i - 1, j);
                addEdgeIfFlor(gameObject, i + 1, j);
            }
        }
        return Graphs.undirectedGraph(this.graph);
    }

    private void addAllNodes() {
        for (final var row : this.map) {
            for (final var gameObject : row) {
                if (gameObject.getType().equals(Type.FLOR)) {
                    this.graph.addVertex(gameObject);
                }
            }
        }
    }

    private void addEdgeIfFlor(final GameObject source, final int i, final int j) {
        if (isValidPosition(i, j) && this.map[i][j] != null && this.map[i][j].getType().equals(Type.FLOR)) {
            this.graph.addEdge(source, this.map[i][j]);
        }
    }

    private boolean isValidPosition(final int i, final int j) {
        return i >= 0 && i < this.map.length && j >= 0 && j < this.map[i].length;
    }

    private GameObjectImpl[][] deepCopy(final GameObjectImpl[][] source) {
        GameObjectImpl[][] copy = new GameObjectImpl[source.length][];
        for (int i = 0; i < source.length; i++) {
            copy[i] = Arrays.copyOf(source[i], source[i].length);
        }
        return copy;
    }
}
