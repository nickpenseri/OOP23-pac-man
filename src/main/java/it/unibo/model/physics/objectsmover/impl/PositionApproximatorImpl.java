package it.unibo.model.physics.objectsmover.impl;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

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
    public Optional<GameObject> getApproximatedPosition(final GameObject target, final List<GameObject> list) {
        Objects.requireNonNull(target);
        Objects.requireNonNull(list);

        if (list.isEmpty()) {
            return Optional.empty();
        }

        return Optional.of(list.stream()
        .min(Comparator.comparingDouble(o -> getDistance(target, o)))).get();
    }


    private double getDistance(final GameObject target, final GameObject object) {
        final var targetPos = Objects.requireNonNull(target.getPosition());
        final var objPos = Objects.requireNonNull(object.getPosition());
        return Math.sqrt(Math.pow(targetPos.x - objPos.x, 2) + Math.pow(targetPos.y - objPos.y, 2));
    }

}
