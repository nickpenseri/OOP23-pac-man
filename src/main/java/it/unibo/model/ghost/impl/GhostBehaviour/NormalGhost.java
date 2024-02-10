package it.unibo.model.ghost.impl.GhostBehaviour;

import it.unibo.model.ghost.api.Ghost;
import it.unibo.model.ghost.api.GhostState;
import it.unibo.model.physics.objectsmover.api.DirectionSelector;
import it.unibo.model.physics.objectsmover.api.PositionApproximator;
import it.unibo.model.physics.objectsmover.impl.PositionApproximatorImpl;
import it.unibo.model.physics.timer.api.Timer;
import it.unibo.model.physics.timer.impl.TimerImpl;

public class NormalGhost extends FollowingGhost{

    private final PositionApproximator approximator = new PositionApproximatorImpl();
    private final Timer timer = new TimerImpl(100_00);
    private static final int SPEED_INCREASE = 20;
    private boolean interlock;


    public NormalGhost(final Ghost ghost, final DirectionSelector directionSelector) {
        super(ghost,directionSelector);
    }

    
    @Override
    protected void deadBehaviour(final Ghost character, final long elapsed) {
       timer.reset();
       super.getDirectionSelector().setDirection(super.getGhost(), deadTarget, elapsed);

       if (!interlock) {
            for (int i = 0; i < SPEED_INCREASE; i++) {
                super.increaseSpeed();
            }
            interlock = true;
        }

        if (approximator.isPositionCloseEnough(character, deadTarget, 2.0)) {
            super.setState(GhostState.NORMAL);
            for (int i = 0; i < SPEED_INCREASE; i++) {
                super.decreaseSpeed();
            }
            interlock = false;
        }
    }

    @Override
    protected void scaredBehaviour(final Ghost character, final long elapsed) {
        super.getDirectionSelector().setDirection(super.getGhost(), scaredTarget, elapsed);
        if (timer.update(elapsed)) {
            timer.reset();
            super.setState(GhostState.NORMAL);
        }
    }

    @Override
    protected void normalBehaviour(final Ghost character, final long elapsed) {
        timer.reset();
        super.getDirectionSelector().setDirection(super.getGhost(), normalTarget, elapsed);
    }

    
}
