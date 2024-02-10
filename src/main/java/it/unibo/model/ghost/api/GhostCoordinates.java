package it.unibo.model.ghost.api;

import java.util.List;

import it.unibo.model.api.GameObject;
import it.unibo.model.physics.objectsmover.api.DirectionSelector;

public interface GhostCoordinates {
    DirectionSelector getDirectionSelector();
    GameObject getNormalTarget();
    GameObject getDeadTarget();
    List<GameObject> getScaredTarget();
    void resetBehaviour();
}
