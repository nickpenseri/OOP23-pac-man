package it.unibo.model.ghost.impl;

import java.awt.Point;
import java.awt.geom.Dimension2D;
import java.net.URL;
import java.util.Optional;

import it.unibo.model.api.Direction;
import it.unibo.model.api.GameObject;
import it.unibo.model.ghost.api.Ghost;
import it.unibo.model.ghost.api.GhostState;
import it.unibo.model.physics.objectsmover.api.DirectionSelector;

public class FollowingGhost implements Ghost{

    final private Ghost ghost;
    final private DirectionSelector directionSelector;
    final private GameObject target;
    private GhostState state = GhostState.NORMAL;

    public FollowingGhost(Ghost ghost, DirectionSelector directionSelector, GameObject target) {
        this.ghost = ghost;
        this.directionSelector = directionSelector;
        this.target = target;

    }

    @Override
    public int getSpeedLevel() {
        return ghost.getSpeedLevel();
    }

    @Override
    public void setState(GhostState state) {
      this.state = state;
    }   

    @Override
    public GhostState getState() {
        return ghost.getState();
    }

    @Override
    public void updateState(long elapsed) {
        ghost.setState(this.state);
        directionSelector.setDirection(ghost, target, elapsed);
    }

    @Override
    public URL getImageUrl() {
        return ghost.getImageUrl();
    }

    @Override
    public boolean increaseSpeed() {
        return ghost.increaseSpeed();
    }

    @Override
    public boolean decreaseSpeed() {
        return ghost.decreaseSpeed();
    }

    @Override
    public void setDirection(Direction direction) {
        ghost.setDirection(direction);
    }

    @Override
    public void setPosition(Point position) {
        ghost.setPosition(position);
    }

    @Override
    public Optional<Direction> getDirection() {
        return ghost.getDirection();
    }

    @Override
    public void resetDirection() {
        ghost.resetDirection();
    }


    @Override
    public Point getPosition() {
        return ghost.getPosition();
    }

    @Override
    public Dimension2D getDimension() {
        return ghost.getDimension();
    }
    
}
