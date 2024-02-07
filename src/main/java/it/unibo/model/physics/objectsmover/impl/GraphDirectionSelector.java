package it.unibo.model.physics.objectsmover.impl;

import it.unibo.model.api.Character;
import it.unibo.model.api.GameObject;
import it.unibo.model.physics.objectsmover.api.DirectionSelector;
import it.unibo.model.physics.objectsmover.api.PositionApproximator;

import org.jgrapht.alg.shortestpath.AStarShortestPath;
import org.jgrapht.graph.DefaultEdge;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

import java.awt.Point;
import java.util.Objects;

import org.jgrapht.Graph;
import org.jgrapht.alg.interfaces.ShortestPathAlgorithm.SingleSourcePaths;

/**
 * Implementation of the interface DirectionSelector, sets the direction of a character to move to reach a target 
 * moving on a graph.
 */
public class GraphDirectionSelector implements DirectionSelector {

    private final Graph<GameObject, DefaultEdge> graph;
    private final PositionApproximator approximator;
    private final DirectionSelector selectDir;
    private State state = State.NOT_SELECTED;
    private GameObject selected;
    private enum State {
        SELECTED, NOT_SELECTED
    }

    /**
     * Creates a new GraphDirectionSelector.
     * @param graph the graph of the gameMap
     */
    @SuppressFBWarnings("EI_EXPOSE_REP2")
    public GraphDirectionSelector(final Graph<GameObject, DefaultEdge> graph) {
        this.graph = Objects.requireNonNull(graph);
        this.approximator = new PositionApproximatorImpl();
        selectDir = new EuclideanDirectionSelector();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setDirection(final Character toMove, final GameObject target, final long elapsedTime) {
        Objects.requireNonNull(toMove);
        Objects.requireNonNull(target);

        final var sourceVertex = approximator.getApproximatedTarget(toMove, graph.vertexSet());
        if (!sourceVertex.isPresent()) {
            throw new IllegalArgumentException("The source is not in the graph");
        }

        final var targetVertex = approximator.getApproximatedTarget(target, graph.vertexSet());
        if (!targetVertex.isPresent()) {
            throw new IllegalArgumentException("The target is not in the graph");
        }

        final AStarShortestPath<GameObject, DefaultEdge> aStarAlg = new AStarShortestPath<>(this.graph, (s, t) -> {
            final double dx = s.getPosition().x - t.getPosition().x;
            final double dy = s.getPosition().y - t.getPosition().y;
            return Math.sqrt(dx * dx + dy * dy);
        });

        final SingleSourcePaths<GameObject, DefaultEdge> aPaths = aStarAlg.getPaths(sourceVertex.get());
        final var path = aPaths.getPath(targetVertex.get());
        if (path.getVertexList().size() >= 2) {

           if (state == State.SELECTED) {
                if (!approximator.isPositionCloseEnough(toMove, selected, 2.0)) {
                    selectDir.setDirection(toMove, selected, elapsedTime);
                } else {
                    toMove.setPosition(selected.getPosition()); 
                    state =  State.NOT_SELECTED;
                }
           } else if (state == State.NOT_SELECTED) {
                selected =  path.getVertexList().get(1);
                selectDir.setDirection(toMove, selected, elapsedTime);
                state = State.SELECTED;
           }
        } else {

            if (state == State.SELECTED) {
                if (!approximator.isPositionCloseEnough(toMove, selected, 2.0)) {
                    selectDir.setDirection(toMove, selected, elapsedTime);
                } else {
                   toMove.setPosition(selected.getPosition()); 
                    state =  State.NOT_SELECTED;
                }
            } else {
                 selectDir.setDirection(toMove, target, elapsedTime);
            }
        }

    }
}
