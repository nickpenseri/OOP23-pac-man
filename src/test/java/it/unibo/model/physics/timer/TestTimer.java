package it.unibo.model.physics.timer;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import it.unibo.model.physics.timer.api.Timer;
import it.unibo.model.physics.timer.impl.TimerImpl;

/**
 * Test for the Timer.
 
 */
class TestTimer {
    private static final long DURATION = 1000;
    private static final long HALF_DURATION = DURATION / 2;

    @Test
    void testTimer() {
        final Timer timer = new TimerImpl(DURATION);
        assertTrue(timer.isOn(DURATION));
        timer.reset();
        assertFalse(timer.isOn(0L));
        assertFalse(timer.isOn(HALF_DURATION));
        assertTrue(timer.isOn(HALF_DURATION));
        timer.reset();
        assertTrue(timer.isOn(DURATION + 1));
    }

}
