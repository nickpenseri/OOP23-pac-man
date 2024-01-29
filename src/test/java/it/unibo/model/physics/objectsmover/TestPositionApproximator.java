package it.unibo.model.physics.objectsmover;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.awt.Dimension;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.model.api.GameObject;
import it.unibo.model.api.GameObjectFactory;
import it.unibo.model.impl.GameObjectFactoryImpl;
import it.unibo.model.impl.GameObjectImpl;
import it.unibo.model.physics.objectsmover.api.PositionApproximator;
import it.unibo.model.physics.objectsmover.impl.PositionApproximatorImpl;


/**
 * This class is used to test the behaviour of PositionApproximatorImpl.
 */
class TestPositionApproximator {
   
    //private PositionApproximator approximator;
    private GameObjectFactory factory;

    /** Configuration method */
    @BeforeEach
    void test() {
       //approximator = new PositionApproximatorImpl();
       factory = new GameObjectFactoryImpl();
    }


    @Test
    void EmptyTest() {
        assertTrue(new Dimension(10, 10).equals(factory.createGameObjectWithEmptyGraphics(new Point(0, 0), new Dimension(10, 10), GameObjectImpl.Type.PASSABLE).getDimension()));
     }
}
