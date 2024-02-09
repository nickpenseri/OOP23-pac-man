package it.unibo.model.ghost.impl;

import java.util.Objects;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.model.api.GameObject;
import it.unibo.model.ghost.api.Ghost;
import it.unibo.model.ghost.api.GhostBehaviour;
import it.unibo.model.physics.objectsmover.api.DirectionSelector;
import it.unibo.model.physics.objectsmover.api.PositionApproximator;
import it.unibo.model.physics.objectsmover.impl.PositionApproximatorImpl;
import it.unibo.model.physics.timer.api.Timer;
import it.unibo.model.physics.timer.impl.TimerImpl;

/**
 * This class models a behaviour of a ghost.
 */
public class GhostBehaviourImpl implements GhostBehaviour {

    private final DirectionSelector directionSelector;
    private final GameObject normalTarget;
    private final GameObject deadTarget;
    private final GameObject scaredTarget;
    private final PositionApproximator approximator = new PositionApproximatorImpl();
    private final Timer timer = new TimerImpl(9000);

    /**
     * Create a new behaviour.
     * 
     * @param directionSelector the direction selector
     * @param normalTarget      the normal target
     * @param deadTarget        the dead target
     * @param scaredTarget      the scared target
     */
    @SuppressFBWarnings(value = {
            "EI_EXPOSE_REP2"
    }, justification = "Is ok to use an external directionSelector, it can be changed at runtime.")
    public GhostBehaviourImpl(final DirectionSelector directionSelector, final GameObject normalTarget,
            final GameObject deadTarget, final GameObject scaredTarget) {
        this.directionSelector = Objects.requireNonNull(directionSelector);
        this.normalTarget = Objects.requireNonNull(normalTarget);
        this.deadTarget = Objects.requireNonNull(deadTarget);
        this.scaredTarget = Objects.requireNonNull(scaredTarget);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean normalBehaviour(final Ghost character, final long elapsed) {
        directionSelector.setDirection(character, normalTarget, elapsed);
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean deadBehaviour(final Ghost character, final long elapsed) {
        directionSelector.setDirection(character, deadTarget, elapsed);
        return approximator.isPositionCloseEnough(character, deadTarget, 2.0);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean scaredBehaviour(final Ghost character, final long elapsed) {
        directionSelector.setDirection(character, scaredTarget, elapsed);
        if (timer.update(elapsed)) {
            timer.reset();
            return true;
        }
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void resetBehaviour() {
        directionSelector.reset();
    }
}
