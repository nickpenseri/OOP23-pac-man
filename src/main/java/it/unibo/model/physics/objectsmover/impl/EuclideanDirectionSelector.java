package it.unibo.model.physics.objectsmover.impl;


import java.awt.Point;

import it.unibo.model.api.Character;
import it.unibo.model.api.Direction;
import it.unibo.model.api.GameObject;
import it.unibo.model.physics.objectsmover.api.DirectionSelector;
 
 /**
  * Implementation of the interface DirectionSelector, sets the direction of a character to move to reach a target.
  */
  public class EuclideanDirectionSelector implements DirectionSelector {

    /**
     * {@inheritDoc}
     * the longhest distance has always the priority.
     */
    @Override
    public void setDirection(final Character toMove, final GameObject target, final long elapsedTime) {
        final int diffX = target.getPosition().x - toMove.getPosition().x;
        final int diffY = target.getPosition().y - toMove.getPosition().y;

        if (isPositionCloseEnough(toMove.getPosition(), target.getPosition(), 2)) {
           toMove.setPosition(target.getPosition()); 
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

    private boolean isPositionCloseEnough(Point pos1, Point pos2, double tolerance) {
        double distance = Math.sqrt(Math.pow(pos1.x - pos2.x, 2) + Math.pow(pos1.y - pos2.y, 2));
        return distance <= tolerance;
    }
}
 
