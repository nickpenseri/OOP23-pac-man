package it.unibo.model.ghost.impl;

import java.util.Objects;

import it.unibo.model.api.GameObject;
import it.unibo.model.ghost.api.Ghost;
import it.unibo.model.ghost.api.GhostBehaviour;
import it.unibo.model.ghost.api.GhostState;
import it.unibo.model.physics.objectsmover.api.DirectionSelector;
import it.unibo.model.physics.objectsmover.api.PositionApproximator;
import it.unibo.model.physics.objectsmover.impl.PositionApproximatorImpl;
import it.unibo.model.physics.timer.api.Timer;
import it.unibo.model.physics.timer.impl.TimerImpl;

public class GhostBehaviourImpl implements GhostBehaviour{

    final private DirectionSelector directionSelector;
    final private GameObject normalTarget;
    final private GameObject deadTarget;
    final private GameObject scaredTarget;
    final private PositionApproximator approximator = new PositionApproximatorImpl();
    final private Timer timer = new TimerImpl(10000);

    public GhostBehaviourImpl(DirectionSelector directionSelector, GameObject normalTarget, GameObject deadTarget, GameObject scaredTarget) {
        this.directionSelector = Objects.requireNonNull(directionSelector);
        this.normalTarget = Objects.requireNonNull(normalTarget);
        this.deadTarget = Objects.requireNonNull(deadTarget);
        this.scaredTarget = Objects.requireNonNull(scaredTarget);
    }
    @Override
    public boolean normalBehaviour(final Ghost character, final long elapsed) {
        directionSelector.setDirection(character, normalTarget, elapsed);
        return false;
    }

    @Override
    public boolean deadBehaviour(final Ghost character, final long elapsed) {
        directionSelector.setDirection(character, deadTarget, elapsed);
        if (approximator.isPositionCloseEnough(character, deadTarget, 2.0)) {
           return true;
        }
        return false;
    }

    @Override
    public boolean scaredBehaviour(final Ghost character, final long elapsed) {
        directionSelector.setDirection(character, scaredTarget, elapsed);
        if (timer.update(elapsed)) {
            timer.reset();
            return true;
        }
        return false;
    }
    
}
