package it.unibo.model.ghost.impl.ghostbehaviour;


import java.util.Objects;
import it.unibo.model.api.GameObject;
import it.unibo.model.ghost.api.Ghost;
import it.unibo.model.ghost.api.GhostState;
import it.unibo.model.ghost.api.ghostbehaviour.GhostCoordinates;
import it.unibo.model.physics.objectsmover.api.CharacterMover;
import it.unibo.model.physics.objectsmover.api.PositionApproximator;
import it.unibo.model.physics.objectsmover.impl.PositionApproximatorImpl;
import it.unibo.model.physics.timer.api.Timer;
import it.unibo.model.physics.timer.impl.ClockTimer;
import it.unibo.model.physics.timer.impl.TimerImpl;

/**
 * This class models a normal ghost, the mapCoordinates is like in the normal game.
 */
public class AggressiveGhost extends FollowingGhostImpl {

    private final GameObject deadTargetSelected;
    private final CharacterMover directionSelector;
    private final PositionApproximator approximator = new PositionApproximatorImpl();
    private final Timer scaredTimer = new TimerImpl(9999);
    private final Timer randomTimer = new ClockTimer(9999);
    /**
     * Create a new normal ghost.
     * @param ghost the ghost to follow
     * @param mapCoordinates the coordinates of the map for the ghost
     */
    public AggressiveGhost(final Ghost ghost, final GhostCoordinates mapCoordinates) {
        super(ghost, mapCoordinates);
        this.directionSelector = Objects.requireNonNull(mapCoordinates.getCharacterMover().get());
        deadTargetSelected = super.getBehaviour().getDeadTarget();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void scaredBehaviour(final long elapsed) {
        randomTimer.reset();
        this.directionSelector.moveCharacter(super.getGhost(), super.getBehaviour().getDeadTarget(), elapsed);
        if (scaredTimer.update(elapsed)) {
            scaredTimer.reset();
            super.setState(GhostState.NORMAL);
        }
    }


    /**
     * {@inheritDoc}
     */
    @Override
    protected void deadBehaviour(final long elapsed) {
        scaredTimer.reset();
        randomTimer.reset();
        this.directionSelector.moveCharacter(super.getGhost(), deadTargetSelected, elapsed);
        if (approximator.isPositionCloseEnough(this, deadTargetSelected, 4.0)) {
            super.setState(GhostState.NORMAL);
        }
    }

        /**
     * {@inheritDoc}
     */
    @Override
    protected void normalBehaviour(final long elapsed) {
        scaredTimer.reset();
        this.directionSelector.moveCharacter(super.getGhost(), super.getBehaviour().getNormalTarget(), elapsed);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void resetBehaviour() {
        scaredTimer.reset();
        randomTimer.reset();
        this.directionSelector.reset();
    }
}
