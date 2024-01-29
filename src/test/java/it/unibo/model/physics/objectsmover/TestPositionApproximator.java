package it.unibo.model.physics.objectsmover;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

import java.awt.Dimension;
import java.awt.Point;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import it.unibo.model.api.GameObject;
import it.unibo.model.api.GameObjectFactory;
import it.unibo.model.impl.GameObjectFactoryImpl;
import it.unibo.model.physics.objectsmover.api.PositionApproximator;
import it.unibo.model.physics.objectsmover.impl.PositionApproximatorImpl;


/**
 * This class is used to test the behaviour of PositionApproximatorImpl.
 */
class TestPositionApproximator {

   private static final int GAME_OBJ_SIZE = 10;
   private static final int GAME_OBJ_DISTANCE = 10;
   private static final int TARGET_INITIAL_POSITION = 8;
   private static final int TARGET_SECOND_POSITION = 30;
   private static final int TARGET_THIRD_POSITION = 200;
   private static final int NUMBER_OF_GAME_OBJECTS = 10;

   private final PositionApproximator approximator = new PositionApproximatorImpl();
   private final GameObjectFactory factory  = new GameObjectFactoryImpl();
      /**
      * Test the behaviour of getApproximatedPosition, test if is always near to something.
      */
    @Test
    void CheckPositionTest() {
        final Dimension dim = new Dimension(GAME_OBJ_SIZE, GAME_OBJ_SIZE);

        var target = factory.createGameObjectWithEmptyGraphics(new Point(TARGET_INITIAL_POSITION, TARGET_INITIAL_POSITION), dim);
        final List<GameObject> list = IntStream.range(0, NUMBER_OF_GAME_OBJECTS)
        .mapToObj(i -> factory.createGameObjectWithEmptyGraphics(new Point(i * GAME_OBJ_DISTANCE, i * GAME_OBJ_DISTANCE), dim))
        .collect(Collectors.toList());

        assertNotEquals(list.get(0), approximator.getApproximatedPosition(target, list).get());
        assertEquals(list.get(1), approximator.getApproximatedPosition(target, list).get());

        target = factory.createGameObjectWithEmptyGraphics(new Point(TARGET_SECOND_POSITION, TARGET_SECOND_POSITION), dim);
        assertNotEquals(list.get(4), approximator.getApproximatedPosition(target, list).get());
        assertEquals(list.get(3), approximator.getApproximatedPosition(target, list).get());

        target = factory.createGameObjectWithEmptyGraphics(new Point(TARGET_THIRD_POSITION, TARGET_THIRD_POSITION), dim);
        assertFalse(approximator.getApproximatedPosition(target, list).isEmpty());
        assertEquals(list.get(list.size() -1), approximator.getApproximatedPosition(target, list).get());

     }

     @Test
     void checkEmptyList(){
         final Dimension dim = new Dimension(GAME_OBJ_SIZE, GAME_OBJ_SIZE);
         var target = factory.createGameObjectWithEmptyGraphics(new Point(TARGET_INITIAL_POSITION, TARGET_INITIAL_POSITION), dim);
         final List<GameObject> list = List.of();
         assertFalse(approximator.getApproximatedPosition(target, list).isPresent());
     }

      @Test
      void testSamePosition(){

      }

}
