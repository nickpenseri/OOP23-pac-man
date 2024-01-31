package it.unibo.model.ghost.impl;

import java.io.File;
import java.net.URL;
import java.util.Optional;
import it.unibo.model.api.Direction;
import it.unibo.model.pacman.api.ImageChooser;

/**
 * This class represents an implementation of ImageChooser, which can be updated
 * and can give
 * the actual image.
 */
public class BlueGhostMovementImages implements ImageChooser {

    private static final String COMMON_NAME = "image" + File.separator + "ghost" + File.separator + "blue" + File.separator;

    private static final String STOPPED = COMMON_NAME + "Ghost.png";
    private static final String UP_TAIL_EXTENDED = COMMON_NAME + "GhostUp.png";
    private static final String UP_TAIL_RETRACTED = COMMON_NAME + "GhostUp2.png";
    private static final String RIGHT_TAIL_EXTENDED = COMMON_NAME + "Right2.png";
    private static final String RIGHT_TAIL_RETRACTED = COMMON_NAME + "Right.png";
    private static final String DOWN_TAIL_EXTENDED = COMMON_NAME + "Down2.png";
    private static final String DOWN_TAIL_RETRACTED = COMMON_NAME + "Down.png";
    private static final String LEFT_TAIL_EXTENDED = COMMON_NAME + "Left2.png";
    private static final String LEFT_TAIL_RETRACTED = COMMON_NAME + "Left.png";
    private boolean tailExtended;

    /**
     * Creates an object of this class which initial state is closed.
     */
    public BlueGhostMovementImages() {
        this.tailExtended = true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update() {
        this.tailExtended = !this.tailExtended;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public URL actualImageUrl(final Optional<Direction> dir) {
        final String actualImage = select(dir);
        return ClassLoader.getSystemResource(actualImage);
    }

    private String select(final Optional<Direction> dir) {
        if (dir.isEmpty()) {
            return STOPPED;
        } else if (this.tailExtended) {
            return switch (dir.get()) {
                case UP -> UP_TAIL_RETRACTED;
                case RIGHT -> RIGHT_TAIL_RETRACTED;
                case DOWN -> DOWN_TAIL_RETRACTED;
                case LEFT -> LEFT_TAIL_RETRACTED;
            };
        } else {
            return switch (dir.get()) {
                case UP -> UP_TAIL_EXTENDED;
                case RIGHT -> RIGHT_TAIL_EXTENDED;
                case DOWN -> DOWN_TAIL_EXTENDED;
                case LEFT -> LEFT_TAIL_EXTENDED;
            };
        }
    }
}
