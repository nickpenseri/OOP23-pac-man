package it.unibo.model.physics.objectsmover;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import it.unibo.model.api.GameObject;
import it.unibo.model.impl.GameObjectImpl;
import it.unibo.model.physics.objectsmover.api.PositionApproximator;
import it.unibo.model.physics.objectsmover.impl.PositionApproximatorImpl;


/**
 * This class is used to test the behaviour of PositionApproximatorImpl.
 */
class TestPositionApproximator {
   
    private PositionApproximator approximator;


    @BeforeAll
    void test() {
       approximator = new PositionApproximatorImpl();
    }


    void EmptyTest() { }
}
