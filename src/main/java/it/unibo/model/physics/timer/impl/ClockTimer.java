package it.unibo.model.physics.timer.impl;

import it.unibo.model.physics.timer.api.Timer;
public class ClockTimer implements Timer{

    private final Timer timerOn;
    private final Timer timerOff;

    /**
     * Creates a timer with the given duration.
     * 
     * @param duration the duration of the timer
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
     * {@ineritDoc}
     * @throws IllegalArgumentException if the elapsed time is negative
     */
    @Override
    public boolean update(Long elapsedTime) {
        if (elapsedTime < 0) {
            throw new IllegalArgumentException("Elapsed time cannot be negative");
        }
       
        
        return isOn();
    }

    /**
     * {@ineritDoc}
     */
    @Override
    public boolean isOn() {
        return timerOn.isOn();
    }

    @Override
    public void reset() {
        timerOn.reset();
        timerOff.reset();
    }
    
}
