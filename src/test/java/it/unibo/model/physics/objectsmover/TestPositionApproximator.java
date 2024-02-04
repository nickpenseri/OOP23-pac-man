package it.unibo.model.physics.objectsmover;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import java.awt.Point;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import it.unibo.model.api.GameObject;
import it.unibo.model.api.GameObjectFactory;
import it.unibo.model.impl.GameObjectFactoryImpl;
import it.unibo.model.impl.GameObjectImpl.Type;
import it.unibo.model.physics.objectsmover.api.PositionApproximator;
import it.unibo.model.physics.objectsmover.impl.PositionApproximatorImpl;

/**
 * This class is used to test the behaviour of PositionApproximatorImpl.
 */
class TestPositionApproximator {

   private static final int GAME_OBJ_SIZE = 10;
   private static final int MAP_SIZE = 1;
   private static final int GAME_OBJ_DISTANCE = 10;
   private static final int TARGET_INIT_POSITION = 8;
   private static final int TARGET_SECOND_POSITION = 30;
   private static final int TARGET_THIRD_POSITION = 200;
   private static final int NUMBER_OF_GAME_OBJECTS = 10;

   private final PositionApproximator approximator = new PositionApproximatorImpl();
   private final GameObjectFactory factory = new GameObjectFactoryImpl(GAME_OBJ_SIZE, GAME_OBJ_SIZE, MAP_SIZE, MAP_SIZE);

   /**
    * Test the behaviour of getApproximatedPosition, test if is always near to
    * something.
    */
   @Test
   void checkPositionTest() {

      var target = factory.createGameObject(new Point(TARGET_INIT_POSITION, TARGET_INIT_POSITION), Type.FLOR);
      final List<GameObject> list = IntStream.range(0, NUMBER_OF_GAME_OBJECTS)
            .mapToObj(i -> factory
            .createGameObject(new Point(i * GAME_OBJ_DISTANCE, i * GAME_OBJ_DISTANCE), Type.FLOR))
            .collect(Collectors.toList());

      assertNotEquals(list.get(0), approximator.getApproximatedPosition(target, new HashSet<>(list)).get());
      assertEquals(list.get(1), approximator.getApproximatedPosition(target,  new HashSet<>(list)).get());

      target = factory.createGameObject(new Point(TARGET_SECOND_POSITION, TARGET_SECOND_POSITION), Type.FLOR);
      assertNotEquals(list.get(4), approximator.getApproximatedPosition(target, new HashSet<>(list)).get());
      assertEquals(list.get(3), approximator.getApproximatedPosition(target,  new HashSet<>(list)).get());

      target =  factory.createGameObject(new Point(TARGET_THIRD_POSITION, TARGET_THIRD_POSITION), Type.FLOR);
      assertFalse(approximator.getApproximatedPosition(target,  new HashSet<>(list)).isEmpty());
      assertEquals(list.get(list.size() - 1), approximator.getApproximatedPosition(target,  new HashSet<>(list)).get());

   }

   @Test
   void checkEmptyList() {
      final var target = factory.createGameObject(new Point(TARGET_INIT_POSITION, TARGET_INIT_POSITION), Type.FLOR);
      final Set<GameObject> list = Set.of();
      assertFalse(approximator.getApproximatedPosition(target, list).isPresent());
   }

   @Test
   void testSamePosition() {
      final var target = factory.createGameObject(new Point(TARGET_INIT_POSITION, TARGET_INIT_POSITION), Type.FLOR);
      final List<GameObject> list = IntStream.range(0, NUMBER_OF_GAME_OBJECTS)
            .mapToObj(i -> factory
            .createGameObject(new Point(TARGET_INIT_POSITION, TARGET_INIT_POSITION), Type.FLOR))
            .collect(Collectors.toList());
      assertTrue(list.contains(approximator.getApproximatedPosition(target,  new HashSet<>(list)).get()));
   }

}
