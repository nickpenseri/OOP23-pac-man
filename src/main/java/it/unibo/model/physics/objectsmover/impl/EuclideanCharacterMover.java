package it.unibo.model.physics.objectsmover.impl;

import it.unibo.model.api.Character;
import it.unibo.model.api.Direction;
import it.unibo.model.api.GameObject;
import it.unibo.model.physics.objectsmover.api.CharacterMover;
import it.unibo.model.physics.objectsmover.api.PositionApproximator;
 
 /**
  * Implementation of the interface CharacterMover, move a character to reach a target.
  */
  public class EuclideanCharacterMover implements CharacterMover {

    private final PositionApproximator approximator = new PositionApproximatorImpl();
    /**
     * {@inheritDoc}
     * the longhest distance has always the priority.
     */
    @Override
    public void moveCharacter(final Character toMove, final GameObject target, final long elapsedTime) {
        final int diffX = target.getPosition().x - toMove.getPosition().x;
        final int diffY = target.getPosition().y - toMove.getPosition().y;

        if (approximator.isPositionCloseEnough(toMove, target, 0.0)) {
           toMove.resetDirection();
        } else {
            if (Math.abs(diffX) >= Math.abs(diffY)) {
                if (diffX > 0) {
                    toMove.setDirection(Direction.RIGHT);
                } else {
                    toMove.setDirection(Direction.LEFT);
                }
            } else {
                if (diffY > 0) {
                    toMove.setDirection(Direction.UP);
                } else {
                    toMove.setDirection(Direction.DOWN);
                } 
            }
        }
        toMove.updateState(elapsedTime);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void reset() { }
}
 
