package it.unibo.model.physics.objectsmover;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.awt.Dimension;
import java.awt.Point;

import org.junit.jupiter.api.Test;


import it.unibo.model.api.GameObjectFactory;
import it.unibo.model.ghost.impl.GhostImpl;
import it.unibo.model.impl.GameObjectFactoryImpl;
import it.unibo.model.physics.objectsmover.api.DirectionSelector;
import it.unibo.model.physics.objectsmover.impl.DirectionSelectorImpl;
import it.unibo.model.api.Character;
import it.unibo.model.api.GameObject;



public class TestDirectionSelector {
    
    private static final int GAME_OBJ_SIZE = 10;
    private static final int TARGET_INIT_POSITION = 10;

    private final Dimension dim = new Dimension(GAME_OBJ_SIZE, GAME_OBJ_SIZE);
    private final DirectionSelector selector = new DirectionSelectorImpl();
    private final GameObjectFactory factory  = new GameObjectFactoryImpl();

    @Test
    void upDirection(){
        Character ghost = new GhostImpl(new Point(TARGET_INIT_POSITION,TARGET_INIT_POSITION), dim, 1);
        GameObject target = factory.createGameObjectWithEmptyGraphics(new Point(TARGET_INIT_POSITION,TARGET_INIT_POSITION + 1), dim);
    }
}
