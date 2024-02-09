package it.unibo.model.ghost.api;

/** GhostBehaviour interface. */
public interface GhostBehaviour {
    /**
     * Method that defines the normal behaviour of the ghost.
     * @param ghost the ghost
     * @param elapsed the time elapsed
     * @return true if the ghost has to change state
     */
    boolean normalBehaviour(Ghost ghost, long elapsed);
    /**
     * Method that defines the dead behaviour of the ghost.
     * @param ghost the ghost
     * @param elapsed the time elapsed
     * @return true if the ghost has to change state
     */
    boolean deadBehaviour(Ghost ghost, long elapsed);
    /**
     * Method that defines the scared behaviour of the ghost.
     * @param ghost the ghost
     * @param elapsed the time elapsed
     * @return true if the ghost has to change state
     */
    boolean scaredBehaviour(Ghost ghost, long elapsed);

    /**
     * Method that resets the behaviour of the ghost.
     */
    void resetBehaviour();
}
