package it.unibo.model.ghost.api;

public interface GhostBehaviour {
    
    boolean normalBehaviour(Ghost character, long elapsed);
    boolean deadBehaviour(Ghost character, long elapsed);
    boolean scaredBehaviour(Ghost character, long elapsed);
}
