package it.unibo.model.ghost.api;

import it.unibo.model.api.GameObject;
import it.unibo.model.physics.objectsmover.api.DirectionSelector;

public interface GhostBehaviour {
    DirectionSelector getDirectionSelector();
    GameObject getNormalTarget();
    GameObject getDeadTarget();
    GameObject getScatedTarget();
    void resetBehaviour();
}
