package it.unibo.model.ghost.impl.GhostBehaviour;

import java.util.List;
import java.util.Objects;

import it.unibo.model.api.GameObject;
import it.unibo.model.ghost.api.Ghost;
import it.unibo.model.ghost.api.GhostState;
import it.unibo.model.physics.objectsmover.api.DirectionSelector;
import it.unibo.model.physics.objectsmover.api.PositionApproximator;
import it.unibo.model.physics.objectsmover.impl.PositionApproximatorImpl;
import it.unibo.model.physics.timer.api.Timer;
import it.unibo.model.physics.timer.impl.TimerImpl;

public class NormalGhost extends FollowingGhost{

    private final GameObject normalTarget;
    private final List<GameObject> deadTarget;
    private final List<GameObject> GameVertex;
    private final PositionApproximator approximator = new PositionApproximatorImpl();
    private final Timer timer = new TimerImpl(100_00);
    private static final int SPEED_INCREASE = 20;
    private boolean interlock;


    public NormalGhost(final Ghost ghost, final DirectionSelector directionSelector, final GameObject normalTarget, final List<GameObject> deadTarget, final  List<GameObject> GameVertex) {
        super(ghost,directionSelector);
        this.normalTarget = Objects.requireNonNull(normalTarget);
        this.deadTarget = Objects.requireNonNull(deadTarget);
        this.GameVertex = Objects.requireNonNull(GameVertex);
    }


    @Override
    protected void deadBehaviour(final long elapsed) {
       timer.reset();
       super.getDirectionSelector().setDirection(super.getGhost(), deadTarget, elapsed);

       if (!interlock) {
            for (int i = 0; i < SPEED_INCREASE; i++) {
                super.increaseSpeed();
            }
            interlock = true;
        }

        if (approximator.isPositionCloseEnough( deadTarget, 2.0)) {
            super.setState(GhostState.NORMAL);
            for (int i = 0; i < SPEED_INCREASE; i++) {
                super.decreaseSpeed();
            }
            interlock = false;
        }
    }

    @Override
    protected void scaredBehaviour(final long elapsed) {
        super.getDirectionSelector().setDirection(super.getGhost(), scaredTarget, elapsed);
        if (timer.update(elapsed)) {
            timer.reset();
            super.setState(GhostState.NORMAL);
        }
    }

    @Override
    protected void normalBehaviour(final long elapsed) {
        timer.reset();
        super.getDirectionSelector().setDirection(super.getGhost(), normalTarget, elapsed);
    }

    
}
