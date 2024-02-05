package it.unibo.model.ghost.api;

/** States of a ghost. */
public enum GhostState {

    /**
     * The ghost is in its normal state, it can move and kill the pacman.
     */
    NORMAL,
    /**
     * The ghost is dead, it can't move and it can't kill the pacman.
     */
    DEAD,
    /**
     * The ghost is scared, it can move and it can't kill the pacman.
     */
    SCARED
}
