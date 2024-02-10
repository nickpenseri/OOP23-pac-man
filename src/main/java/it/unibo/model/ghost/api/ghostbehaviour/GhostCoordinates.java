package it.unibo.model.ghost.api.ghostbehaviour;


import java.util.Optional;
import it.unibo.model.api.GameObject;
import it.unibo.model.physics.objectsmover.api.DirectionSelector;


/**
 * This interface models the coordinates of a ghost on a map.
 */
public interface GhostCoordinates {

    /**
     * @return the direction selector of the ghost
     */
    Optional<DirectionSelector> getDirectionSelector();

    /**
     * @return the normal target of the ghost
     */
    GameObject getNormalTarget();

    /**
     * @return the dead target of the ghost
     */
    GameObject getDeadTarget();

    /**
     * @return a Random target for the ghost
     */
    GameObject getRandomTarget();
}
