package it.unibo.model.ghost.impl.GhostBehaviour;

import java.util.List;
import java.util.Objects;
import java.util.Random;

import it.unibo.model.api.GameObject;
import it.unibo.model.ghost.api.Ghost;
import it.unibo.model.ghost.api.GhostCoordinates;
import it.unibo.model.ghost.api.GhostState;
import it.unibo.model.physics.objectsmover.api.DirectionSelector;
import it.unibo.model.physics.objectsmover.api.PositionApproximator;
import it.unibo.model.physics.objectsmover.impl.PositionApproximatorImpl;
import it.unibo.model.physics.timer.api.Timer;
import it.unibo.model.physics.timer.impl.TimerImpl;

public class NormalGhost extends FollowingGhostImpl{

    private GameObject deadTargetSelected;
    private final DirectionSelector directionSelector;
    private final PositionApproximator approximator = new PositionApproximatorImpl();

    private final Timer timer = new TimerImpl(100_00);
    private static final int SPEED_INCREASE = 20;
    private boolean interlock;


    public NormalGhost(final Ghost ghost, final GhostCoordinates behaviour) {
        super(ghost,behaviour);
        this.directionSelector = Objects.requireNonNull(behaviour.getDirectionSelector());
      
    }


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

        if (approximator.isPositionCloseEnough(this , deadTargetSelected, 2.0)) {
            super.setState(GhostState.NORMAL);
            for (int i = 0; i < SPEED_INCREASE; i++) {
                super.decreaseSpeed();
            }
            interlock = false;
        }
    }

    @Override
    protected void scaredBehaviour(final long elapsed) {
        this.directionSelector.setDirection(super.getGhost(), super.getBehaviour().getDeadTarget(), elapsed);
        if (timer.update(elapsed)) {
            timer.reset();
            super.setState(GhostState.NORMAL);
        }
    }

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
