package it.unibo.model.physics.timer.impl;

import it.unibo.model.physics.timer.api.Timer;


/**
 * This class models a timer.
 */
public class TimerImpl implements Timer {

    private final long duration;
    private long currentTime;

    /**
     * Creates a timer with the given duration.
     * 
     * @param duration the duration of the timer
     * @throws IllegalArgumentException if the duration is negative
     */
    public TimerImpl(final long duration) {
        if (duration < 0) {
            throw new IllegalArgumentException("Duration cannot be negative");
        }
        this.duration = duration;
    }

    /**
     * {@inheritDoc}
     * @throws IllegalArgumentException if the elapsed time is negative
     */
    @Override
    public boolean isOn(final Long elapsedTime) {
        if (elapsedTime < 0) {
            throw new IllegalArgumentException("Elapsed time cannot be negative");
        }
        currentTime += elapsedTime;
        return currentTime <= duration;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void reset() {
        currentTime = 0;
    }
}
