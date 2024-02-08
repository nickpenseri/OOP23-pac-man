package it.unibo.model.physics.timer;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import it.unibo.model.physics.timer.api.Timer;
import it.unibo.model.physics.timer.impl.ClockTimer;
import it.unibo.model.physics.timer.impl.TimerImpl;

/**
 * Test for the Timer.
 
 */
class TestClockTimer {
    private static final long DURATION = 1000;
    private static final long HALF_DURATION = DURATION / 2;

    @Test
    void testasNormalTimer() {
        final Timer timer = new ClockTimer(DURATION);
        assertTrue(timer.update(DURATION));
        assertTrue(timer.isOn());
        timer.reset();
        assertFalse(timer.update(0L));
        assertFalse(timer.isOn());
        assertFalse(timer.update(HALF_DURATION));
        assertFalse(timer.isOn());
        assertTrue(timer.update(HALF_DURATION));
        assertTrue(timer.isOn());
        timer.reset();
        assertTrue(timer.update(DURATION + 1));
        assertTrue(timer.isOn());
    }

    @Test
    void testasClockTimer() {
        final Timer timer = new ClockTimer(DURATION);
        assertFalse(timer.update(DURATION-1));
        assertFalse(timer.isOn());
        assertTrue(timer.update(1L));
        assertTrue(timer.isOn());
        assertTrue(timer.update(HALF_DURATION));
        assertTrue(timer.isOn());
        assertTrue(timer.update(HALF_DURATION - 2));
        assertTrue(timer.isOn());
        assertFalse(timer.update(1L));
        assertFalse(timer.isOn());
    }

}
