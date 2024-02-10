package it.unibo.model.ghost.api;

/** Interface for a ghost that follow an object */
public interface FollowingGhost extends Ghost{



    /**
     * Reset the behaviour of the ghost, it will search for the target again.
     */
    void resetBehaviour();
}
