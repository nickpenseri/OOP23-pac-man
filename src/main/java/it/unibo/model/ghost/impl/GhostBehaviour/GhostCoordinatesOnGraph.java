package it.unibo.model.ghost.impl.GhostBehaviour;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

import it.unibo.model.api.GameObject;
import it.unibo.model.ghost.api.GhostCoordinates;
import it.unibo.model.physics.objectsmover.api.DirectionSelector;

public class GhostCoordinatesOnGraph implements GhostCoordinates {

    private final DirectionSelector directionSelector;
    private final GameObject normalTarget;
    private final List<GameObject> deadTargets;
    private final List<GameObject> GameVertex;
    private final Random random = new Random();

    public GhostCoordinatesOnGraph( final DirectionSelector directionSelector, final GameObject normalTarget, final List<GameObject> deadTargets, final  List<GameObject> GameVertex) {
        this.directionSelector = Objects.requireNonNull(directionSelector);
        this.normalTarget = Objects.requireNonNull(normalTarget);
        this.deadTargets = Objects.requireNonNull(deadTargets);
        this.GameVertex = Objects.requireNonNull(GameVertex);
    }
    @Override
    public DirectionSelector getDirectionSelector() {
        return this.directionSelector;
    }

    @Override
    public GameObject getNormalTarget() {
        return this.normalTarget;
    }

    @Override
    public GameObject getDeadTarget() {
        return deadTargets.get(random.nextInt(0,deadTargets.size()));
    }

    @Override
    public List<GameObject> getScaredTarget() {
       return new ArrayList<GameObject>(GameVertex);
    }
}
