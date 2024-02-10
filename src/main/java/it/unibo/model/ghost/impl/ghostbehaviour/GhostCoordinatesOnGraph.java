package it.unibo.model.ghost.impl.ghostbehaviour;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Random;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;

import it.unibo.model.api.GameObject;
import it.unibo.model.ghost.api.ghostBehaviour.GhostCoordinates;
import it.unibo.model.physics.objectsmover.api.DirectionSelector;
import it.unibo.model.physics.objectsmover.impl.GraphDirectionSelector;

/**
 * This class models the coordinates of a ghost on a graph.
 */
public class GhostCoordinatesOnGraph implements GhostCoordinates {

    private final Optional<DirectionSelector> directionSelector;
    private final GameObject normalTarget;
    private final List<GameObject> deadTargets;
    private final List<GameObject> gameVertex;
    private final Random random = new Random();

    /**
     * Create a new ghost coordinates on a graph.
     * 
     * @param graph             the graph of the game
     * @param normalTarget      the normal target of the ghost
     * @param deadTargets       the dead targets of the ghost
     */
    public GhostCoordinatesOnGraph(final Optional<Graph<GameObject, DefaultEdge>> graph, final GameObject normalTarget,
            final List<GameObject> deadTargets) {
        if (graph.isEmpty()) {
            throw new IllegalArgumentException("The graph is empty");
        }
        this.directionSelector = Optional.of(new GraphDirectionSelector(graph.get()));
        this.normalTarget = Objects.requireNonNull(normalTarget);
        this.deadTargets = new ArrayList<>(Objects.requireNonNull(deadTargets));
        this.gameVertex = new ArrayList<>(Objects.requireNonNull(graph.get().vertexSet()));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<DirectionSelector> getDirectionSelector() {
        return this.directionSelector;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GameObject getNormalTarget() {
        return this.normalTarget;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GameObject getDeadTarget() {
        return deadTargets.get(random.nextInt(0, deadTargets.size()));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GameObject getRandomTarget() {
        return gameVertex.get(random.nextInt(0, gameVertex.size()));
    }
}
