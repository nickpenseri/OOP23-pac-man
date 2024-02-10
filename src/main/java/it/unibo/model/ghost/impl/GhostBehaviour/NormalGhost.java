package it.unibo.model.ghost.impl.GhostBehaviour;


import java.util.Objects;
import it.unibo.model.api.GameObject;
import it.unibo.model.ghost.api.Ghost;
import it.unibo.model.ghost.api.GhostCoordinates;
import it.unibo.model.ghost.api.GhostState;
import it.unibo.model.physics.objectsmover.api.DirectionSelector;
import it.unibo.model.physics.objectsmover.api.PositionApproximator;
import it.unibo.model.physics.objectsmover.impl.PositionApproximatorImpl;
import it.unibo.model.physics.timer.api.Timer;
import it.unibo.model.physics.timer.impl.TimerImpl;

/**
 * This class models a normal ghost, the mapCoordinates is like in the normal game.
 */
public class NormalGhost extends FollowingGhostImpl {

    private GameObject deadTargetSelected;
    private final DirectionSelector directionSelector;
    private final PositionApproximator approximator = new PositionApproximatorImpl();
    private final Timer timer = new TimerImpl(100_00);
    private static final int SPEED_INCREASE = 20;
    private boolean interlock;


    /**
     * Create a new normal ghost.
     * @param ghost the ghost to follow
     * @param mapCoordinates the coordinates of the map for the ghost
     */
    public NormalGhost(final Ghost ghost, final GhostCoordinates mapCoordinates) {
        super(ghost, mapCoordinates);
        this.directionSelector = Objects.requireNonNull(mapCoordinates.getDirectionSelector());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void deadBehaviour(final long elapsed) {
       timer.reset();
       if (!interlock) {
            for (int i = 0; i < SPEED_INCREASE; i++) {
                super.increaseSpeed();
            }
            deadTargetSelected = super.getBehaviour().getDeadTarget();
            interlock = true;
        }

        this.directionSelector.setDirection(super.getGhost(), deadTargetSelected, elapsed);

        if (approximator.isPositionCloseEnough(this, deadTargetSelected, 2.0)) {
            super.setState(GhostState.NORMAL);
            for (int i = 0; i < SPEED_INCREASE; i++) {
                super.decreaseSpeed();
            }
            interlock = false;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void scaredBehaviour(final long elapsed) {
        this.directionSelector.setDirection(super.getGhost(), super.getBehaviour().getDeadTarget(), elapsed);
        if (timer.update(elapsed)) {
            timer.reset();
            super.setState(GhostState.NORMAL);
        }
    }

        /**
     * {@inheritDoc}
     */
    @Override
    protected void normalBehaviour(final long elapsed) {
        timer.reset();
        this.directionSelector.setDirection(super.getGhost(),  super.getBehaviour().getNormalTarget(), elapsed);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void resetBehaviour() {
        timer.reset();
        this.directionSelector.reset();
    }
}
