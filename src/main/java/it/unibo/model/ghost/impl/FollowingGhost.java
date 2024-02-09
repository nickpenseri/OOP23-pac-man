package it.unibo.model.ghost.impl;

import java.awt.Point;
import java.awt.geom.Dimension2D;
import java.net.URL;
import java.util.Objects;
import java.util.Optional;

import it.unibo.model.api.Direction;
import it.unibo.model.ghost.api.Ghost;
import it.unibo.model.ghost.api.GhostBehaviour;
import it.unibo.model.ghost.api.GhostState;

public class FollowingGhost implements Ghost{

    final private Ghost ghost;
    private GhostState state = GhostState.NORMAL;
    final private GhostBehaviour behaviour;

    public FollowingGhost(Ghost ghost, GhostBehaviour behaviour) {
        this.ghost = ghost;
        this.behaviour = Objects.requireNonNull(behaviour);

    }

    @Override
    public int getSpeedLevel() {
        return ghost.getSpeedLevel();
    }

    @Override
    public void setState(GhostState state) {
      this.state = state;
      ghost.setState(this.state);
      System.out.println("State: " + state);
    }   

    @Override
    public GhostState getState() {
        return ghost.getState();
    }

    @Override
    public void updateState(long elapsed) {
        ghost.setState(this.state);
        switch (state) {
            case NORMAL:
                behaviour.normalBehaviour(ghost, elapsed);
                break;
            case DEAD:
                if (behaviour.deadBehaviour(ghost, elapsed)){
                    setState(GhostState.NORMAL);
                }

                break;
            case SCARED:
                if (behaviour.scaredBehaviour(ghost, elapsed)){
                    setState(GhostState.NORMAL);
                }
                break;
            default:
                break;
        }
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
