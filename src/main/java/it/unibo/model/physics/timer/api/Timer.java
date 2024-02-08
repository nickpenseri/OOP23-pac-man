package it.unibo.model.physics.timer.api;

/**
 * This interface models a timer.
 */
public interface Timer {
    /**
     * @param elapsedTime the time elapsed from the start of the timer
     * @return if the timer is on
     */
    boolean isOn(Long elapsedTime);

    /**
     * @ reset the timer
     */
    void reset();
}
