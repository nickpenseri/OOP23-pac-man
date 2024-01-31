package it.unibo.model.physics.objectsmover.impl;

import it.unibo.model.api.Character;
import it.unibo.model.api.GameObject;
import it.unibo.model.physics.objectsmover.api.DirectionSelector;
import it.unibo.model.physics.objectsmover.api.PositionApproximator;

import org.jgrapht.alg.shortestpath.AStarShortestPath;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.DefaultUndirectedGraph;

import java.util.Objects;

import org.jgrapht.Graph;
import org.jgrapht.alg.interfaces.ShortestPathAlgorithm.SingleSourcePaths;
import org.jgrapht.alg.shortestpath.AStarShortestPath;

public class GraphDirectionSelector implements DirectionSelector {

    final private Graph<GameObject, DefaultEdge> graph;
    final private PositionApproximator approximator;

    public GraphDirectionSelector(final Graph<GameObject, DefaultEdge> graph) {
        this.graph = Objects.requireNonNull(graph);
        this.approximator = new PositionApproximatorImpl();
    }


    @Override
    public void setDirection(Character toMove, GameObject target) {
        Objects.requireNonNull(toMove);
        Objects.requireNonNull(target);

        var source = approximator.getApproximatedPosition(toMove, graph.vertexSet());
    }
    
}
