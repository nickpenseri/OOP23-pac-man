package it.unibo.model.ghost.impl;

import java.awt.Point;
import java.awt.geom.Dimension2D;
import java.net.URL;
import java.util.Objects;
import java.util.Optional;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.model.api.Direction;
import it.unibo.model.ghost.api.Ghost;
import it.unibo.model.ghost.api.GhostBehaviour;
import it.unibo.model.ghost.api.GhostState;

/**
 * This class models a ghost that follows a specific behaviour.
 */
public class FollowingGhost implements Ghost {

    private final Ghost ghost;
    private GhostState state = GhostState.NORMAL;
    private boolean interlock;
    private final GhostBehaviour behaviour;
    private static final int SPEED_INCREASE = 20;

    /**
     * Create a new following ghost.
     * @param ghost the ghost to follow
     * @param behaviour the behaviour of the ghost
     */
    @SuppressFBWarnings(value = {
            "EI_EXPOSE_REP2"
    }, justification = "Changings of the decorated object should also affect this object")
    public FollowingGhost(final Ghost ghost, final GhostBehaviour behaviour) {
        this.ghost = ghost;
        this.behaviour = Objects.requireNonNull(behaviour);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getSpeedLevel() {
        return ghost.getSpeedLevel();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setState(final GhostState state) {
      this.state = state;
      ghost.setState(this.state);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GhostState getState() {
        return ghost.getState();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateState(final long elapsed) {
        ghost.setState(this.state);
        switch (state) {
            case NORMAL:
                behaviour.normalBehaviour(ghost, elapsed);
                break;
            case DEAD:
            if (!interlock) {
                for (int i = 0; i < SPEED_INCREASE; i++) {
                    ghost.increaseSpeed();
                }
                interlock = true;
            }
                if (behaviour.deadBehaviour(ghost, elapsed)) {
                    setState(GhostState.NORMAL);
                    for (int i = 0; i < SPEED_INCREASE; i++) {
                        ghost.decreaseSpeed();
                    }
                    interlock = false;
                }

                break;
            case SCARED:
                if (behaviour.scaredBehaviour(ghost, elapsed)) {
                    setState(GhostState.NORMAL);
                }
                break;
            default:
                break;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public URL getImageUrl() {
        return ghost.getImageUrl();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean increaseSpeed() {
        return ghost.increaseSpeed();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean decreaseSpeed() {
        return ghost.decreaseSpeed();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setDirection(final Direction direction) {
        ghost.setDirection(direction);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setPosition(final Point position) {
        setState(GhostState.NORMAL);
        ghost.setPosition(position);
        behaviour.resetBehaviour();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Direction> getDirection() {
        return ghost.getDirection();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void resetDirection() {
        ghost.resetDirection();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Point getPosition() {
        return ghost.getPosition();
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public Dimension2D getDimension() {
        return ghost.getDimension();
    }
}
