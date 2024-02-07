package it.unibo.model.physics.objectsmover.impl;

import java.util.Comparator;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import it.unibo.model.api.GameObject;
import it.unibo.model.physics.objectsmover.api.PositionApproximator;

/**
* Implementation of the interface PositionApproximator, approximates the position of a target in a list of game objects.
*/
public class PositionApproximatorImpl implements PositionApproximator {

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<GameObject> getApproximatedTarget(final GameObject target, final Set<GameObject> list) {
        Objects.requireNonNull(target);
        Objects.requireNonNull(list);

        if (list.isEmpty()) {
            return Optional.empty();
        }

        return Optional.of(list.stream()
        .min(Comparator.comparingDouble(o -> getDistance(target, o)))).get();
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public Double getDistance(final GameObject target, final GameObject object) {
        final var targetPos = Objects.requireNonNull(target.getPosition());
        final var objPos = Objects.requireNonNull(object.getPosition());
        return Math.sqrt(Math.pow(targetPos.x - objPos.x, 2) + Math.pow(targetPos.y - objPos.y, 2));
    }

     /**
     * {@inheritDoc}
     */
    @Override
    public Boolean isPositionCloseEnough(final GameObject object1, final GameObject object2, final Double tolerance) {
        Objects.requireNonNull(object1);
        Objects.requireNonNull(object2);
        final var pos1 = Objects.requireNonNull(object1.getPosition());
        final var pos2 = Objects.requireNonNull(object2.getPosition());
        final double distance = Math.sqrt(Math.pow(pos1.x - pos2.x, 2) + Math.pow(pos1.y - pos2.y, 2));
        return distance <= tolerance;
    }
}
