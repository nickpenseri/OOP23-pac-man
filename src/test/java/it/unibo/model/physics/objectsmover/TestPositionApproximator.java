package it.unibo.model.physics.objectsmover;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.awt.Dimension;
import java.awt.Point;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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
   
    private PositionApproximator approximator;
    private GameObjectFactory factory;

    /** Configuration method */
    @BeforeEach
    void test() {
       approximator = new PositionApproximatorImpl();
       factory = new GameObjectFactoryImpl();
    }


      /**
      * Test the behaviour of getApproximatedPosition, test if is always near to something.
      */
    @Test
    void EmptyTest() {
        var target = factory.createGameObjectWithEmptyGraphics(new Point(10, 10), new Dimension(10, 10), GameObjectImpl.Type.PASSABLE);
        List<GameObject> list = IntStream.range(0, 10)
        .mapToObj(i -> factory.createGameObjectWithEmptyGraphics(new Point(i * 10, i * 10), new Dimension(10, 10), GameObjectImpl.Type.PASSABLE))
        .collect(Collectors.toList());
        
        assertFalse(list.get(0).equals(approximator.getApproximatedPosition(target, list).get()));
        assertTrue(list.get(1).equals(approximator.getApproximatedPosition(target, list).get()));

        target = factory.createGameObjectWithEmptyGraphics(new Point(30, 30), new Dimension(10, 10), GameObjectImpl.Type.PASSABLE);
        assertFalse(list.get(4).equals(approximator.getApproximatedPosition(target, list).get()));
        assertTrue(list.get(3).equals(approximator.getApproximatedPosition(target, list).get()));

        target = factory.createGameObjectWithEmptyGraphics(new Point(200, 200), new Dimension(10, 10), GameObjectImpl.Type.PASSABLE);
        assertFalse(approximator.getApproximatedPosition(target, list).isEmpty());

     }
}
