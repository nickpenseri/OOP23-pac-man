package it.unibo.model.ghost.impl.GhostBehaviour;

import java.awt.Point;
import java.awt.geom.Dimension2D;
import java.net.URL;
import java.util.Objects;
import java.util.Optional;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.model.api.Direction;
import it.unibo.model.ghost.api.Ghost;
import it.unibo.model.ghost.api.GhostState;
import it.unibo.model.physics.objectsmover.api.DirectionSelector;

/**
 * This class models a ghost that follows a specific behaviour.
 */
public abstract class FollowingGhost implements Ghost {
    
    private final DirectionSelector directionSelector;
    private final Ghost ghost;
    /**
     * Create a new following ghost.
     * @param ghost the ghost to follow
     * @param behaviour the behaviour of the ghost
     */
    @SuppressFBWarnings(value = {
            "EI_EXPOSE_REP2"
    }, justification = "Changings of the decorated object should also affect this object")
    public FollowingGhost(final Ghost ghost, final DirectionSelector directionSelector) {
        this.directionSelector = Objects.requireNonNull(directionSelector);
        this.ghost = ghost;
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
      ghost.setState(state);
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
        if (this.ghost.getState() == GhostState.NORMAL) {
            normalBehaviour(this.ghost, elapsed);
        } else if (this.ghost.getState() == GhostState.DEAD) {
            deadBehaviour(this.ghost, elapsed);
        } else if (this.ghost.getState() == GhostState.SCARED) {
            scaredBehaviour(this.ghost, elapsed);
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

    protected DirectionSelector getDirectionSelector() {
        return directionSelector;
    }
    
    protected abstract void deadBehaviour(final Ghost character, final long elapsed);
    protected abstract void scaredBehaviour(final Ghost character, final long elapsed);
    protected abstract void normalBehaviour(final Ghost character, final long elapsed);
    
}
