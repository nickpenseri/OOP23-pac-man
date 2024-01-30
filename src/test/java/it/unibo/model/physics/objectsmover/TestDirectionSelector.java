package it.unibo.model.physics.objectsmover;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import org.junit.jupiter.api.Test;

import it.unibo.model.api.Direction;
import it.unibo.model.api.GameObjectFactory;
import it.unibo.model.impl.GameObjectFactoryImpl;
import it.unibo.model.physics.objectsmover.api.DirectionSelector;
import it.unibo.model.physics.objectsmover.impl.DirectionSelectorImpl;



public class TestDirectionSelector {
    
    private final DirectionSelector selector = new DirectionSelectorImpl();
    private final GameObjectFactory factory  = new GameObjectFactoryImpl();
}
