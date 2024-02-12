package it.unibo.model.physics.timer.impl;

import it.unibo.model.physics.timer.api.Timer;

/** implementation of a clock. */
public class ClockTimer implements Timer {

    private final Timer timerOn;
    private final Timer timerOff;

    /**
     * Creates a Clock with the given duration.
     * 
     * @param duration the duration of the Clock
     * @throws IllegalArgumentException if the duration is negative
     */
    public ClockTimer(final long duration) {
        if (duration < 0) {
            throw new IllegalArgumentException("Duration cannot be negative");
        }
        timerOn = new TimerImpl(duration);
        timerOff = new TimerImpl(duration);
    }

    /**
     * {@inheritDoc}
     * @throws IllegalArgumentException if the elapsed time is negative
     */
    @Override
    public boolean update(final Long elapsedTime) {
        if (elapsedTime < 0) {
            throw new IllegalArgumentException("Elapsed time cannot be negative");
        }
        timerOn.update(elapsedTime);
        if (timerOn.isOn() && timerOff.update(elapsedTime)) {
            timerOn.reset();
            timerOff.reset();
        }

        return isOn();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isOn() {
        return timerOn.isOn();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void reset() {
        timerOn.reset();
        timerOff.reset();
    }

}
