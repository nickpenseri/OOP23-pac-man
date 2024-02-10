package it.unibo.model.ghost.impl.GhostBehaviour;

import it.unibo.model.ghost.api.Ghost;
import it.unibo.model.physics.objectsmover.api.DirectionSelector;
import it.unibo.model.physics.objectsmover.api.PositionApproximator;
import it.unibo.model.physics.objectsmover.impl.PositionApproximatorImpl;
import it.unibo.model.physics.timer.api.Timer;
import it.unibo.model.physics.timer.impl.TimerImpl;

public class NormalGhost extends FollowingGhost{


    private final PositionApproximator approximator = new PositionApproximatorImpl();
    private final Timer timer = new TimerImpl(100_00);
    public NormalGhost(final Ghost ghost, final DirectionSelector directionSelector) {
        super(ghost,directionSelector);
    }
    @Override
    protected void deadBehaviour(Ghost character, long elapsed) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deadBehaviour'");
    }

    @Override
    protected void scaredBehaviour(Ghost character, long elapsed) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'scaredBehaviour'");
    }

    @Override
    protected void normalBehaviour(Ghost character, long elapsed) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'normalBehaviour'");
    }

    
}
