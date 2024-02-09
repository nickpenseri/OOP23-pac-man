package it.unibo.model.ghost.impl;

import java.util.Objects;

import it.unibo.model.api.GameObject;
import it.unibo.model.ghost.api.GhostBehaviour;
import it.unibo.model.physics.objectsmover.api.DirectionSelector;
import it.unibo.model.api.Character;

public class GhostBehaviuorImpl implements GhostBehaviour{

    final private DirectionSelector directionSelector;
    final private GameObject normalTarget;
    final private GameObject deadTarget;
    final private GameObject scaredTarget;

    public GhostBehaviuorImpl(DirectionSelector directionSelector, GameObject normalTarget, GameObject deadTarget, GameObject scaredTarget) {
        this.directionSelector = Objects.requireNonNull(directionSelector);
        this.normalTarget = Objects.requireNonNull(normalTarget);
        this.deadTarget = Objects.requireNonNull(deadTarget);
        this.scaredTarget = Objects.requireNonNull(scaredTarget);
    }
    @Override
    public void normalBehaviour(final Character character, final long elapsed) {
        directionSelector.setDirection(character, normalTarget, elapsed);
    }

    @Override
    public void deadBehaviour(final Character character, final long elapsed) {
        directionSelector.setDirection(character, deadTarget, elapsed);
    }

    @Override
    public void scaredBehaviour(final Character character, final long elapsed) {
        directionSelector.setDirection(character, scaredTarget, elapsed);
    }
    
}
