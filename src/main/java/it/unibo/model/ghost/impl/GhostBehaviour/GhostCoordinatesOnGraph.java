package it.unibo.model.ghost.impl.ghostBehaviour;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.model.api.GameObject;
import it.unibo.model.ghost.api.GhostCoordinates;
import it.unibo.model.physics.objectsmover.api.DirectionSelector;

/**
 * This class models the coordinates of a ghost on a graph.
 */
public class GhostCoordinatesOnGraph implements GhostCoordinates {

    private final DirectionSelector directionSelector;
    private final GameObject normalTarget;
    private final List<GameObject> deadTargets;
    private final List<GameObject> gameVertex;
    private final Random random = new Random();

    /**
     * Create a new ghost coordinates on a graph.
     * 
     * @param directionSelector the direction selector of the ghost
     * @param normalTarget      the normal target of the ghost
     * @param deadTargets       the dead targets of the ghost
     * @param gameVertex        the vertex of the game
     */
     @SuppressFBWarnings(value = {
            "EI_EXPOSE_REP2"
    }, justification = "Is ok to expose the object, i don't need a copy and is ok if they are changed from outside")
    public GhostCoordinatesOnGraph(final DirectionSelector directionSelector, final GameObject normalTarget, 
            final List<GameObject> deadTargets, final  List<GameObject> gameVertex) {
        this.directionSelector = Objects.requireNonNull(directionSelector);
        this.normalTarget = Objects.requireNonNull(normalTarget);
        this.deadTargets = Objects.requireNonNull(deadTargets);
        this.gameVertex = Objects.requireNonNull(gameVertex);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public DirectionSelector getDirectionSelector() {
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
    public List<GameObject> getScaredTarget() {
       return new ArrayList<GameObject>(gameVertex);
    }
}
