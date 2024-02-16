package it.unibo.model.physics.objectsmover.impl;


import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.model.api.Character;
import it.unibo.model.api.GameObject;
import it.unibo.model.physics.objectsmover.api.CharacterMover;
import it.unibo.model.physics.objectsmover.api.PositionApproximator;

import org.jgrapht.Graph;
import org.jgrapht.alg.interfaces.ShortestPathAlgorithm.SingleSourcePaths;
import org.jgrapht.alg.shortestpath.AStarShortestPath;
import org.jgrapht.graph.DefaultEdge;

import java.util.Objects;
import java.util.Optional;


/**
 * Implementation of the interface CharacterMover, sets the direction of a
 * character to move to reach a target
 * moving on a graph.
 */
public class CharacterMoverOnGraph implements CharacterMover {

    private final Graph<GameObject, DefaultEdge> graph;
    private final AStarShortestPath<GameObject, DefaultEdge> aStarAlg;
    private final PositionApproximator approximator;
    private final CharacterMover selectDir;
    private State state = State.NOT_SELECTED;
    private GameObject selected;

    private enum State {
        SELECTED, NOT_SELECTED
    }

    /**
     * Creates a new GraphDirectionSelector.
     * 
     * @param graph the graph of the gameMap
     */
    @SuppressFBWarnings(value = {
        "EI_EXPOSE_REP2"
    }, justification = "Don't want to create a new object, don't use other memory and is ok if the graph is modified")
    public CharacterMoverOnGraph(final Graph<GameObject, DefaultEdge> graph) {
        this.graph = Objects.requireNonNull(graph);
        this.approximator = new PositionApproximatorImpl();
        selectDir = new EuclideanCharacterMover();
        aStarAlg = new AStarShortestPath<>(this.graph, approximator::getDistance);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void moveCharacter(final Character toMove, final GameObject target, final long elapsedTime) {
        final var sourceVertex = checkVertexPresence(Objects.requireNonNull(toMove));
        final var targetVertex = checkVertexPresence(Objects.requireNonNull(target));

        final SingleSourcePaths<GameObject, DefaultEdge> aPaths = aStarAlg.getPaths(sourceVertex.get());
        final var path = aPaths.getPath(targetVertex.get());

        if (path.getVertexList().size() >= 2) {
            if (state == State.NOT_SELECTED) {
                selected = path.getVertexList().get(1);
                selectDir.moveCharacter(toMove, selected, elapsedTime);
                state = State.SELECTED;
            } else {
                handleSelectedState(toMove, elapsedTime);
            }
        } else {
            if (state == State.NOT_SELECTED) {
                selectDir.moveCharacter(toMove, target, elapsedTime);
            } else {
                handleSelectedState(toMove, elapsedTime);
            }
        }
    }

        /**
     * {@inheritDoc}
     */
    @Override
    public void reset() {
        selectDir.reset();
        state = State.NOT_SELECTED;
    }

    private void handleSelectedState(final Character toMove, final long elapsedTime) {
        if (!approximator.isPositionCloseEnough(toMove, selected, 2.0)) {
            selectDir.moveCharacter(toMove, selected, elapsedTime);
        } else {
            toMove.setPosition(selected.getPosition());
            state = State.NOT_SELECTED;
        }
    }

    private Optional<GameObject> checkVertexPresence(final GameObject target) {
        final var targetVertex = approximator.getApproximatedTarget(target, graph.vertexSet());
        if (!targetVertex.isPresent()) {
            throw new IllegalArgumentException("The target is not in the graph");
        }
        return targetVertex;
    }
}
