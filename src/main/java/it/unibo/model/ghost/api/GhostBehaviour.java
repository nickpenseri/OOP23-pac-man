package it.unibo.model.ghost.api;

import it.unibo.model.api.Character;
public interface GhostBehaviour {
    
    void normalBehaviour(Character character, long elapsed);
    void deadBehaviour(Character character, long elapsed);
    void scaredBehaviour(Character character, long elapsed);
}
